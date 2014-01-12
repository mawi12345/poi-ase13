package at.fhj.poi;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class PoiApplication extends Application {

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

        return router;
    }
}
