package org.cyk.system.bakery.ui.web.primefaces.production;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.business.api.production.BakeryUnitProductionBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProductionSearchCriteria;
import org.cyk.system.bakery.ui.web.primefaces.model.BakeryUnitProductionQueryFormModel;
import org.cyk.system.bakery.ui.web.primefaces.model.BakeryUnitProductionQueryResultFormModel;
import org.cyk.system.root.business.impl.RootBusinessLayer;
import org.cyk.ui.api.model.table.Row;
import org.cyk.ui.web.primefaces.Commandable;
import org.cyk.ui.web.primefaces.page.AbstractBusinessQueryPage;

@Named @ViewScoped @Getter @Setter
public class BakeryUnitProductionListPage extends AbstractBusinessQueryPage<BakeryUnitProduction, BakeryUnitProductionQueryFormModel, 
	BakeryUnitProductionQueryResultFormModel> implements Serializable {

	private static final long serialVersionUID = 9040359120893077422L;

	@Inject private BakeryUnitProductionBusiness bakeryUnitProductionBusiness;

	@Override
	protected void initialisation() {
		super.initialisation();
		table.setShowToolBar(Boolean.FALSE);
		((Commandable)table.getAddRowCommandable()).updateLabel(languageBusiness.findDoFunctionnalityText(BakeryUnitProduction.class));
		table.getAddRowCommandable().setRendered(Boolean.TRUE);
		table.setShowOpenCommand(Boolean.TRUE);
		
	}
	
	@Override
	protected void rowAdded(Row<Object> row) {
		super.rowAdded(row);
		row.setOpenable(Boolean.TRUE);
	}
		
	@Override
	protected Collection<BakeryUnitProduction> __query__() {
		BakeryUnitProductionSearchCriteria searchCriteria = searchCriteria();
		Collection<BakeryUnitProduction> collection = bakeryUnitProductionBusiness.findByCriteria(searchCriteria);
		table.getPrintCommandable().setParameter(RootBusinessLayer.getInstance().getParameterFromDate(),searchCriteria.getFromDateSearchCriteria().getPreparedValue().getTime());
		table.getPrintCommandable().setParameter(RootBusinessLayer.getInstance().getParameterToDate(),searchCriteria.getToDateSearchCriteria().getPreparedValue().getTime());
		return collection;
	}
	
	@Override
	protected Long __count__() {
		return bakeryUnitProductionBusiness.countByCriteria(searchCriteria());
	}
	
	@Override
	protected Boolean autoLoad() {
		return Boolean.TRUE;
	}
	
	private BakeryUnitProductionSearchCriteria searchCriteria() {
		return new BakeryUnitProductionSearchCriteria(form.getData().getFromDate(),form.getData().getToDate());
	}

	@Override
	protected Class<BakeryUnitProduction> __entityClass__() {
		return BakeryUnitProduction.class;
	}

	@Override
	protected Class<BakeryUnitProductionQueryFormModel> __queryClass__() {
		return BakeryUnitProductionQueryFormModel.class;
	}

	@Override
	protected Class<BakeryUnitProductionQueryResultFormModel> __resultClass__() {
		return BakeryUnitProductionQueryResultFormModel.class;
	}
	
	
}