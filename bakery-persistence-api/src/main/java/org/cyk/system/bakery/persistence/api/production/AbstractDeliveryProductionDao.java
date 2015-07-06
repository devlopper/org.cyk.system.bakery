package org.cyk.system.bakery.persistence.api.production;

import java.util.Collection;

import org.cyk.system.bakery.model.production.AbstractBakeryUnitDeliveryProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.root.persistence.api.TypedDao;

public interface AbstractDeliveryProductionDao<DELIVERY_PRODUCTION extends AbstractBakeryUnitDeliveryProduction> extends TypedDao<DELIVERY_PRODUCTION> {

	Collection<DELIVERY_PRODUCTION> readByBakeryUnitProduction(BakeryUnitProduction bakeryUnitProduction);
	
}
