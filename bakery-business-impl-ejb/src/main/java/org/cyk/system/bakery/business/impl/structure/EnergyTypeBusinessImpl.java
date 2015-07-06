package org.cyk.system.bakery.business.impl.structure;

import java.io.Serializable;

import javax.inject.Inject;

import org.cyk.system.bakery.business.api.structure.EnergyTypeBusiness;
import org.cyk.system.bakery.model.structure.EnergyType;
import org.cyk.system.bakery.persistence.api.structure.EnergyTypeDao;
import org.cyk.system.root.business.impl.AbstractEnumerationBusinessImpl;

public class EnergyTypeBusinessImpl extends AbstractEnumerationBusinessImpl<EnergyType, EnergyTypeDao> implements EnergyTypeBusiness,Serializable {

	private static final long serialVersionUID = -3799482462496328200L;
	
	@Inject
	public EnergyTypeBusinessImpl(EnergyTypeDao dao) {
		super(dao); 
	}   
	
}
