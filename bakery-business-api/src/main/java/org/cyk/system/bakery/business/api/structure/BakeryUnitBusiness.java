package org.cyk.system.bakery.business.api.structure;

import java.util.Collection;

import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.company.model.structure.Company;
import org.cyk.system.root.business.api.TypedBusiness;

public interface BakeryUnitBusiness extends TypedBusiness<BakeryUnit> {

	Collection<BakeryUnit> findByCompanies(Collection<Company> companies);
	

}
