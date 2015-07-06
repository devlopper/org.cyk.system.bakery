package org.cyk.system.bakery.ui.web.primefaces.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.ui.api.data.collector.form.AbstractFormModel;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputNumber;
import org.cyk.utility.common.annotation.user.interfaces.OutputSeperator;
import org.cyk.utility.common.annotation.user.interfaces.Text;

@Getter @Setter
public  class BakeryUnitProductionFormModel extends AbstractFormModel<BakeryUnitProduction> implements Serializable {
	private static final long serialVersionUID = -731657715703646576L;
	
	@OutputSeperator(label=@Text(value="raw.materials"))
	@NotNull @Input @InputNumber private BigDecimal flourInputQuantity;
	@NotNull @Input @InputNumber private BigDecimal flourOutputQuantity;
	@NotNull @Input @InputNumber private BigDecimal yeastQuantity;
	@NotNull @Input @InputNumber private BigDecimal improvingQuantity;
	@NotNull @Input @InputNumber private BigDecimal energyQuantity;
	@NotNull @Input @InputNumber private BigDecimal edibleIceQuantity;
	@NotNull @Input @InputNumber private BigDecimal workForceQuantity;
	
	@OutputSeperator(label=@Text(value="production"))
	@NotNull @Input @InputNumber private BigDecimal salableBreadQuantity;
	@NotNull @Input @InputNumber private BigDecimal rationQuantity;
	
	@IncludeInputs @OutputSeperator(label=@Text(value="model.entity.bakeryUnitCounter"))
	private BakeryUnitDeliveryProductionFormModel deliveryProductionFormModel = new BakeryUnitDeliveryProductionFormModel();

	@Override
	public void write() {
		super.write();
		identifiable.getSalableBreadQuantity().setUser(salableBreadQuantity);
	}
	
}