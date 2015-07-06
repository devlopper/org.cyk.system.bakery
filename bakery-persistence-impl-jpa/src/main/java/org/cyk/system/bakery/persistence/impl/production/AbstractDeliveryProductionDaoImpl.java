package org.cyk.system.bakery.persistence.impl.production;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.bakery.model.production.AbstractBakeryUnitDeliveryProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.persistence.api.production.AbstractDeliveryProductionDao;
import org.cyk.system.root.persistence.impl.AbstractTypedDao;

public abstract class AbstractDeliveryProductionDaoImpl<DELIVERY_PRODUCTION extends AbstractBakeryUnitDeliveryProduction> extends AbstractTypedDao<DELIVERY_PRODUCTION> implements AbstractDeliveryProductionDao<DELIVERY_PRODUCTION>,Serializable {

	private static final long serialVersionUID = -1712788156426144935L;

	private String readByBakeryUnitProduction;
	
	@Override
	protected void namedQueriesInitialisation() {
		super.namedQueriesInitialisation();
		registerNamedQuery(readByBakeryUnitProduction, _select().where("bakeryUnitProduction"));
	}
	
	@Override
	public Collection<DELIVERY_PRODUCTION> readByBakeryUnitProduction(BakeryUnitProduction bakeryUnitProduction) {
		return namedQuery(readByBakeryUnitProduction).parameter("bakeryUnitProduction", bakeryUnitProduction).resultMany();
	}

}
