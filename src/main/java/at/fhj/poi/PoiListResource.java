package at.fhj.poi;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PoiListResource extends ServerResource {

    @Get
    public String list() {
        return "list";
    }
    
}