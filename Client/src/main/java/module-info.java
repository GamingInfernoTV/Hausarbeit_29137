module de.medieninformatik.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.medieninformatik.common;
    requires jakarta.ws.rs;
    requires jakarta.annotation;
    requires jakarta.activation;
    requires jakarta.inject;
    requires org.json;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires jersey.media.json.jackson;
    requires org.glassfish.jaxb.runtime;
    requires jakarta.xml.bind;
    requires jersey.hk2;
    requires java.xml.bind;


    opens de.medieninformatik.client to jakarta.ws.rs;
    opens de.medieninformatik.client.gui to javafx.fxml;
    exports de.medieninformatik.client.gui;
}