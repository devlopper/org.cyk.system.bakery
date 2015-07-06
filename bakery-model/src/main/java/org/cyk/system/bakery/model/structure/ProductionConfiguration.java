package org.cyk.system.bakery.model.structure;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.cyk.system.root.model.AbstractModelElement;
import org.cyk.system.root.model.time.TimeDivisionType;

@Getter @Setter @NoArgsConstructor @Embeddable
public class ProductionConfiguration extends AbstractModelElement implements Serializable {

	private static final long serialVersionUID = 1371797411549893368L;

	@ManyToOne @NotNull private EnergyType energyType;
	
	@Temporal(TemporalType.TIMESTAMP) private Date previousReportDate;
	
	@Temporal(TemporalType.TIMESTAMP) @NotNull private Date nextReportDate;
	
	@ManyToOne @NotNull private TimeDivisionType reportIntervalTimeDivisionType;
	
	public ProductionConfiguration(EnergyType energyType, Date nextReportDate,TimeDivisionType reportIntervalTimeDivisionType) {
		super();
		this.energyType = energyType;
		this.nextReportDate = nextReportDate;
		this.reportIntervalTimeDivisionType = reportIntervalTimeDivisionType;
	}
	
	@Override
	public String getUiString() {
		return toString();
	}

	
}
