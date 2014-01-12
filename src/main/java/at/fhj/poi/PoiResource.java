package at.fhj.poi;

import javax.jdo.PersistenceManager;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import at.fhj.poi.model.Poi;

public class PoiResource extends ServerResource {
	
    @Get("json")
    public Poi represent() {
		long id = Long.valueOf(getRequest().getAttributes().get("id").toString());    	
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
    
    @Post
    public Poi update(Poi updatedPoi) {
		long id = Long.valueOf(getRequest().getAttributes().get("id").toString());    	
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	try {
    		Poi poi = pm.getObjectById(Poi.class, id);
	    	if (updatedPoi.getCategory() != null &&
	    		updatedPoi.getCategory().length() > 0)
	    		poi.setCategory(updatedPoi.getCategory());
	    	if (updatedPoi.getCreator() != null &&
	    		updatedPoi.getCreator().length() > 0)
	    		poi.setCreator(updatedPoi.getCreator());
	    	if (updatedPoi.getDescription() != null)
	    		poi.setDescription(updatedPoi.getDescription());
	    	if (updatedPoi.getLatitude() != null)
	    		poi.setLatitude(updatedPoi.getLatitude());
	    	if (updatedPoi.getLongitude() != null)
	    		poi.setLongitude(updatedPoi.getLongitude());
	    	if (updatedPoi.getName() != null && updatedPoi.getName().length() > 0)
	    		poi.setName(updatedPoi.getName());
	    	return poi;
    	} catch (Exception e) {
    		return null;
    	} finally {
    		pm.close();
    	}
    }
    
    @Delete
    public String remove() {
		long id = Long.valueOf(getRequest().getAttributes().get("id").toString());    	
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	try {
    		Poi poi = pm.getObjectById(Poi.class, id);
    		pm.deletePersistent(poi);
    		return "OK";
    	} catch (Exception e) {
    		return "ERROR "+e.getMessage();
    	} finally {
    		pm.close();
    	}
    }
}