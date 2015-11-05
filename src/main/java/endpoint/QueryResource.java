package endpoint;

import com.google.gson.Gson;
import jdbc.Pcap;
import jdbc.Query;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        List<Pcap> list = Query.execute(json.getString("sql"), resultSet -> {
            ArrayList<Pcap> pcapList = new ArrayList<Pcap>();
            while (resultSet.next()) {
                Pcap pcap = new Pcap();
                pcap.init(resultSet);
                pcapList.add(pcap);
            }

            return pcapList;
        });

        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        return new JsonRepresentation(jsonString);
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
