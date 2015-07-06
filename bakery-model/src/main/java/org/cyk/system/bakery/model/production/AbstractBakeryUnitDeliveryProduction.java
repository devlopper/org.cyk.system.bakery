package org.cyk.system.bakery.model.production;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractIdentifiable;

@Getter @Setter @NoArgsConstructor @MappedSuperclass
public abstract class AbstractBakeryUnitDeliveryProduction extends AbstractIdentifiable implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@ManyToOne @NotNull protected BakeryUnitProduction bakeryUnitProduction;
	
	@Embedded protected SellDetails sellDetails = new SellDetails();

	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull protected BigDecimal discount = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull protected BigDecimal comission = BigDecimal.ZERO;
	
	public AbstractBakeryUnitDeliveryProduction(BakeryUnitProduction bakeryUnitProduction) {
		super();
		this.bakeryUnitProduction = bakeryUnitProduction;
	}
	
	
	
}
