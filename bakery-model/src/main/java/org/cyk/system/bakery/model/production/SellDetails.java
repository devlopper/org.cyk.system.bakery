package org.cyk.system.bakery.model.production;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractModelElement;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputNumber;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs.Layout;
import org.cyk.utility.common.annotation.user.interfaces.OutputSeperator;
import org.cyk.utility.common.annotation.user.interfaces.Text;

@Getter @Setter @NoArgsConstructor @Embeddable
public class SellDetails extends AbstractModelElement implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@Embedded @IncludeInputs(layout=Layout.VERTICAL)
	@OutputSeperator(label=@Text(value="model.entity.TakingDetails"))
	private TakingDetails takingDetails = new TakingDetails();
	
	@Column(name="sale_quantity",precision=10,scale=FLOAT_SCALE,nullable=false) @NotNull @Input @InputNumber 
	private BigDecimal quantity = BigDecimal.ZERO;
	
	@Embedded @IncludeInputs(layout=Layout.VERTICAL) 
	@OutputSeperator(label=@Text(value="model.entity.ReturnDetails"))
	private ReturnDetails returnDetails = new ReturnDetails();
	
	@Override
	public String getUiString() {
		return toString();
	}
	
}
