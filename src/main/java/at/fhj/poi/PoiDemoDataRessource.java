package at.fhj.poi;

import java.util.Random;

import javax.jdo.PersistenceManager;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import at.fhj.poi.model.Poi;

public class PoiDemoDataRessource extends ServerResource {

    @Get
    public String createNewDemoPoi() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Random rand = new Random();
    	try {
    		Poi poi = new Poi();
    		poi.setName("Demo POI "+rand.nextInt(1000));
    		poi.setLatitude(rand.nextDouble() * 100);
    		poi.setLongitude(rand.nextDouble() * 100);
    		poi.setCategory("Demo Data");
    		poi.setCreator("demo");   
    		poi.setDescription("this poi has been created by the demo data generator");
    		pm.makePersistent(poi);
    		return poi.getName()+" created";
    	} catch (Exception e) {
    		return "could not create demo poi";
    	} finally {
    		pm.close();
    	}
        
    }
    
}