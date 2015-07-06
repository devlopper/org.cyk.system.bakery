package org.cyk.system.bakery.ui.web.primefaces.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.ui.api.data.collector.form.AbstractFormModel;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputCalendar;
import org.cyk.utility.common.annotation.user.interfaces.InputCalendar.Format;

@Getter @Setter
public class BakeryUnitProductionQueryResultFormModel extends AbstractFormModel<BakeryUnitProduction> implements Serializable {

	private static final long serialVersionUID = -3328823824725030136L;

	@Input @InputCalendar(format=Format.DATE_SHORT) private Date date;
	
	@Override
	public void read() {
		super.read();
		date = identifiable.getProductionSpreadSheet().getPeriod().getFromDate();
	}
	
}
