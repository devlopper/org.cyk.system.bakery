package org.cyk.system.bakery.business.impl.structure;

import java.io.Serializable;

import javax.inject.Inject;

import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryResellerBusiness;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryReseller;
import org.cyk.system.bakery.persistence.api.structure.BakeryUnitDeliveryResellerDao;
import org.cyk.system.root.business.impl.party.person.AbstractActorBusinessImpl;

public class BakeryUnitDeliveryResellerBusinessImpl extends AbstractActorBusinessImpl<BakeryUnitDeliveryReseller, BakeryUnitDeliveryResellerDao> implements BakeryUnitDeliveryResellerBusiness,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	@Inject
	public BakeryUnitDeliveryResellerBusinessImpl(BakeryUnitDeliveryResellerDao dao) {
		super(dao);
	}

}
