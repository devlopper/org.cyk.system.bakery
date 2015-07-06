package org.cyk.system.bakery.business.impl.structure;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.cyk.system.bakery.business.api.structure.BakeryUnitBusiness;
import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.bakery.persistence.api.structure.BakeryUnitDao;
import org.cyk.system.company.model.structure.Company;
import org.cyk.system.root.business.impl.AbstractTypedBusinessService;

@Stateless
public class BakeryUnitBusinessImpl extends AbstractTypedBusinessService<BakeryUnit, BakeryUnitDao> implements BakeryUnitBusiness,Serializable {

	private static final long serialVersionUID = -7830673760640348717L;

	@Inject
	public BakeryUnitBusinessImpl(BakeryUnitDao dao) {
		super(dao);
	}

	@Override @TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Collection<BakeryUnit> findByCompanies(Collection<Company> companies) {
		return dao.readByCompanies(companies);
	}
	
	

}
