package org.cyk.system.bakery.persistence.api.structure;

import java.util.Collection;

import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.company.model.structure.Company;
import org.cyk.system.root.persistence.api.TypedDao;

public interface BakeryUnitDao extends TypedDao<BakeryUnit> {

	Collection<BakeryUnit> readByCompanies(Collection<Company> companies);

}
