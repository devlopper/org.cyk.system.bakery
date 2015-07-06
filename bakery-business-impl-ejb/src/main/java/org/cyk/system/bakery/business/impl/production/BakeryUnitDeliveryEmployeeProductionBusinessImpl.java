package org.cyk.system.bakery.business.impl.production;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.cyk.system.bakery.business.api.production.BakeryUnitDeliveryEmployeeProductionBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitDeliveryEmployeeProduction;
import org.cyk.system.bakery.persistence.api.production.BakeryUnitDeliveryEmployeeProductionDao;

@Stateless
public class BakeryUnitDeliveryEmployeeProductionBusinessImpl extends AbstractDeliveryProductionBusinessImpl<BakeryUnitDeliveryEmployeeProduction, BakeryUnitDeliveryEmployeeProductionDao> implements BakeryUnitDeliveryEmployeeProductionBusiness,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	@Inject
	public BakeryUnitDeliveryEmployeeProductionBusinessImpl(BakeryUnitDeliveryEmployeeProductionDao dao) {
		super(dao);
	}
	
	

}
