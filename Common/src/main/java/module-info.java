module de.medieninformatik.common {
    requires org.json;
    requires jakarta.ws.rs;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports de.medieninformatik.common.query;
    opens de.medieninformatik.common.query to com.fasterxml.jackson.databind;
    exports de.medieninformatik.common;
    opens de.medieninformatik.common to com.fasterxml.jackson.databind;
    exports de.medieninformatik.common.library;
    opens de.medieninformatik.common.library to com.fasterxml.jackson.databind;
}