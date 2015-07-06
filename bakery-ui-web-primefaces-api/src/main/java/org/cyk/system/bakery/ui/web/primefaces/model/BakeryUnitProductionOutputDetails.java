package org.cyk.system.bakery.ui.web.primefaces.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.utility.common.annotation.user.interfaces.Input;
import org.cyk.utility.common.annotation.user.interfaces.InputText;
import org.cyk.utility.common.annotation.user.interfaces.OutputSeperator;
import org.cyk.utility.common.annotation.user.interfaces.OutputSeperator.SeperatorLocation;
import org.cyk.utility.common.annotation.user.interfaces.Sequence;
import org.cyk.utility.common.annotation.user.interfaces.Sequence.Direction;
import org.cyk.utility.common.annotation.user.interfaces.Text;

@Getter @Setter
public class BakeryUnitProductionOutputDetails extends AbstractProductionOutputDetails implements Serializable {
	private static final long serialVersionUID = -1498269103849317057L;
	
	@Input @InputText private String manager,date;

	@OutputSeperator(label=@Text(value="raw.materials"))
	@Input @InputText private String flourInputQuantity;
	@Input @InputText private String flourOutputQuantity;
	@Input @InputText private String yeastQuantity;
	@Input @InputText private String improvingQuantity;
	@Input @InputText private String energyQuantity;
	@Input @InputText private String edibleIceQuantity;
	@Input @InputText private String workForceQuantity;
	
	@OutputSeperator(label=@Text(value="production"))
	@Input @InputText private String salableBreadQuantity;
	@OutputSeperator(label=@Text(value="model.entity.bakeryUnitCounter"),location=SeperatorLocation.AFTER)
	@Input @InputText private String rationQuantity;
	
	public BakeryUnitProductionOutputDetails(BakeryUnitProduction bakeryUnitProduction) {
		super(bakeryUnitProduction.getCounterProduction().getCounterSellDetails(), bakeryUnitProduction.getResidualBreadQuantity());
		this.manager = bakeryUnitProduction.getBakeryUnit().getCompany().getManager().getNames();
		this.date = timeBusiness.formatDate(bakeryUnitProduction.getProductionSpreadSheet().getPeriod().getFromDate());
		this.flourInputQuantity = numberBusiness.format(bakeryUnitProduction.getFlourInputQuantity());
		this.flourOutputQuantity = numberBusiness.format(bakeryUnitProduction.getFlourOutputQuantity());
		this.yeastQuantity = numberBusiness.format(bakeryUnitProduction.getYeastQuantity());
		this.improvingQuantity = numberBusiness.format(bakeryUnitProduction.getImprovingQuantity());
		this.energyQuantity = numberBusiness.format(bakeryUnitProduction.getEnergyQuantity());
		this.edibleIceQuantity = numberBusiness.format(bakeryUnitProduction.getEdibleIceQuantity());
		this.workForceQuantity = numberBusiness.format(bakeryUnitProduction.getWorkForceQuantity());
		this.salableBreadQuantity = numberBusiness.format(bakeryUnitProduction.getSalableBreadQuantity().getUser());
		this.rationQuantity = numberBusiness.format(bakeryUnitProduction.getRationQuantity());
	}
	
	//@OutputSeperator(label=@Text(value="production"))
	@Override @Sequence(direction=Direction.AFTER,field="rationQuantity")
	public String getTakingBreadQuantity() {
		return super.getTakingBreadQuantity();
	}
	/*
	@Override @Sequence(direction=Direction.AFTER,field="takingBreadQuantity")
	public String getTakingUnitPrice() {
		return super.getTakingUnitPrice();
	}
	
	@Override @Sequence(direction=Direction.AFTER,field="takingUnitPrice")
	public String getReturnBreadQuantity() {
		return super.getReturnBreadQuantity();
	}
	
	@Override @Sequence(direction=Direction.AFTER,field="returnBreadQuantity")
	public String getReturnUnitPrice() {
		return super.getReturnUnitPrice();
	}
	
	@Override @Sequence(direction=Direction.AFTER,field="returnUnitPrice")
	public String getResidualBreadQuantity() {
		return super.getResidualBreadQuantity();
	}
	
	@Override @Sequence(direction=Direction.AFTER,field="residualBreadQuantity")
	public String getTotalAmountPaid() {
		return super.getTotalAmountPaid();
	}*/
}