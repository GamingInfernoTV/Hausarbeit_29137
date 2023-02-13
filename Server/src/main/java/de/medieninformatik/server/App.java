package de.medieninformatik.server;

import de.medieninformatik.rest.DBMSRest;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */
public class App extends Application {
    private static final Set<Class<?>> classes = new HashSet<>();

    public App() {classes.add(DBMSRest.class);}

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}