package org.cyk.system.bakery.persistence.impl.production;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProductionSearchCriteria;
import org.cyk.system.bakery.persistence.api.production.BakeryUnitProductionDao;
import org.cyk.system.root.persistence.impl.AbstractTypedDao;
import org.cyk.system.root.persistence.impl.QueryWrapper;

public class BakeryUnitProductionDaoImpl extends AbstractTypedDao<BakeryUnitProduction> implements BakeryUnitProductionDao,Serializable {

	private static final long serialVersionUID = -1712788156426144935L;

	private static final String READ_BY_CRITERIA_SELECT_FORMAT = "SELECT bup FROM BakeryUnitProduction bup ";
	private static final String READ_BY_CRITERIA_WHERE_FORMAT = "WHERE bup.productionSpreadSheet.period.fromDate BETWEEN :fromDate AND :toDate ";
	
	private static final String READ_BY_CRITERIA_NOTORDERED_FORMAT = READ_BY_CRITERIA_SELECT_FORMAT+READ_BY_CRITERIA_WHERE_FORMAT;
	private static final String READ_BY_CRITERIA_ORDERED_FORMAT = READ_BY_CRITERIA_SELECT_FORMAT+READ_BY_CRITERIA_WHERE_FORMAT+ORDER_BY_FORMAT;
	
	private String readAllSortedByDate,readByCriteria,countByCriteria,readByCriteriaDateAscendingOrder,readByCriteriaDateDescendingOrder;
	
	@Override
    protected void namedQueriesInitialisation() {
    	super.namedQueriesInitialisation();
    	registerNamedQuery(readAllSortedByDate,READ_BY_CRITERIA_SELECT_FORMAT+" ORDER BY bup.productionSpreadSheet.period.fromDate DESC");
    	registerNamedQuery(readByCriteria,READ_BY_CRITERIA_NOTORDERED_FORMAT+" ORDER BY bup.productionSpreadSheet.period.fromDate ASC");
        registerNamedQuery(readByCriteriaDateAscendingOrder,String.format(READ_BY_CRITERIA_ORDERED_FORMAT, "bup.productionSpreadSheet.period.fromDate ASC") );
        registerNamedQuery(readByCriteriaDateDescendingOrder,String.format(READ_BY_CRITERIA_ORDERED_FORMAT, "bup.productionSpreadSheet.period.fromDate DESC") );
    }
	
	@Override
	public Collection<BakeryUnitProduction> readAll() {
		return namedQuery(readAllSortedByDate).resultMany();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<BakeryUnitProduction> readByCriteria(BakeryUnitProductionSearchCriteria searchCriteria) {
		String queryName = null;
		if(searchCriteria.getFromDateSearchCriteria().getAscendingOrdered()!=null){
			queryName = Boolean.TRUE.equals(searchCriteria.getFromDateSearchCriteria().getAscendingOrdered())?
					readByCriteriaDateAscendingOrder:readByCriteriaDateDescendingOrder;
		}else
			queryName = readByCriteriaDateAscendingOrder;
		QueryWrapper<?> queryWrapper = namedQuery(queryName);
		applyCriteriaParameters(queryWrapper, searchCriteria);
		return (Collection<BakeryUnitProduction>) queryWrapper.resultMany();
	}

	@Override
	public Long countByCriteria(BakeryUnitProductionSearchCriteria searchCriteria) {
		QueryWrapper<?> queryWrapper = countNamedQuery(countByCriteria);
		applyCriteriaParameters(queryWrapper, searchCriteria);
		return (Long) queryWrapper.resultOne();
	}
	
	protected void applyCriteriaParameters(QueryWrapper<?> queryWrapper,BakeryUnitProductionSearchCriteria searchCriteria){
		queryWrapper.parameter("fromDate",searchCriteria.getFromDateSearchCriteria().getPreparedValue());
		queryWrapper.parameter("toDate",searchCriteria.getToDateSearchCriteria().getPreparedValue());
	}

}
