package org.cyk.system.bakery.ui.web.primefaces;

import java.io.Serializable;

import javax.inject.Singleton;

import lombok.Getter;

import org.cyk.system.bakery.business.impl.BakeryBusinessLayer;
import org.cyk.ui.api.AbstractUserSession;
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
		
			
		return systemMenu;
	}
	
	
	
}
