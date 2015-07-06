package org.cyk.system.bakery.ui.web.primefaces.production;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.cyk.system.bakery.business.api.production.BakeryUnitDeliveryEmployeeProductionBusiness;
import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryEmployeeBusiness;
import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryResellerBusiness;
import org.cyk.system.bakery.model.production.AbstractBakeryUnitDeliveryProduction;
import org.cyk.system.bakery.model.production.BakeryUnitDeliveryEmployeeProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.production.SellDetails;
import org.cyk.system.bakery.model.structure.AbstractBakeryUnitDelivery;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryEmployee;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryReseller;
import org.cyk.system.bakery.ui.web.primefaces.model.BakeryUnitProductionOutputDetails;
import org.cyk.system.root.business.api.Crud;
import org.cyk.system.root.model.party.person.Person;
import org.cyk.ui.api.command.CommandAdapter;
import org.cyk.ui.api.command.UICommand;
import org.cyk.ui.api.model.table.Cell;
import org.cyk.ui.api.model.table.Column;
import org.cyk.ui.api.model.table.Row;
import org.cyk.ui.web.primefaces.Table;
import org.cyk.ui.web.primefaces.data.collector.control.ControlSetAdapter;
import org.cyk.ui.web.primefaces.data.collector.form.FormOneData;
import org.cyk.ui.web.primefaces.page.crud.AbstractConsultPage;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputText;
import org.cyk.utility.common.model.table.TableAdapter;

@Named @ViewScoped @Getter @Setter
public class BakeryUnitProductionConsultPage extends AbstractConsultPage<BakeryUnitProduction> implements Serializable {

	private static final long serialVersionUID = 9040359120893077422L;

	@Inject private BakeryUnitDeliveryEmployeeBusiness bakeryUnitDeliveryEmployeeBusiness;
	@Inject private BakeryUnitDeliveryEmployeeProductionBusiness bakeryUnitDeliveryEmployeeProductionBusiness;
	@Inject private BakeryUnitDeliveryResellerBusiness bakeryUnitDeliveryResellerBusiness;
	
	private List<BakeryUnitDeliveryEmployee> bakeryUnitDeliveryEmployees;
	private List<BakeryUnitDeliveryReseller> bakeryUnitDeliveryResellers;
	
	private FormOneData<BakeryUnitProductionOutputDetails> bakeryUnitProductionDetails;
	private Table<BakeryUnitDeliveryEmployeeProductionDetails> bakeryUnitDeliveryEmployeeProductionDetails;
	
	@Override
	protected void initialisation() {
		super.initialisation();
		contentTitle = text("company.production.consult.page.content.title");
		bakeryUnitDeliveryEmployees = new ArrayList<BakeryUnitDeliveryEmployee>(bakeryUnitDeliveryEmployeeBusiness.findAll());
		bakeryUnitDeliveryResellers = new ArrayList<BakeryUnitDeliveryReseller>(bakeryUnitDeliveryResellerBusiness.findAll());
		bakeryUnitProductionDetails = (FormOneData<BakeryUnitProductionOutputDetails>) createFormOneData(new BakeryUnitProductionOutputDetails(identifiable), Crud.READ);
		configureDetailsForm(bakeryUnitProductionDetails);
		bakeryUnitProductionDetails.setControlSetListener(new ControlSetAdapter<BakeryUnitProductionOutputDetails>(){
			@Override
			public Boolean build(Field field) {
				return !field.getName().equals("totalAmountPaid") && !field.getName().equals("returnBreadQuantity") && !field.getName().equals("returnUnitPrice");
			}
		}); 
	}
	
