package org.cyk.system.bakery.business.impl.integration;

import org.cyk.system.company.business.impl.CompanyBusinessLayer;
import org.cyk.system.root.business.impl.RootBusinessLayer;
import org.cyk.system.root.model.security.Installation;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;

public class ApplicationSetupBusinessIT extends AbstractBusinessIT {

    private static final long serialVersionUID = -6691092648665798471L;

    @Deployment
    public static Archive<?> createDeployment() {
        return createRootDeployment();
    } 
    
    @Override
    protected void businesses() {
    	installApplication();
    	
    	/*
    	Installation installation = CompanyBusinessLayer.getInstance().buildInstallation();
    	installation.setFaked(Boolean.TRUE);
    	applicationBusiness.install(installation);
    	*/
    	System.exit(0);
    }

    
    

}
