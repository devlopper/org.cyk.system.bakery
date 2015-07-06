package org.cyk.system.bakery.ui.web.primefaces;

import java.io.Serializable;

import javax.inject.Singleton;

import lombok.Getter;

import org.cyk.system.bakery.business.impl.BakeryBusinessLayer;
import org.cyk.system.bakery.model.production.BakeryUnitProduction;
import org.cyk.ui.api.AbstractUserSession;
import org.cyk.ui.api.command.UICommandable;
import org.cyk.ui.api.command.menu.SystemMenu;
import org.cyk.ui.web.api.security.shiro.WebEnvironmentAdapter;
import org.cyk.ui.web.api.security.shiro.WebEnvironmentAdapter.SecuredUrlProvider;
import org.cyk.ui.web.primefaces.AbstractPrimefacesManager;
import org.cyk.utility.common.annotation.Deployment;
import org.cyk.utility.common.annotation.Deployment.InitialisationType;

@Singleton @Deployment(initialisationType=InitialisationType.EAGER,order=BakeryWebManager.DEPLOYMENT_ORDER) @Getter
public class BakeryWebManager extends AbstractPrimefacesManager implements Serializable {

	public static final int DEPLOYMENT_ORDER = BakeryBusinessLayer.DEPLOYMENT_ORDER+1;
	private static final long serialVersionUID = 7231721191071228908L;

	private static BakeryWebManager INSTANCE;
	
	@Override
	protected void initialisation() {
		INSTANCE = this;
		identifier = "bakery";
		super.initialisation();  
		
		WebEnvironmentAdapter.SECURED_URL_PROVIDERS.add(new SecuredUrlProvider() {
			
			@Override
			public void provide() {
				
			}
		});
	}
		
	public static BakeryWebManager getInstance() {
		return INSTANCE;
	}
	
	@Override
	public SystemMenu systemMenu(AbstractUserSession userSession) {
		SystemMenu systemMenu = new SystemMenu();
		
		addBusinessMenu(systemMenu,bakeryUnitProductionCommandables(userSession));
		return systemMenu;
	}
	
	public UICommandable bakeryUnitProductionCommandables(AbstractUserSession userSession){
		UICommandable production = uiProvider.createCommandable("model.entity.bakeryUnitProduction", null),p;
		production.getChildren().add(p=menuManager.crudOne(BakeryUnitProduction.class, null));
		p.setLabel(uiManager.text("create"));
		production.getChildren().add(p=menuManager.crudMany(BakeryUnitProduction.class, null) /*uiProvider.createCommandable("list", null, "bakeryUnitProductionListView")*/);
		p.setLabel(uiManager.text("list"));
		return production;
	}
	
	/*
	public Collection<UICommandable> bakeryUnitProductionContextCommandables(AbstractUserSession userSession){
		Collection<UICommandable> commandables = new ArrayList<>();
		//commandables.add(uiProvider.createCommandable("dashboard", null, "productionDashBoardView"));
		commandables.add(menuManager.crudOne(BakeryUnitProduction.class, null));
		commandables.add(menuManager.crudOne(BakeryUnitDeliveryEmployeeProduction.class, null));
		//commandables.add(menuManager.crudOne(BakeryUnitDeliveryResellerProduction.class, null));
		return commandables;
	}*/
	
}
