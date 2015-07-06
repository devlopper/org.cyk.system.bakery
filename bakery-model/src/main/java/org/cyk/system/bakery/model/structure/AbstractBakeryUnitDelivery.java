package org.cyk.system.bakery.model.structure;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.party.person.AbstractActor;

@Getter @Setter @NoArgsConstructor @MappedSuperclass
public abstract class AbstractBakeryUnitDelivery extends AbstractActor implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@ManyToOne protected BakeryUnit bakeryUnit;

	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal amountPaidGapCumulation = BigDecimal.ZERO;

	public AbstractBakeryUnitDelivery(BakeryUnit bakeryUnit) {
		super();
		this.bakeryUnit = bakeryUnit;
	}
	
}
