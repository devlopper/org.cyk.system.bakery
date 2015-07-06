package org.cyk.system.bakery.ui.web.primefaces.production;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.business.api.production.BakeryUnitProductionBusiness;
import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryEmployeeBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitDeliveryEmployeeProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryEmployee;
import org.cyk.system.bakery.ui.web.primefaces.model.BakeryUnitDeliveryEmployeeProductionFormModel;
import org.cyk.system.root.business.api.BusinessEntityInfos;
import org.cyk.system.root.business.api.Crud;
import org.cyk.ui.api.data.collector.form.AbstractFormModel;
import org.cyk.ui.web.primefaces.page.crud.AbstractCrudOnePage;

@Named @ViewScoped @Getter @Setter
public class BakeryUnitDeliveryEmployeeProductionCrudOnePage extends AbstractCrudOnePage<BakeryUnitDeliveryEmployeeProduction> implements Serializable {

	private static final long serialVersionUID = 9040359120893077422L;

	@Inject private BakeryUnitProductionBusiness bakeryUnitProductionBusiness;
	@Inject private BakeryUnitDeliveryEmployeeBusiness bakeryUnitDeliveryEmployeeBusiness;
	
	@Override
	protected void initialisation() {
		super.initialisation();
		if(Crud.CREATE.equals(crud)){
			identifiable.setBakeryUnitProduction(bakeryUnitProductionBusiness.find(requestParameterLong(BakeryUnitProduction.class)));
			identifiable.setBakeryUnitDeliveryEmployee(bakeryUnitDeliveryEmployeeBusiness.find(requestParameterLong(BakeryUnitDeliveryEmployee.class)));
		}
	}
	
	@Override
	protected BusinessEntityInfos fetchBusinessEntityInfos() {
		return uiManager.businessEntityInfos(BakeryUnitDeliveryEmployeeProduction.class);
	}
	
	@Override
	protected Crud crudFromRequestParameter() {
		return Crud.CREATE;
	}
	
	@Override
	protected Class<? extends AbstractFormModel<?>> __formModelClass__() {
		return BakeryUnitDeliveryEmployeeProductionFormModel.class;
	}
			
}