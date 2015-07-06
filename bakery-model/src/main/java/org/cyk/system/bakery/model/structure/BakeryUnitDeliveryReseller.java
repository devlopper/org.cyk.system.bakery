package org.cyk.system.bakery.model.structure;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Entity
public class BakeryUnitDeliveryReseller extends AbstractBakeryUnitDelivery implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

}
