package org.cyk.system.bakery.business.api.production;

import java.util.Collection;

import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProductionSearchCriteria;
import org.cyk.system.root.business.api.TypedBusiness;

public interface BakeryUnitProductionBusiness extends TypedBusiness<BakeryUnitProduction> {

	Collection<BakeryUnitProduction> findByCriteria(BakeryUnitProductionSearchCriteria searchCriteria);

	Long countByCriteria(BakeryUnitProductionSearchCriteria searchCriteria);

}
