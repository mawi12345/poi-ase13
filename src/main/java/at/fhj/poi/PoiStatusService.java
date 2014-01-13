package at.fhj.poi;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.service.StatusService;

public class PoiStatusService extends StatusService {
	
	
    /**
     * Returns a status for a given exception or error. By default it unwraps
     * the status of {@link ResourceException}. For other exceptions or errors,
     * it returns an {@link Status#SERVER_ERROR_INTERNAL} status.<br>
     * <br>
     * In order to customize the default behavior, this method can be
     * overridden.
     * 
     * @param throwable
     *            The exception or error caught.
     * @param request
     *            The request handled.
     * @param response
     *            The response updated.
     * @return The representation of the given status.
     */
    public Status getStatus(Throwable throwable, Request request,
            Response response) {
        Status result = null;

        if (throwable instanceof ResourceException) {
            ResourceException re = (ResourceException) throwable;

            if (re.getCause() != null) {
                // What is most interesting is the embedded cause
                result = new Status(re.getStatus(), re.getCause());
            } else {
                result = re.getStatus();
            }
        } else {
            result = new Status(Status.CLIENT_ERROR_BAD_REQUEST, throwable);
        }

        return result;
    }
    
    
    /**
     * Returns a representation for the given status.<br>
     * In order to customize the default representation, this method can be
     * overridden. It returns null by default.
     * 
     * @param status
     *            The status to represent.
     * @param request
     *            The request handled.
     * @param response
     *            The response updated.
     * @return The representation of the given status.
     */
    public Representation getRepresentation(Status status, Request request,
            Response response) {
        return new StringRepresentation("{\"error\": \""+status.getDescription()+"\"}\r\n", MediaType.APPLICATION_JSON);
    }
    
}