import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class CSVDiagnosis {
    // Class-level variables
    List<Patient> p = new ArrayList<Patient>();
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "patientDiagnosisID, doctorID, diagnosisDate, diagnosisType, diagnosisCategory, symptom, severity, diagnosisCode";

    //constructors
    public CSVDiagnosis() {
    }

    public CSVDiagnosis(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    // Method to write a list of Diagnosis objects to a CSV file
    public void writeCSV(List<Diagnosis> diagnoses, String fileName) {
        // Local variable
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (Diagnosis diagnosis : diagnoses) {
                // Accessing Diagnosis object's methods
                fileWriter.append(diagnosis.getPatientDiagnosisID());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getDoctorID());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getDiagnosisDate().toString());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getDiagnosisType());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getDiagnosisCategory());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getSymptoms());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getSeverity());
                fileWriter.append(delimiter);
                fileWriter.append(diagnosis.getDiagnosisCode());
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

    // Method to read a CSV file and return a list of Diagnosis objects
    public List<Diagnosis> readCSV(String fileName) {
        // Local variables
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    // Creating Diagnosis object and adding to the list
                    Diagnosis diagnoses = new Diagnosis(tokens[0], tokens[1], LocalDate.parse(tokens[2]), tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
                    diagnosis.add(diagnoses);
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
        return diagnosis;
    }

    // Method to create a new Diagnosis object and add it to the CSV file
    public void create(Diagnosis d) {
        List<Diagnosis> diagnosis = this.readCSV(Diagnosis.fileName);
        diagnosis.add(d);
        Collections.sort(diagnosis);
        writeCSV(diagnosis, Diagnosis.fileName);
    }

    // Method to update an existing Diagnosis object using ID in the CSV file
    public void update(Diagnosis d, String fileName) {
        List<Diagnosis> diagnoses = readCSV(fileName);
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getPatientDiagnosisID().equals(d.getPatientDiagnosisID())) {
                // Updating properties of the Diagnosis object
                diagnosis.setPatientDiagnosisID(d.getPatientDiagnosisID());
                diagnosis.setDiagnosisType(d.getDiagnosisType());
                diagnosis.setDiagnosisCode(d.getDiagnosisCode());
                diagnosis.setDiagnosisCategory(d.getDiagnosisCategory());
                diagnosis.setDoctorID(d.getDoctorID());
                diagnosis.setSeverity(d.getSeverity());
                diagnosis.setSymptoms(d.getSymptoms());
                break;
            }
        }
        Collections.sort(diagnoses);
        writeCSV(diagnoses, fileName);
    }

    public void update(String patientDiagnosisID, String doctorID, LocalDate diagnosisDate, String diagnosisType,
                       String diagnosisCategory, String symptoms, String severity, String diagnosisCode, String fileName) {
        List<Diagnosis> diagnoses = readCSV(fileName);
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getPatientDiagnosisID().equals(patientDiagnosisID)) {
                diagnosis.setDoctorID(doctorID);
                diagnosis.setDiagnosisCategory(diagnosisCategory);
                diagnosis.setDiagnosisCode(diagnosisCode);
                diagnosis.setDiagnosisDate(diagnosisDate);
                diagnosis.setDiagnosisType(diagnosisType);
                diagnosis.setSymptoms(symptoms);
                diagnosis.setSeverity(severity);
                break;
            }
        }
        Collections.sort(diagnoses);
        writeCSV(diagnoses, fileName);
    }

    public void update(List<Diagnosis> diagnoses, Diagnosis d, String fileName) {
        diagnoses = readCSV(fileName);
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getPatientDiagnosisID().equals(d.getPatientDiagnosisID())) {
                diagnosis.setDoctorID(d.getDoctorID());
                diagnosis.setDiagnosisDate(d.getDiagnosisDate());
                diagnosis.setDiagnosisType(d.getDiagnosisType());
                diagnosis.setDiagnosisCategory(d.getDiagnosisCategory());
                diagnosis.setSymptoms(d.getSymptoms());
                diagnosis.setSeverity(d.getSeverity());
                diagnosis.setDiagnosisCode(d.getDiagnosisCode());
                break;
            }
        }
        Collections.sort(diagnoses);
        writeCSV(diagnoses, fileName);
    }

    // Method to delete a Diagnosis object using ID from the CSV file
    public void delete(String id, String fileName) {
        List<Diagnosis> diagnoses = readCSV(fileName);
        for (Diagnosis diagnosis : diagnoses) {
            if (diagnosis.getPatientDiagnosisID().equals(id)) {
                diagnoses.remove(diagnosis);
                break;
            }
        }
        Collections.sort(diagnoses);
        writeCSV(diagnoses, fileName);
    }
}




