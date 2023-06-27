import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

public class AppointmentController extends HelloApplication {

    // JavaFX controls (inheritance)
    @FXML
    private Button addButtonAppointment;
    @FXML
    private Button searchButtonAppointment;
    @FXML
    private Button updateButtonAppointment;
    @FXML
    private Button deleteButtonAppointment;
    @FXML
    private Button clearButtonAppointment;
    @FXML
    private Button backButtonAppointment;
    @FXML
    private TextArea actionStatusAppointment;
    @FXML
    private TextField idFieldAppointment;
    @FXML
    private TextField AppointmentIDField;
    @FXML
    private TextField DoctorIDAppointment;
    @FXML
    private TextField AppointmentReasonField;
    @FXML
    private TextField SessionTimeField;
    @FXML
    private TextField AppointmentTypeField;
    @FXML
    private DatePicker AppointmentDateField;

    // Instance variables
    private List<Appointment> searchList = new ArrayList<>();
    private String searchString = "";

    // Initialization method (abstraction)
    public void initialize() {
        addButtonAppointment.setOnAction(e -> createAppointment());
        updateButtonAppointment.setOnAction(e -> updateAppointment());
        deleteButtonAppointment.setOnAction(e -> deleteAppointment());
        backButtonAppointment.setOnAction(e -> {
            try {
                changeScene("treatment-course.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        searchButtonAppointment.setOnAction(e -> {
            Appointment a = searchAppointment();
            if (a != null) {
                // Updating JavaFX controls based on search result
                idFieldAppointment.setText(a.getPatientID());
                AppointmentIDField.setText(a.getAppointmentID());
                DoctorIDAppointment.setText(a.getDoctorID());
                AppointmentReasonField.setText(a.getAppointmentReason());
                SessionTimeField.setText(a.getAppointmentTime());
                AppointmentTypeField.setText(a.getAppointmentType());
                AppointmentDateField.setValue(a.getAppointmentDate());
            } else {
                // Clearing JavaFX controls if search result is null
                idFieldAppointment.setText("");
                AppointmentIDField.setText("");
                DoctorIDAppointment.setText("");
                AppointmentReasonField.setText("");
                SessionTimeField.setText("");
                AppointmentTypeField.setText("");
                AppointmentDateField.setValue(null);
            }
        });
        clearButtonAppointment.setOnAction(e -> {
            // Clearing JavaFX controls
            idFieldAppointment.setText("");
            AppointmentIDField.setText("");
            DoctorIDAppointment.setText("");
            AppointmentReasonField.setText("");
            SessionTimeField.setText("");
            AppointmentTypeField.setText("");
            AppointmentDateField.setValue(null);
        });
    }

    // Method for creating an appointment (abstraction)
    private void createAppointment() {
        LocalDate date = null;
        date = AppointmentDateField.getValue();
        if (date == null) {
            actionStatusAppointment.setText(actionStatusAppointment.getText() + "\n" + "Error. Value type is wrong.");
            actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
            return;
        }
        String patientid = idFieldAppointment.getText();
        String appointmentid = AppointmentIDField.getText();
        String doctorid = DoctorIDAppointment.getText();
        String reason = AppointmentReasonField.getText();
        String time = SessionTimeField.getText();
        String type = AppointmentTypeField.getText();

        Appointment a = new Appointment(appointmentid, patientid, doctorid, reason, type, date, time);
        searchString = appointmentid;

        if (keyExists(searchString)) {
            actionStatusAppointment.setText(actionStatusAppointment.getText() + "\nRecord Exists. Please click update to modify the appointment details.");
            actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
        } else {
            CSVAppointmentHandler csv = new CSVAppointmentHandler();
            csv.createAppointment(a);
            actionStatusAppointment.setText(actionStatusAppointment.getText() + "\nAdded Appointment " + appointmentid + " for Patient " + patientid + "\nThe appointment is in " + DatesDuration(date));
            actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
        }
    }

    // Method for updating an appointment (abstraction)
    private void updateAppointment() {
        LocalDate date = null;
        date = AppointmentDateField.getValue();
        if (date == null) {
            actionStatusAppointment.setText(actionStatusAppointment.getText() + "\n" + "Error. Value type is wrong.");
            actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
            return;
        }
        String patientid = idFieldAppointment.getText();
        String appointmentid = AppointmentIDField.getText();
        String doctorid = DoctorIDAppointment.getText();
        String reason = AppointmentReasonField.getText();
        String time = SessionTimeField.getText();
        String type = AppointmentTypeField.getText();

        Appointment a = new Appointment(appointmentid, patientid, doctorid, reason, type, date, time);

        CSVAppointmentHandler csv = new CSVAppointmentHandler();
        csv.updateappointment2(a, Appointment.fileName);

        actionStatusAppointment.setText(actionStatusAppointment.getText() + "\nUpdated Appointment " + appointmentid + " for Patient " + patientid + "\nThe appointment is in " + DatesDuration(date));
        actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
    }

    // Method for deleting an appointment (abstraction)
    private void deleteAppointment() {
        CSVAppointmentHandler csv = new CSVAppointmentHandler();
        String id = AppointmentIDField.getText().equals("") ? "-" : AppointmentIDField.getText();
        csv.deleteappointment(id, Appointment.fileName);
        actionStatusAppointment.setText(actionStatusAppointment.getText() + "\n" + "Deleted appointment");
        actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
    }

    // Method for checking if a key exists in the appointment list (abstraction)
    private boolean keyExists(String key) {
        CSVAppointmentHandler csv = new CSVAppointmentHandler();
        searchList = csv.readCSV(Appointment.fileName);
        for (Appointment a : searchList) {
            if (a.getAppointmentID().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // Method for calculating the duration between dates (abstraction)
    private String DatesDuration(LocalDate date) {
        Period findDuration = Period.between(LocalDate.now(), date);
        String duration = findDuration.getDays() + " Days";
        return duration;
    }

    // Method for searching an appointment (abstraction)
    private Appointment searchAppointment() {
        searchString = AppointmentIDField.getText();
        CSVAppointmentHandler csv = new CSVAppointmentHandler();
        searchList = csv.readCSV(Appointment.fileName);
        for (Appointment a : searchList) {
            if (a.getAppointmentID().contentEquals(searchString)) {
                actionStatusAppointment.setText(actionStatusAppointment.getText() + "\nFound: " + searchString);
                actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
                return new Appointment(a.getAppointmentID(), a.getPatientID(), a.getDoctorID(), a.getAppointmentReason(), a.getAppointmentType(), a.getAppointmentDate(), a.getAppointmentTime());
            } else {
                // Clearing JavaFX controls if search result is null
                idFieldAppointment.setText("");
                AppointmentIDField.setText("");
                DoctorIDAppointment.setText("");
                AppointmentReasonField.setText("");
                SessionTimeField.setText("");
                AppointmentTypeField.setText("");
                AppointmentDateField.setValue(null);
            }
        }
        actionStatusAppointment.setText(actionStatusAppointment.getText() + "\n" + searchString + " Not Found.");
        actionStatusAppointment.positionCaret(actionStatusAppointment.getLength());
        return null;
    }
}



