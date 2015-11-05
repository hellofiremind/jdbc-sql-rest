package endpoint;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Created by jamescross91 on 05/11/2015.
 */
public class QueryResource extends ServerResource {

    @Get
    public String represent() {
        return "Post your SQL queries to this endpoint";
    }

    @Post
    public Representation processQuery(Representation entity) {
        return null;
    }
}
