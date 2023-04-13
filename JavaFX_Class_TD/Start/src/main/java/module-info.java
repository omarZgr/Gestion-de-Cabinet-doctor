module com.application.start {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;

    opens com.application.start.Dashboard.Patient;
    opens com.application.start.Dashboard.Patient.Add;
    opens com.application.start.Dashboard.Patient.Modifiy;
    opens com.application.start.Dashboard.Patient.Remove;
    opens com.application.start.Dashboard.Patient.Afficher;

    opens com.application.start.Dashboard.User ;
    opens com.application.start.Dashboard.User.Add ;
    opens com.application.start.Dashboard.User.Modifiy ;
    opens com.application.start.Dashboard.User.Remove ;

    opens com.application.start.Dashboard.Consultation001 ;


    opens com.application.start to javafx.fxml;
    exports com.application.start;
}