package org.cyk.system.bakery.business.impl.production;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.cyk.system.bakery.business.api.production.BakeryUnitDeliveryResellerProductionBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitDeliveryResellerProduction;
import org.cyk.system.bakery.persistence.api.production.BakeryUnitDeliveryResellerProductionDao;

@Stateless
public class BakeryUnitDeliveryResellerProductionBusinessImpl extends AbstractDeliveryProductionBusinessImpl<BakeryUnitDeliveryResellerProduction, BakeryUnitDeliveryResellerProductionDao> implements BakeryUnitDeliveryResellerProductionBusiness,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	
	@Inject
	public BakeryUnitDeliveryResellerProductionBusinessImpl(BakeryUnitDeliveryResellerProductionDao dao) {
		super(dao);
	}
	
	

}
