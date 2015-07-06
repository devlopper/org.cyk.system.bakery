package org.cyk.system.bakery.ui.web.primefaces.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.cyk.system.bakery.model.production.SellDetails;
import org.cyk.ui.api.model.AbstractOutputDetails;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputText;
import org.cyk.utility.common.annotation.user.interfaces.Sequence;
import org.cyk.utility.common.annotation.user.interfaces.Sequence.Direction;

@Getter @Setter
public abstract class AbstractProductionOutputDetails extends AbstractOutputDetails implements Serializable {
	
	private static final long serialVersionUID = -731657715703646576L;
	
	@Input @InputText protected String takingBreadQuantity;
	@Input @InputText @Sequence(direction=Direction.AFTER,field="takingBreadQuantity") protected String takingUnitPrice;
	
	@Input @InputText @Sequence(direction=Direction.AFTER,field="takingUnitPrice") protected String returnBreadQuantity;
	@Input @InputText @Sequence(direction=Direction.AFTER,field="returnBreadQuantity") protected String returnUnitPrice;
	
	@Input @InputText @Sequence(direction=Direction.AFTER,field="returnUnitPrice") protected String residualBreadQuantity;
	@Input @InputText @Sequence(direction=Direction.AFTER,field="residualBreadQuantity") protected String totalAmountPaid = "NULL";
	
	public AbstractProductionOutputDetails(SellDetails sellDetails,BigDecimal residualBreadQuantity) {
		this.takingBreadQuantity = numberBusiness.format(sellDetails.getTakingDetails().getQuantity());
		this.returnBreadQuantity = numberBusiness.format(sellDetails.getReturnDetails().getQuantity());
		this.residualBreadQuantity = StringUtils.defaultString(numberBusiness.format(residualBreadQuantity),"NULL");
		this.takingUnitPrice = numberBusiness.format(sellDetails.getTakingDetails().getUnitPrice());
		this.returnUnitPrice = numberBusiness.format(sellDetails.getReturnDetails().getUnitPrice());
		if(sellDetails.getReturnDetails().getAmountPaid()!=null)
			this.totalAmountPaid = StringUtils.defaultString(numberBusiness.format(sellDetails.getReturnDetails().getAmountPaid().getUser()),"NULL");
	}
	
}