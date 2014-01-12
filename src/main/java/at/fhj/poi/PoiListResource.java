package at.fhj.poi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.jdo.PersistenceManager;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.jdo.Query;
import at.fhj.poi.model.Poi;

public class PoiListResource extends ServerResource {

	@Get
	public List<Poi> list() {
		int size = 5;
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
		try {
			List<Poi> results = (List<Poi>) query.executeWithMap(queryParameters);
			return results;
		} finally {
			query.closeAll();
		}

	}

}