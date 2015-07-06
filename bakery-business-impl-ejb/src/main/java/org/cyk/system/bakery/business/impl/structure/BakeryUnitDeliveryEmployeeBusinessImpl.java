package org.cyk.system.bakery.business.impl.structure;

import java.io.Serializable;

import javax.inject.Inject;

import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryEmployeeBusiness;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryEmployee;
import org.cyk.system.bakery.persistence.api.structure.BakeryUnitDeliveryEmployeeDao;
import org.cyk.system.root.business.impl.party.person.AbstractActorBusinessImpl;

public class BakeryUnitDeliveryEmployeeBusinessImpl extends AbstractActorBusinessImpl<BakeryUnitDeliveryEmployee, BakeryUnitDeliveryEmployeeDao> implements BakeryUnitDeliveryEmployeeBusiness,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	@Inject
	public BakeryUnitDeliveryEmployeeBusinessImpl(BakeryUnitDeliveryEmployeeDao dao) {
		super(dao);
	}

}
