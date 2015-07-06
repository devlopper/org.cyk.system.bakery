package org.cyk.system.bakery.model.production;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractModelElement;
import org.cyk.system.root.model.ValueDetails;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputNumber;

@Getter @Setter @NoArgsConstructor @Embeddable
public class ReturnDetails extends AbstractModelElement implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@Column(name="return_quantity",precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull @Input @InputNumber 
	private BigDecimal quantity = BigDecimal.ZERO;
	
	@Column(name="return_unit_price",precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull @Input @InputNumber 
	private BigDecimal unitPrice = BigDecimal.ZERO;
	
	@AttributeOverrides(value={
			@AttributeOverride(name="user",column=@Column(name="return_amount_paid_user"))
			,@AttributeOverride(name="system",column=@Column(name="return_amount_paid_computed"))
			,@AttributeOverride(name="gap",column=@Column(name="return_amount_paid_gap"))
	})
	@Embedded private ValueDetails amountPaid = new ValueDetails();
	
	@Override
	public String getUiString() {
		return toString();
	}
	
}
