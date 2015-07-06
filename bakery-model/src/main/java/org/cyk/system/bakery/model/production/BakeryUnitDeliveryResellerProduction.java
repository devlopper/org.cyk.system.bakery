package org.cyk.system.bakery.model.production;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryReseller;
import org.cyk.utility.common.annotation.ModelBean;
import org.cyk.utility.common.annotation.ModelBean.CrudStrategy;

@Getter @Setter @NoArgsConstructor @Entity @ModelBean(crudStrategy=CrudStrategy.BUSINESS)
public class BakeryUnitDeliveryResellerProduction extends AbstractBakeryUnitDeliveryProduction implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@ManyToOne @NotNull private BakeryUnitDeliveryReseller bakeryUnitDeliveryReseller;

	public BakeryUnitDeliveryResellerProduction(BakeryUnitProduction bakeryUnitProduction,BakeryUnitDeliveryReseller bakeryUnitDeliveryReseller) {
		super(bakeryUnitProduction);
		this.bakeryUnitDeliveryReseller = bakeryUnitDeliveryReseller;
	}
	
	
	
}
