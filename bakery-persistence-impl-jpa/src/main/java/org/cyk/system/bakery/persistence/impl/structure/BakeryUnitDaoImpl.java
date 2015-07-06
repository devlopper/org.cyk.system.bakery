package org.cyk.system.bakery.persistence.impl.structure;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.bakery.persistence.api.structure.BakeryUnitDao;
import org.cyk.system.company.model.structure.Company;
import org.cyk.system.root.persistence.impl.AbstractTypedDao;

public class BakeryUnitDaoImpl extends AbstractTypedDao<BakeryUnit> implements BakeryUnitDao,Serializable {

	private static final long serialVersionUID = -1712788156426144935L;

	private String readByCompanies;
	
	@Override
	protected void namedQueriesInitialisation() {
		super.namedQueriesInitialisation();
		registerNamedQuery(readByCompanies, _select().whereIdentifierIn("company"));
	}
	
	@Override
	public Collection<BakeryUnit> readByCompanies(Collection<Company> companies) {
		return namedQuery(readByCompanies).parameterIdentifiers(companies).resultMany();
	}
 
}
