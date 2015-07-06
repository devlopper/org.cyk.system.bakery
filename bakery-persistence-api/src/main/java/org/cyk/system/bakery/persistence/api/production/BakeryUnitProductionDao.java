package org.cyk.system.bakery.persistence.api.production;

import java.util.Collection;

import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProductionSearchCriteria;
import org.cyk.system.root.persistence.api.TypedDao;

public interface BakeryUnitProductionDao extends TypedDao<BakeryUnitProduction> {

	Collection<BakeryUnitProduction> readByCriteria(BakeryUnitProductionSearchCriteria searchCriteria);

	Long countByCriteria(BakeryUnitProductionSearchCriteria searchCriteria);

   
}
