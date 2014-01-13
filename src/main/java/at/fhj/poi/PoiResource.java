package at.fhj.poi;

import javax.jdo.PersistenceManager;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import at.fhj.poi.model.Poi;

public class PoiResource extends ServerResource {
	
	public long getId() {
    	try {
    		return Long.valueOf(getRequest().getAttributes().get("id").toString());
    	} catch (Exception e) {
    		throw new ResourceException(new Status(400), "invalid POI id", e);
    	}
	}
	
    @Get("json")
    public Poi represent() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	try {  
    		Poi poi = pm.getObjectById(Poi.class, getId());
    		return poi;
    	} catch (Exception e) {
    		if (e instanceof ResourceException) throw e;
    		throw new ResourceException(new Status(404), "POI not found", e);
    	} finally {
    		pm.close();
    	}
    }
    
    @Post
    public Poi update(Poi updatedPoi) {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	updatedPoi.validate();
    	try {
    		Poi poi = pm.getObjectById(Poi.class, getId());
    		poi.setCategory(updatedPoi.getCategory());
    		poi.setCreator(updatedPoi.getCreator());
    		poi.setDescription(updatedPoi.getDescription());
    		poi.setLatitude(updatedPoi.getLatitude());
    		poi.setLongitude(updatedPoi.getLongitude());
    		poi.setName(updatedPoi.getName());
    		poi.validate();
	    	return poi;
    	} catch (Exception e) {
    		if (e instanceof ResourceException) throw e;
    		throw new ResourceException(new Status(400), "POI not found", e);
    	} finally {
    		pm.close();
    	}
    }
    
    @Delete
    public Poi remove() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	try {
    		Poi poi = pm.getObjectById(Poi.class, getId());
    		pm.deletePersistent(poi);
    		throw new ResourceException(new Status(204));
    	} catch (Exception e) {
    		if (e instanceof ResourceException) throw e;
    		throw new ResourceException(new Status(404), "POI not found", e);
    	} finally {
    		pm.close();
    	}
    }
}