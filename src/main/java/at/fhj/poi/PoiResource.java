package at.fhj.poi;

import javax.jdo.PersistenceManager;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import at.fhj.poi.model.Poi;

public class PoiResource extends ServerResource {

    @Get("json")
    public Poi represent() {
    	long id = Long.valueOf(getRequest().getAttributes().get("id").toString());
    	//String id = getRequest().getAttributes().get("id").toString();
    	
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	try {
    		Poi poi = pm.getObjectById(Poi.class, id);
    		return poi;
    	} catch (Exception e) {
    		return null;
    	} finally {
    		pm.close();
    	}
        
    }
    
}