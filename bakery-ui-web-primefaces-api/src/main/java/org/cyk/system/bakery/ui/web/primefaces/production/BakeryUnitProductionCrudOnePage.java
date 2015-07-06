package org.cyk.system.bakery.ui.web.primefaces.production;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.business.api.structure.BakeryUnitBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.bakery.ui.web.primefaces.model.BakeryUnitProductionFormModel;
import org.cyk.system.company.business.api.structure.CompanyBusiness;
import org.cyk.system.company.model.structure.Company;
import org.cyk.system.root.business.api.BusinessEntityInfos;
import org.cyk.system.root.business.api.Crud;
import org.cyk.system.root.model.party.person.Person;
import org.cyk.ui.api.data.collector.form.AbstractFormModel;
import org.cyk.ui.web.primefaces.page.crud.AbstractCrudOnePage;

@Named @ViewScoped @Getter @Setter
public class BakeryUnitProductionCrudOnePage extends AbstractCrudOnePage<BakeryUnitProduction> implements Serializable {

	private static final long serialVersionUID = 9040359120893077422L;

	@Inject private BakeryUnitBusiness bakeryUnitBusiness;
	@Inject private CompanyBusiness companyBusiness;
	
	@Override
	protected void initialisation() {
		super.initialisation();
		Collection<Company> companies = companyBusiness.findByManager((Person) getUserSession().getUser());
		Collection<BakeryUnit> bakeryUnits = bakeryUnitBusiness.findByCompanies(companies);
		BakeryUnit bakeryUnit = bakeryUnits.iterator().next();
		identifiable.setBakeryUnit(bakeryUnit);
	}
	
	@Override
	protected BusinessEntityInfos fetchBusinessEntityInfos() {
		return uiManager.businessEntityInfos(BakeryUnitProduction.class);
	}
	
	@Override
	protected Crud crudFromRequestParameter() {
		return Crud.CREATE;
	}
	
	@Override
	protected Class<? extends AbstractFormModel<?>> __formModelClass__() {
		return BakeryUnitProductionFormModel.class;
	}
	
	/**/
	
	public BakeryUnitProductionFormModel formModel(){
		return (BakeryUnitProductionFormModel) form.getData();
	}
				
}