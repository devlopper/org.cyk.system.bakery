package org.cyk.system.bakery.ui.web.primefaces.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputNumber;

@Getter @Setter
public  class BakeryUnitDeliveryProductionFormModel implements Serializable {
	
	private static final long serialVersionUID = -731657715703646576L;
	
	@NotNull @Input @InputNumber private BigDecimal takingBreadQuantity;
	@NotNull @Input @InputNumber private BigDecimal returnBreadQuantity;
	@NotNull @Input @InputNumber private BigDecimal residualBreadQuantity;
	@NotNull @Input @InputNumber private BigDecimal takingUnitPrice;
	@NotNull @Input @InputNumber private BigDecimal returnUnitPrice;
	@NotNull @Input @InputNumber private BigDecimal totalAmountPaid;
	
	
}