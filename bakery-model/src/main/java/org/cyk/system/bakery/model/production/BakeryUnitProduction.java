package org.cyk.system.bakery.model.production;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.cyk.system.company.model.product.resell.ResellerProductSale;
import org.cyk.system.company.model.production.Production;
import org.cyk.system.root.model.AbstractIdentifiable;
import org.cyk.system.root.model.ValueDetails;
import org.cyk.utility.common.annotation.ModelBean;
import org.cyk.utility.common.annotation.ModelBean.CrudStrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Entity @ModelBean(crudStrategy=CrudStrategy.BUSINESS)
public class BakeryUnitProduction extends AbstractIdentifiable implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@OneToOne @NotNull private Production production;
	
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal flourInputQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal flourOutputQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal yeastQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal improvingQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal energyQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal edibleIceQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal workForceQuantity = BigDecimal.ZERO;
	
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal rationQuantity = BigDecimal.ZERO;
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal residualBreadQuantity = BigDecimal.ZERO;
	
	@AttributeOverrides(value={
			@AttributeOverride(name="user",column=@Column(name="salable_bread_quantity_user"))
			,@AttributeOverride(name="system",column=@Column(name="salable_bread_quantity_computed"))
			,@AttributeOverride(name="gap",column=@Column(name="salable_bread_quantity_gap"))
	})
	@Embedded private ValueDetails salableBreadQuantity = new ValueDetails();
	
	@Column(precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull private BigDecimal observations = BigDecimal.ZERO;
	
	@Embedded private ResellerProductSale productSale = new ResellerProductSale();

}
