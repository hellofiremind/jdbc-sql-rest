package endpoint;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;

/**
 * Created by jamescross91 on 05/11/2015.
 */
public class QueryResource extends ServerResource {

    @Get
    public String represent() {
        return "Post your SQL queries to this endpoint";
    }

    @Post
    public Representation processQuery(Representation entity) throws IOException, JSONException {
        JSONObject json = new JsonRepresentation(entity).getJsonObject();

        if(!json.has("sql")) {
            return errorRepresentation("SQL parameter not supplied");
        }


    }

    private JsonRepresentation errorRepresentation(String errMessage) {
        JSONObject object = new JSONObject();
        try {
            object.put("message", errMessage);
            object.put("success", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JsonRepresentation(object);
    }
}
