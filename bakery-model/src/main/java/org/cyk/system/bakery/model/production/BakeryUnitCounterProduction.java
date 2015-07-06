package org.cyk.system.bakery.model.production;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractModelElement;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs;
import org.cyk.utility.common.annotation.user.interfaces.IncludeInputs.Layout;

@Getter @Setter @NoArgsConstructor @Embeddable
public class BakeryUnitCounterProduction extends AbstractModelElement implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@Embedded @IncludeInputs(layout=Layout.VERTICAL) private SellDetails counterSellDetails = new SellDetails();
	
	@Override
	public String getUiString() {
		return toString();
	}
	
}
