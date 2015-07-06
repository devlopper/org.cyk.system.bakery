package org.cyk.system.bakery.business.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.cyk.system.bakery.business.api.production.BakeryUnitDeliveryEmployeeProductionBusiness;
import org.cyk.system.bakery.business.api.production.BakeryUnitDeliveryResellerProductionBusiness;
import org.cyk.system.bakery.business.api.production.BakeryUnitProductionBusiness;
import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryEmployeeBusiness;
import org.cyk.system.bakery.business.api.structure.BakeryUnitDeliveryResellerBusiness;
import org.cyk.system.bakery.business.api.structure.EnergyTypeBusiness;
import org.cyk.system.bakery.model.production.BakeryUnitDeliveryEmployeeProduction;
import org.cyk.system.bakery.model.production.BakeryUnitDeliveryResellerProduction;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.system.bakery.model.structure.BakeryUnit;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryEmployee;
import org.cyk.system.bakery.model.structure.BakeryUnitDeliveryReseller;
import org.cyk.system.bakery.model.structure.EnergyType;
import org.cyk.system.company.business.api.structure.CompanyBusiness;
import org.cyk.system.company.business.impl.CompanyBusinessLayer;
import org.cyk.system.root.business.api.TypedBusiness;
import org.cyk.system.root.business.impl.AbstractBusinessLayer;
import org.cyk.system.root.business.impl.RootBusinessLayer;
import org.cyk.system.root.business.impl.RootRandomDataProvider;
import org.cyk.system.root.model.AbstractIdentifiable;
import org.cyk.utility.common.annotation.Deployment;
import org.cyk.utility.common.annotation.Deployment.InitialisationType;

@Singleton @Deployment(initialisationType=InitialisationType.EAGER,order=BakeryBusinessLayer.DEPLOYMENT_ORDER)
public class BakeryBusinessLayer extends AbstractBusinessLayer implements Serializable {

	public static final int DEPLOYMENT_ORDER = CompanyBusinessLayer.DEPLOYMENT_ORDER+1;
	private static final long serialVersionUID = -462780912429013933L;

	private static BakeryBusinessLayer INSTANCE;
	
	@Inject private CompanyBusiness companyBusiness;
	@Inject private BakeryUnitDeliveryEmployeeBusiness bakeryUnitDeliveryEmployeeBusiness;
	@Inject private BakeryUnitDeliveryResellerBusiness bakeryUnitDeliveryResellerBusiness;
	@Inject private BakeryUnitProductionBusiness bakeryUnitProductionBusiness;
	@Inject private BakeryUnitDeliveryEmployeeProductionBusiness bakeryUnitDeliveryEmployeeProductionBusiness;
	@Inject private BakeryUnitDeliveryResellerProductionBusiness bakeryUnitDeliveryResellerProductionBusiness;
	@Inject private EnergyTypeBusiness energyTypeBusiness;
		
	@Override
	protected void initialisation() {
		INSTANCE = this;
		super.initialisation();
		registerResourceBundle("org.cyk.system.bakery.model.resources.entity", getClass().getClassLoader());
		registerResourceBundle("org.cyk.system.bakery.model.resources.message", getClass().getClassLoader());
		registerResourceBundle("org.cyk.system.bakery.business.impl.resources.message", getClass().getClassLoader());
		
	}
	
	
	@Override
	protected void persistData() {
		create(new EnergyType(EnergyType.GAS, "Gaz"));
		create(new EnergyType(EnergyType.FUEL, "Carburant"));
		create(new EnergyType(EnergyType.WOOD, "Bois"));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerTypedBusinessBean(Map<Class<AbstractIdentifiable>, TypedBusiness<AbstractIdentifiable>> beansMap) {
		beansMap.put((Class)BakeryUnitDeliveryEmployee.class, (TypedBusiness)bakeryUnitDeliveryEmployeeBusiness);
		beansMap.put((Class)BakeryUnitDeliveryReseller.class, (TypedBusiness)bakeryUnitDeliveryResellerBusiness);
		beansMap.put((Class)BakeryUnitProduction.class, (TypedBusiness)bakeryUnitProductionBusiness);
		beansMap.put((Class)BakeryUnitDeliveryEmployeeProduction.class, (TypedBusiness)bakeryUnitDeliveryEmployeeProductionBusiness);
		beansMap.put((Class)BakeryUnitDeliveryResellerProduction.class, (TypedBusiness)bakeryUnitDeliveryResellerProductionBusiness);
    }
	
	/**/
	
	@Override
	protected void setConstants(){
    	
    }
	
	
	public static BakeryBusinessLayer getInstance() {
		return INSTANCE;
	}
	
	
	/**/
	
	protected void fakeTransactions(){
		BakeryUnit bakeryUnit = new BakeryUnit(companyBusiness.find().one());
		bakeryUnit.getProductionConfiguration().setEnergyType(energyTypeBusiness.find().all().iterator().next());
		bakeryUnit.getProductionConfiguration().setNextReportDate(new Date());
		bakeryUnit.getProductionConfiguration().setReportIntervalTimeDivisionType(RootBusinessLayer.getInstance().getTimeDivisionTypeDay());
		create(bakeryUnit);
		
		BakeryUnitDeliveryEmployee employee1 = RootRandomDataProvider.getInstance().actor(BakeryUnitDeliveryEmployee.class);
		employee1.setBakeryUnit(bakeryUnit);
		create(employee1);
		BakeryUnitDeliveryEmployee employee2 = RootRandomDataProvider.getInstance().actor(BakeryUnitDeliveryEmployee.class);
		employee1.setBakeryUnit(bakeryUnit);
		create(employee2);
		
		BakeryUnitDeliveryReseller reseller1 = RootRandomDataProvider.getInstance().actor(BakeryUnitDeliveryReseller.class);
		reseller1.setBakeryUnit(bakeryUnit);
		create(reseller1);
		BakeryUnitDeliveryReseller reseller2 = RootRandomDataProvider.getInstance().actor(BakeryUnitDeliveryReseller.class);
		reseller2.setBakeryUnit(bakeryUnit);
		create(reseller2);
		BakeryUnitDeliveryReseller reseller3 = RootRandomDataProvider.getInstance().actor(BakeryUnitDeliveryReseller.class);
		reseller3.setBakeryUnit(bakeryUnit);
		create(reseller3);
		/*
		BakeryUnitProduction bakeryUnitProduction = new BakeryUnitProduction();
		bakeryUnitProduction.setBakeryUnit(bakeryUnit);
		ProductionSpreadSheet productionSpreadSheet = new ProductionSpreadSheet();
		productionSpreadSheet.getPeriod().setFromDate(new Date());
		productionSpreadSheet.getPeriod().setToDate(new Date());
		bakeryUnitProduction.setProductionSpreadSheet(productionSpreadSheet);
		create(bakeryUnitProduction);
		
		create(new BakeryUnitDeliveryEmployeeProduction(bakeryUnitProduction,employee1));
		create(new BakeryUnitDeliveryEmployeeProduction(bakeryUnitProduction,employee2));
		
		create(new BakeryUnitDeliveryResellerProduction(bakeryUnitProduction,reseller1));
		create(new BakeryUnitDeliveryResellerProduction(bakeryUnitProduction,reseller2));
		create(new BakeryUnitDeliveryResellerProduction(bakeryUnitProduction,reseller3));
		*/
	}    
    
}
