package org.cyk.system.bakery.model.structure;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractModelElement;

@Getter @Setter @NoArgsConstructor @Embeddable
public class BakeryUnitCounter extends AbstractModelElement implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@Override
	public String getUiString() {
		return toString();
	}

}
