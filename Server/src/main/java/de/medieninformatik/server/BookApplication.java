package de.medieninformatik.server;

import de.medieninformatik.rest.DatabaseRest;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * @date 30.11.2023
 * @author Carolin Baum m29137
 *
 */
public class BookApplication extends Application {
    //TODO Klasse BookApplication umbenennen
    private static final Set<Class<?>> classes = new HashSet<>();

    public BookApplication() {classes.add(DatabaseRest.class);}

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
