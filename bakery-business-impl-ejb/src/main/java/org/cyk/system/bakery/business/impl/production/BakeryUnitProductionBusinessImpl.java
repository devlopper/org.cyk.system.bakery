package org.cyk.system.bakery.business.impl.production;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.cyk.system.bakery.business.api.production.BakeryUnitProductionBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProductionSearchCriteria;
import org.cyk.system.bakery.model.structure.ProductionConfiguration;
import org.cyk.system.bakery.persistence.api.production.BakeryUnitProductionDao;
import org.cyk.system.bakery.persistence.api.structure.BakeryUnitDao;
import org.cyk.system.company.business.api.production.ProductionSpreadSheetBusiness;
import org.cyk.system.company.model.production.ProductionSpreadSheet;
import org.cyk.system.root.business.impl.AbstractTypedBusinessService;
import org.cyk.system.root.model.time.Period;
import org.joda.time.DateTime;

@Stateless
public class BakeryUnitProductionBusinessImpl extends AbstractTypedBusinessService<BakeryUnitProduction, BakeryUnitProductionDao> implements BakeryUnitProductionBusiness,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	@Inject private ProductionSpreadSheetBusiness productionSpreadSheetBusiness;
	@Inject private BakeryUnitDao bakeryUnitDao;
	
	@Inject
	public BakeryUnitProductionBusinessImpl(BakeryUnitProductionDao dao) {
		super(dao);
	}
	
	@Override
	public BakeryUnitProduction create(BakeryUnitProduction bakeryUnitProduction) {
		ProductionConfiguration productionConfiguration = bakeryUnitProduction.getBakeryUnit().getProductionConfiguration();
		bakeryUnitProduction.setProductionSpreadSheet(new ProductionSpreadSheet());
		Period period = bakeryUnitProduction.getProductionSpreadSheet().getPeriod();
		period.setFromDate(productionConfiguration.getNextReportDate());
		period.setToDate(period.getFromDate());
		productionSpreadSheetBusiness.create(bakeryUnitProduction.getProductionSpreadSheet());
		productionConfiguration.setPreviousReportDate(period.getFromDate());
		productionConfiguration.setNextReportDate(new DateTime(period.getFromDate()).plusMillis(productionConfiguration.getReportIntervalTimeDivisionType().getDuration().intValue())
				.toDate());
		bakeryUnitDao.update(bakeryUnitProduction.getBakeryUnit());
		return super.create(bakeryUnitProduction);
	}
	
	@Override
	public Collection<BakeryUnitProduction> findByCriteria(BakeryUnitProductionSearchCriteria searchCriteria) {
		getPersistenceService().getDataReadConfig().set(searchCriteria.getReadConfig());
		return dao.readByCriteria(searchCriteria);
	}

	@Override
	public Long countByCriteria(BakeryUnitProductionSearchCriteria searchCriteria) {
		return dao.countByCriteria(searchCriteria);
	}

}
