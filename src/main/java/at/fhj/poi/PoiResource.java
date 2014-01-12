package at.fhj.poi;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PoiResource extends ServerResource {

    @Get
    public String represent() {
    	int id = Integer.valueOf(getRequest().getAttributes().get("id").toString());
        return "poi "+id;
    }
    
}