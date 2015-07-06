package org.cyk.system.bakery.business.impl.production;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.cyk.system.bakery.business.api.production.AbstractDeliveryProductionBusiness;
import org.cyk.system.bakery.model.production.AbstractBakeryUnitDeliveryProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.persistence.api.production.AbstractDeliveryProductionDao;
import org.cyk.system.root.business.impl.AbstractTypedBusinessService;

public abstract class AbstractDeliveryProductionBusinessImpl<DELIVERY_PRODUCTION extends AbstractBakeryUnitDeliveryProduction,DAO extends AbstractDeliveryProductionDao<DELIVERY_PRODUCTION>> extends AbstractTypedBusinessService<DELIVERY_PRODUCTION, DAO> implements AbstractDeliveryProductionBusiness<DELIVERY_PRODUCTION>,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	public AbstractDeliveryProductionBusinessImpl(DAO dao) {
		super(dao);
	}
	
	@Override @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Collection<DELIVERY_PRODUCTION> findByBakeryUnitProduction(BakeryUnitProduction bakeryUnitProduction) {
		return dao.readByBakeryUnitProduction(bakeryUnitProduction);
	}

}
