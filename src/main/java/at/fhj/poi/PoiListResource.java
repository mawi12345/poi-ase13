package at.fhj.poi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import at.fhj.poi.model.Poi;

public class PoiListResource extends ServerResource {

	private int size = 30;
	
	@Get
	public List<Poi> list() {
		int page = 0;
				
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Poi.class);
		Map<String, Object> queryParameters = new HashMap<String, Object>();

		if (getQueryValue("p") != null) {
			page = Integer.valueOf(getQueryValue("p"));
			getLogger().log(Level.INFO, "range: "+(page*size)+", "+((page+1)*size));
		}
		
		if (getQueryValue("creator") != null) {
			String creator = getQueryValue("creator");
			getLogger().log(Level.INFO, "creator: "+creator);
			query.setFilter("creator == creatorParam");
			query.declareParameters("String creatorParam");
			queryParameters.put("creatorParam", creator);
		}
		
		if (getQueryValue("category") != null) {
			String category = getQueryValue("category");
			getLogger().log(Level.INFO, "category: "+category);
			query.setFilter("category == categoryParam");
			query.declareParameters("String categoryParam");
			queryParameters.put("categoryParam", category);
		}
		
		if (getQueryValue("name") != null) {
			String name = getQueryValue("name");
			getLogger().log(Level.INFO, "name: "+name);
			query.setFilter("name == nameParam");
			query.declareParameters("String nameParam");
			queryParameters.put("nameParam", name);
		}
		
		query.setRange(page*size, (page+1)*size);
		query.setOrdering("key asc");
		try {
			@SuppressWarnings("unchecked")
			List<Poi> results = (List<Poi>) query.executeWithMap(queryParameters);
			return results;
		} catch (Exception e) {
    		throw new ResourceException(new Status(400), "Bad query", e);
		} finally {
			query.closeAll();
		}

	}
	
    @Post
    @Put
    public Poi add(Poi poi) {
    	getLogger().log(Level.INFO, "add: "+poi);
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	poi.validate();
    	try {
			pm.makePersistent(poi);
			return poi;
    	} catch (Exception e) {
    		throw new ResourceException(new Status(400), "POI not valid", e);
    	} finally {
			pm.close();
		}
    }

}