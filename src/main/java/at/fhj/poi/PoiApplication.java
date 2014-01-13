package at.fhj.poi;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.routing.Router;

import at.fhj.poi.model.Poi;

public class PoiApplication extends Application {

	
	public PoiApplication() {
		super();
		setup();
	}
	
	public PoiApplication(Context context) {
		super(context);
		setup();
	}
	
	public void setup() {
		setStatusService(new PoiStatusService());
	}
	
    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        //router.attachDefault(IndexResource.class);
        router.attach("/", IndexResource.class);
        router.attach("/resources/poi", PoiListResource.class);
        router.attach("/resources/poi/{id}", PoiResource.class);
        router.attach("/demo/create", PoiDemoDataRessource.class);
        
        return router;
    }
    
}
