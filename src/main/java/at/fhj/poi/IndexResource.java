package at.fhj.poi;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class IndexResource extends ServerResource {

    @Get
    public String represent() {
        return "hello, world";
    }
    
}