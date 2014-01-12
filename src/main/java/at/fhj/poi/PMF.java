package at.fhj.poi;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * PersistenceManagerFactory singleton wrapper
 * 
 * @author mawi
 */
public final class PMF {
	
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
    
}