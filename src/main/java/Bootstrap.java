import endpoint.SQLApplication;
import lombok.SneakyThrows;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;

/**
 * Created by jamescross91 on 05/11/2015.
 */

public class Bootstrap {
    @SneakyThrows
    public static void main(String args[]) {
        Component component = new Component();
        component.getLogService().setLoggerName("net.asdf.WebComponent.www");
        component.getDefaultHost().attach("/api", new SQLApplication());

        // Attach the server connectors descriptors to the component
        component.getServers().add(startHttpServer(component, 2511));
        // Start the component, which will lead to start, at the end, the real connectors.
        component.start();
    }

    private static Server startHttpServer(Component component, Integer port) throws Exception {
        Server server = new Server(new Context(), Protocol.HTTP, port, component);

        return server;
    }
}
