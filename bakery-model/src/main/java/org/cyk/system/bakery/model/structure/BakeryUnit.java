package org.cyk.system.bakery.model.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.company.model.structure.Company;
import org.cyk.system.root.model.AbstractIdentifiable;

@Getter @Setter @NoArgsConstructor @Entity
public class BakeryUnit extends AbstractIdentifiable implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@OneToOne @NotNull private Company company;

	@Embedded private ProductionConfiguration productionConfiguration = new ProductionConfiguration();
	
	@Embedded private BakeryUnitCounter counter = new BakeryUnitCounter();
	
	@Transient private Collection<BakeryUnitDeliveryEmployee> deliveryEmployees = new ArrayList<>();
	
	@Transient private Collection<BakeryUnitDeliveryReseller> deliveryResellers = new ArrayList<>();

	public BakeryUnit(Company company) {
		super();
		this.company = company;
	}

}
