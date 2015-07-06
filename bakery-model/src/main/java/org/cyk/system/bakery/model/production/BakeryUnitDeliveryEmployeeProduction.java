package org.cyk.system.bakery.model.production;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryEmployee;
import org.cyk.utility.common.annotation.ModelBean;
import org.cyk.utility.common.annotation.ModelBean.CrudStrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Entity @ModelBean(crudStrategy=CrudStrategy.BUSINESS)
public class BakeryUnitDeliveryEmployeeProduction extends AbstractBakeryUnitDeliveryProduction implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@ManyToOne @NotNull private BakeryUnitDeliveryEmployee bakeryUnitDeliveryEmployee;
	
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal salary = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal transportFees = BigDecimal.ZERO;
	
	public BakeryUnitDeliveryEmployeeProduction(BakeryUnitProduction bakeryUnitProduction,BakeryUnitDeliveryEmployee bakeryUnitDeliveryEmployee) {
		super(bakeryUnitProduction);
		this.bakeryUnitDeliveryEmployee = bakeryUnitDeliveryEmployee;
	}
	
	
	
}
