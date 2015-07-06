package org.cyk.system.bakery.ui.web.primefaces.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.model.production.AbstractBakeryUnitDeliveryProduction;
import org.cyk.ui.api.data.collector.form.AbstractFormModel;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs;

@Getter @Setter
public abstract class AbstractDeliveryProductionFormModel<DELIVERY_PRODUCTION extends AbstractBakeryUnitDeliveryProduction> extends AbstractFormModel<DELIVERY_PRODUCTION> implements Serializable {
	private static final long serialVersionUID = -731657715703646576L;
	
	@IncludeInputs
	protected BakeryUnitDeliveryProductionFormModel deliveryProductionFormModel = new BakeryUnitDeliveryProductionFormModel();

	@Override
	public void write() {
		super.write();
		identifiable.getSellDetails().getTakingDetails().setQuantity(deliveryProductionFormModel.getTakingBreadQuantity());
		identifiable.getSellDetails().getTakingDetails().setUnitPrice(deliveryProductionFormModel.getTakingUnitPrice());
		
		identifiable.getSellDetails().getReturnDetails().setQuantity(deliveryProductionFormModel.getResidualBreadQuantity());
		identifiable.getSellDetails().getReturnDetails().setUnitPrice(deliveryProductionFormModel.getReturnUnitPrice());
		identifiable.getSellDetails().getReturnDetails().getAmountPaid().setUser(deliveryProductionFormModel.getTotalAmountPaid());
		
	}
	
}