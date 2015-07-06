package org.cyk.system.bakery.business.api.production;

import java.util.Collection;

import org.cyk.system.bakery.model.production.AbstractBakeryUnitDeliveryProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.root.business.api.TypedBusiness;

public interface AbstractDeliveryProductionBusiness<DELIVERY_PRODUCTION extends AbstractBakeryUnitDeliveryProduction> extends TypedBusiness<DELIVERY_PRODUCTION> {

	Collection<DELIVERY_PRODUCTION> findByBakeryUnitProduction(BakeryUnitProduction bakeryUnitProduction);
	
}
