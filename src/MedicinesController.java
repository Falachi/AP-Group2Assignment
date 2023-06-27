import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class MedicinesController extends HelloApplication{

    // JavaFX controls (inheritance)
    @FXML
    private Button addButtonMedicine;
    @FXML
    private Button searchButtonMedicine;
    @FXML
    private Button updateButtonMedicine;
    @FXML
    private Button deleteButtonMedicine;
    @FXML
    private Button clearButtonMedicine;
    @FXML
    private Button backButtonMedicine;
    @FXML
    private TextArea actionStatusMedicine;
    @FXML
    private TextField idFieldMedicine;
    @FXML
    private TextField medicineNameField;
    @FXML
    private TextField medicineIDField;
    @FXML
    private TextField dosageField;
    @FXML
    private TextField durationField;
    @FXML
    private TextArea sideEffectsField;
    @FXML
    private DatePicker datePrescribedField;

    // Instance variables
    private List<Medicine> searchList = new ArrayList<>();
    private String searchString = "";
    public MedicinesController() {
    }

    // Initialization method (abstraction)
    public void initialize() {
        addButtonMedicine.setOnAction(e -> createMedicine());
        updateButtonMedicine.setOnAction(e -> updateMedicine());
        deleteButtonMedicine.setOnAction(e -> deleteMedicine());
        this.backButtonMedicine.setOnAction((e) -> {
            try {
                changeScene("procedure-medicine.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        clearButtonMedicine.setOnAction(e -> {
            // Clearing JavaFX controls
            idFieldMedicine.setText("");
            medicineNameField.setText("");
            medicineIDField.setText("");
            dosageField.setText("");
            durationField.setText("");
            sideEffectsField.setText("");
            datePrescribedField.setValue(null);
        });
        searchButtonMedicine.setOnAction(e -> {
            Medicine med = searchMedicine();
            if (med != null) {
                // Updating JavaFX controls based on search result
                idFieldMedicine.setText(med.getMedicineID());
                medicineNameField.setText(med.getMedicineName());
                medicineIDField.setText(med.getMedicineID());
                dosageField.setText(Integer.toString(med.getDosage()));
                durationField.setText(Double.toString(med.getDuration()));
                sideEffectsField.setText(med.getSideEffect());
                datePrescribedField.setValue(med.getDatePrescribed());
            } else {
                // Clearing JavaFX controls if search result is null
                idFieldMedicine.setText("");
                medicineNameField.setText("");
                medicineIDField.setText("");
                dosageField.setText("");
                durationField.setText("");
                sideEffectsField.setText("");
                datePrescribedField.setValue(null);
            }
        });

    }

    // Method for creating a Medicine (abstraction)
    private void createMedicine() {
        LocalDate datePrescribed = null;
        int dosage = 0;
        double duration = 0;
        try {
            dosage = Integer.parseInt(dosageField.getText());
            duration = Double.parseDouble(durationField.getText());
        } catch (NumberFormatException e) {
            actionStatusMedicine.setText(actionStatusMedicine.getText() + "\n" + "Error. Value type is wrong.");
            actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
            return;
        }
        datePrescribed = datePrescribedField.getValue();
        if (datePrescribed == null) {
            actionStatusMedicine.setText(actionStatusMedicine.getText() + "\n" + "Error. Value type is wrong.");
            actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
            return;
        }
        String patientID = idFieldMedicine.getText();
        String medicineID = medicineIDField.getText();
        String medicineName = medicineNameField.getText();
        String sideEffect = sideEffectsField.getText();

        Medicine medicine = new Medicine(patientID, medicineID, medicineName, dosage, duration, sideEffect, datePrescribed);
        if (keyExists(searchString)) {
            actionStatusMedicine.setText(actionStatusMedicine.getText() + "\nRecord Exists. Please click update to modify the treatment details.");
            actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
        } else {
            CSVMedicine csv = new CSVMedicine();
            csv.create(medicine);
            actionStatusMedicine.setText(actionStatusMedicine.getText() + "\nAdded Medicine " + medicineID);
            actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
        }
    }

    // Method for updating a Medicine (abstraction)
    private void updateMedicine() {
        LocalDate datePrescribed = null;
        int dosage = 0;
        double duration = 0;
        try {
            dosage = Integer.parseInt(dosageField.getText());
            duration = Double.parseDouble(durationField.getText());
        } catch (NumberFormatException e) {
            actionStatusMedicine.setText(actionStatusMedicine.getText() + "\n" + "Error. Value type is wrong.");
            actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
            return;
        }
        datePrescribed = datePrescribedField.getValue();
        if (datePrescribed == null) {
            actionStatusMedicine.setText(actionStatusMedicine.getText() + "\n" + "Error. Value type is wrong.");
            actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
            return;
        }
        String patientID = idFieldMedicine.getText();
        String medicineID = medicineIDField.getText();
        String medicineName = medicineNameField.getText();
        String sideEffect = sideEffectsField.getText();

        Medicine medicine = new Medicine(patientID, medicineID,medicineName, dosage, duration, sideEffect, datePrescribed);
        CSVMedicine csv = new CSVMedicine();
        csv.update(medicine, Medicine.fileName);
        actionStatusMedicine.setText(actionStatusMedicine.getText() + "\nUpdated Medicine: " + medicineID);
        actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
    }

    // Method for deleting a Medicine (abstraction)
    private void deleteMedicine() {
        CSVMedicine csv = new CSVMedicine();
        String id = idFieldMedicine.getText() == "" ? "-" : idFieldMedicine.getText();
        System.out.println(id);
        csv.delete(id, Medicine.fileName);
        actionStatusMedicine.setText(actionStatusMedicine.getText() + "\nMedicine Deleted");
        actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
    }

    // Method for checking if a key exists in the medicine list (abstraction)
    private boolean keyExists(String key) {
        CSVMedicine csv = new CSVMedicine();
        searchList = csv.readCSV(Medicine.fileName);
        for (Medicine med : searchList) {
            if (med.getPatientID().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // Method for searching a medicine (abstraction)
    private Medicine searchMedicine() {
        searchString = idFieldMedicine.getText();
        CSVMedicine csv = new CSVMedicine();
        searchList = csv.readCSV(Medicine.fileName);
        for (Medicine med : searchList) {
            if ((med.getPatientID().contentEquals(searchString))) {
                actionStatusMedicine.setText(actionStatusMedicine.getText() + "\nFound: " + searchString);
                actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
                return new Medicine(med.getPatientID(),med.getMedicineID(),med.getMedicineName(),med.getDosage(),med.getDuration(),med.getSideEffect(),med.getDatePrescribed());
            } else {
                // Clearing JavaFX controls if search result is null
                idFieldMedicine.setText("");
                medicineNameField.setText("");
                medicineIDField.setText("");
                dosageField.setText("");
                durationField.setText("");
                sideEffectsField.setText("");
                datePrescribedField.setValue(null);
            }
        }
        actionStatusMedicine.setText(actionStatusMedicine.getText() + "\n" + searchString + " Not Found.");
        actionStatusMedicine.positionCaret(actionStatusMedicine.getLength());
        return null;
    }
}
