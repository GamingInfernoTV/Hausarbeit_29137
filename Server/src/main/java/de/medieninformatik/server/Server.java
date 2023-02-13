package de.medieninformatik.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date 30.01.2023
 * @author Carolin Baum m29137
 *
 * Server Klasse, sie dient zur Erstellung des Servers
 */
public class Server {
    private static final Logger LOGGER = Logger.getLogger("org.glassfish");

    /**
     * Methode zum Starten des {@link Server} auf der übergebenen URL
     * @param uri die übergebene URL auf welcher der Server läuft
     */
    public static void start(String uri) {
        try {
            LOGGER.setLevel(Level.ALL);
            URI baseURI = new URI(uri);
            ResourceConfig config = ResourceConfig.forApplicationClass(App.class);
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseURI, config);
            StaticHttpHandler httpHandler = new StaticHttpHandler("web");
            httpHandler.setFileCacheEnabled(false);
            ServerConfiguration serverConfiguration = server.getServerConfiguration();
            serverConfiguration.addHttpHandler(httpHandler, "/");

            if (!server.isStarted()) server.start();
            System.out.println("Beliebige Eingabe, um den Server zu stoppen.");
            System.in.read();
            server.shutdown();
        } catch (URISyntaxException | IOException e) {
            LOGGER.log(Level.SEVERE, "Fehler beim starten des Servers", e);
        }
    }

    public static void main(String[] args) {
        start("http://localhost:8080/rest");
    }
}
