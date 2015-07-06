package org.cyk.system.bakery.model.production;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractModelElement;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputNumber;

@Getter @Setter @NoArgsConstructor @Embeddable
public class TakingDetails extends AbstractModelElement implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@Column(name="taking_quantity",precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull @Input @InputNumber 
	private BigDecimal quantity = BigDecimal.ZERO;
	
	@Column(name="taking_unit_price",precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull @Input @InputNumber 
	private BigDecimal unitPrice = BigDecimal.ZERO;
	
	@Override
	public String getUiString() {
		return toString();
	}
	
}
