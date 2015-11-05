package endpoint;

import jdbc.Query;
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

        Query.execute(json.getString("sql"), resultSet -> {
            while(resultSet.next()) {
                float time = resultSet.getFloat("time");
                String da = resultSet.getString("da");
                String da_resolved = resultSet.getString("da_resolved");
                String ta = resultSet.getString("ta");
                String ta_resolved = resultSet.getString("ta_resolved");
                String ra = resultSet.getString("ra");
                String ra_resolved = resultSet.getString("ra_resolved");
                String signal_dbm = resultSet.getString("signal_dbm");
                String signal_percent = resultSet.getString("signal_percent");
                String node_id = resultSet.getString("node_id");
                String project_tag = resultSet.getString("project_tag");
            }
            return null;
        });

        return errorRepresentation("oops");
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
