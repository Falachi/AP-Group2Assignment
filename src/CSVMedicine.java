import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class CSVMedicine {
    // Class-level variables
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "patientID, medicineID, medicineName, dosage, duration, sideEffect, datePrescibed";

    // constructors
    public CSVMedicine() {
    }

    public CSVMedicine(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    // Method to write a list of Medicine objects to a CSV file
    public void writeCSV(List<Medicine> medicines, String fileName) {
        // Local variable
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (Medicine med : medicines) {
                // Accessing Medicine object's methods
                fileWriter.append(med.getPatientID());
                fileWriter.append(delimiter);
                fileWriter.append(med.getMedicineID());
                fileWriter.append(delimiter);
                fileWriter.append(med.getMedicineName());
                fileWriter.append(delimiter);
                fileWriter.append(Integer.toString(med.getDosage()));
                fileWriter.append(delimiter);
                fileWriter.append(Double.toString(med.getDuration()));
                fileWriter.append(delimiter);
                fileWriter.append(med.getSideEffect());
                fileWriter.append(delimiter);
                fileWriter.append(med.getDatePrescribed().toString());
                fileWriter.append(newline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to read a CSV file and return a list of Medicine objects
    public List<Medicine> readCSV(String fileName) {
        // Local variables
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Medicine> medicines = new ArrayList<Medicine>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    // Creating Medicine object and adding to the list
                    Medicine medicine = new Medicine(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]), tokens[5], LocalDate.parse(tokens[6])); // Abstraction: Constructor parameters
                    medicines.add(medicine);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return medicines;
    }

    // Method to create a new Medicine object and add it to the CSV file
    public void create(Medicine med) {
        // Local variable
        List<Medicine> medicines = this.readCSV(Medicine.fileName);
        medicines.add(med);
        Collections.sort(medicines);
        writeCSV(medicines, Medicine.fileName);
    }

    // Method to update an existing Medicine object in the CSV file using ID as the identifier
    public void update(String patientID, String medicineID, String medicineName, int dosage, double duration, String sideEffect,LocalDate datePrescribed, String fileName) {
        // Local variable
        List<Medicine> medicines = readCSV(fileName);
        for (Medicine med : medicines) {
            if (med.getPatientID().equals(patientID)) {
                // Updating properties of the Medicine object
                med.setMedicineID(medicineID);
                med.setMedicineName(medicineName);
                med.setDosage(dosage);
                med.setDuration(duration);
                med.setSideEffect(sideEffect);
                med.setDatePrescribed(datePrescribed);
                break;
            }
        }
        Collections.sort(medicines);
        writeCSV(medicines, fileName);
    }

    public void update(Medicine medicine, String fileName) {
        List<Medicine> medicines = readCSV(fileName);
        for (Medicine med : medicines) {
            if (med.getPatientID().equals(medicine.getPatientID())) {
                med.setMedicineID(medicine.getMedicineID());
                med.setMedicineName(medicine.getMedicineName());
                med.setDosage(medicine.getDosage());
                med.setDuration(medicine.getDuration());
                med.setSideEffect(medicine.getSideEffect());
                med.setDatePrescribed(medicine.getDatePrescribed());
                break;
            }
        }
        Collections.sort(medicines);
        writeCSV(medicines, fileName);
    }

    public void update(List<Medicine> medicines, Medicine med, String fileName) {
        medicines = readCSV(fileName);
        for (Medicine medicine : medicines) {
            if (medicine.getPatientID().equals(med.getPatientID())) {
                medicine.setMedicineID(med.getMedicineID());
                medicine.setMedicineName(med.getMedicineName());
                medicine.setDosage(med.getDosage());
                medicine.setDuration(med.getDuration());
                medicine.setSideEffect(med.getSideEffect());
                medicine.setDatePrescribed(med.getDatePrescribed());
            }
        }
        Collections.sort(medicines);
        writeCSV(medicines, fileName);
    }

    // Method to delete a Medicine object using ID from the CSV file
    public void delete(String id, String fileName) {
        // Local variable
        List<Medicine> medicines = readCSV(fileName);
        for (Medicine medicine : medicines) {
            if (medicine.getPatientID().equals(id)) {
                medicines.remove(medicine);
                break;
            }
        }
        Collections.sort(medicines);
        writeCSV(medicines, fileName);
    }
}
