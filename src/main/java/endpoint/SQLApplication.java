package endpoint;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;

import java.util.HashSet;

/**
 * Created by jamescross91 on 05/11/2015.
 */
public class SQLApplication extends Application {
    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());

        CorsFilter corsFilter = new CorsFilter(getContext(), router);
        HashSet<String> allowedOrigins = new HashSet<>();
        allowedOrigins.add("*");
        corsFilter.setAllowedOrigins(allowedOrigins);

        //SQL query endpoint
        router.attach("query", QueryResource.class);

        return corsFilter;
    }
}
