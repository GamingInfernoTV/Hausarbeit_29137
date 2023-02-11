module de.medieninformatik.common {
    requires org.json;
    requires jakarta.ws.rs;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens de.medieninformatik.common to com.fasterxml.jackson.databind;
    exports de.medieninformatik.common;
}