	@Override
	protected void afterInitialisation() {
		super.afterInitialisation();
		Collection<BakeryUnitDeliveryEmployeeProduction> bakeryUnitDeliveryEmployeeProductions = bakeryUnitDeliveryEmployeeProductionBusiness.findByBakeryUnitProduction(identifiable);
		Collection<BakeryUnitDeliveryEmployeeProductionDetails> details = new ArrayList<>();
		for(BakeryUnitDeliveryEmployee employee : bakeryUnitDeliveryEmployeeBusiness.findAll()){
			Boolean added =  Boolean.FALSE;
			for(BakeryUnitDeliveryEmployeeProduction production : bakeryUnitDeliveryEmployeeProductions){
				if(production.getBakeryUnitDeliveryEmployee().equals(employee)){
					details.add(new BakeryUnitDeliveryEmployeeProductionDetails(production));
					added = Boolean.TRUE;
					//break;
				}
			}
			if(Boolean.FALSE.equals(added))
				details.add(new BakeryUnitDeliveryEmployeeProductionDetails(employee));
		}
		
		TableAdapter<Row<BakeryUnitDeliveryEmployeeProductionDetails>, Column, BakeryUnitDeliveryEmployeeProductionDetails, String, Cell, String> listener = 
				new TableAdapter<Row<BakeryUnitDeliveryEmployeeProductionDetails>, Column, BakeryUnitDeliveryEmployeeProductionDetails, String, Cell, String>(){
			
			@Override
			public Boolean ignore(Field field) {
				return field.getName().equals("residualBreadQuantity");
			}
			
			@Override
			public void rowAdded(Row<BakeryUnitDeliveryEmployeeProductionDetails> row) {
				super.rowAdded(row);
				row.setEditable(row.getData().getDeliveryProduction()==null);
			}
		};
		bakeryUnitDeliveryEmployeeProductionDetails = createDetailsTable(BakeryUnitDeliveryEmployeeProductionDetails.class, details,listener, "model.entity.bakeryUnitDeliveryEmployee");	
		bakeryUnitDeliveryEmployeeProductionDetails.setShowHeader(Boolean.FALSE);
		bakeryUnitDeliveryEmployeeProductionDetails.setShowFooter(Boolean.FALSE);
		//bakeryUnitDeliveryEmployeeProductionDetails.setShowOpenCommand(Boolean.TRUE);
		bakeryUnitDeliveryEmployeeProductionDetails.setShowEditColumn(Boolean.TRUE);
		
		bakeryUnitDeliveryEmployeeProductionDetails.getCrudOneRowCommandable().getCommand().getCommandListeners().add(new CommandAdapter(){
			private static final long serialVersionUID = 1L;

			@Override
			public void serve(UICommand command, Object parameter) {
				@SuppressWarnings("unchecked")
				Row<BakeryUnitDeliveryEmployeeProductionDetails> row = (Row<BakeryUnitDeliveryEmployeeProductionDetails>) parameter;
				navigationManager.redirectTo("bakeryUnitDeliveryEmployeeProductionEditView", new Object[]{
						uiManager.businessEntityInfos(BakeryUnitProduction.class).getIdentifier(),identifiable.getIdentifier()
						,uiManager.businessEntityInfos(BakeryUnitDeliveryEmployee.class).getIdentifier(),
						row.getData().getDeliveryProduction()==null?row.getData().getDelivery().getIdentifier():row.getData().getDeliveryProduction().getBakeryUnitDeliveryEmployee().getIdentifier()
				});
				super.serve(command, parameter);
			}
		});
		//for(Row<BakeryUnitDeliveryEmployeeProductionDetails> row : bakeryUnitDeliveryEmployeeProductionDetails.getRows())
		//	row.setEditable(Boolean.TRUE);
		//bakeryUnitDeliveryEmployeeProductionDetails.setShowToolBar(Boolean.TRUE);
		//bakeryUnitDeliveryEmployeeProductionDetails.setReportIdentifier(CompanyBusinessLayer.getInstance().getReportStockDashboard());
		//bakeryUnitDeliveryEmployeeProductionDetails.setIdentifiableClass(TangibleProductStockMovement.class);
	}
	
	/**/
	
	@Getter @Setter
	private abstract class AbstractBakeryUnitDeliveryProductionDetails<DELIVERY extends AbstractBakeryUnitDelivery,DELIVERY_PRODUCTION extends AbstractBakeryUnitDeliveryProduction> implements Serializable {
		private static final long serialVersionUID = -1498269103849317057L;
		
		protected DELIVERY_PRODUCTION deliveryProduction;
		protected DELIVERY delivery;
		@Input @InputText protected String names;
		@Input @InputText private String takingBreadQuantity;
		@Input @InputText private String returnBreadQuantity;
		@Input @InputText private String residualBreadQuantity;
		@Input @InputText private String takingUnitPrice;
		@Input @InputText private String returnUnitPrice;
		@Input @InputText private String totalAmountPaid = "NULL";
		
		public AbstractBakeryUnitDeliveryProductionDetails(DELIVERY delivery) {
			this.delivery = delivery;
			this.names = person().getNames();
			SellDetails sellDetails = new SellDetails();
			init(sellDetails);
		}
		public AbstractBakeryUnitDeliveryProductionDetails(DELIVERY_PRODUCTION deliveryProduction) {
			this.deliveryProduction = deliveryProduction;
			this.names = person().getNames();
			init(deliveryProduction.getSellDetails());
		}
		
		private void init(SellDetails sellDetails){
			this.takingBreadQuantity = numberBusiness.format(sellDetails.getTakingDetails().getQuantity());
			this.returnBreadQuantity = numberBusiness.format(sellDetails.getReturnDetails().getQuantity());
			this.residualBreadQuantity = StringUtils.defaultString(residualBreadQuantity,"NULL");
			this.takingUnitPrice = numberBusiness.format(sellDetails.getTakingDetails().getUnitPrice());
			this.returnUnitPrice = numberBusiness.format(sellDetails.getReturnDetails().getUnitPrice());
			if(sellDetails.getReturnDetails().getAmountPaid()!=null)
				this.totalAmountPaid = StringUtils.defaultString(numberBusiness.format(sellDetails.getReturnDetails().getAmountPaid().getUser()),"NULL");
		}
		
		protected abstract Person person();
	}
	
	@Getter @Setter
	private class BakeryUnitDeliveryEmployeeProductionDetails extends AbstractBakeryUnitDeliveryProductionDetails<BakeryUnitDeliveryEmployee,BakeryUnitDeliveryEmployeeProduction> implements Serializable {
		private static final long serialVersionUID = -1498269103849317057L;
		
		public BakeryUnitDeliveryEmployeeProductionDetails(BakeryUnitDeliveryEmployeeProduction bakeryUnitDeliveryEmployeeProduction) {
			super(bakeryUnitDeliveryEmployeeProduction);
		}
		public BakeryUnitDeliveryEmployeeProductionDetails(BakeryUnitDeliveryEmployee bakeryUnitDeliveryEmployee) {
			super(bakeryUnitDeliveryEmployee);
		}

		@Override
		protected Person person() {
			return delivery==null?deliveryProduction.getBakeryUnitDeliveryEmployee().getPerson():delivery.getPerson();
		}
	}
	
}