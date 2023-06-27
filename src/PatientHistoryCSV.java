import java.io.*;
import java.util.*;

public class PatientHistoryCSV {
    // Class-level variables
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "patientid, height, weight, past illness, family disease, childhood disease, allergies, medicine history, procedure history, sexual history"; // e.g for Patient class data

    // Default constructor
    public PatientHistoryCSV() {
    }

    // Parameterized constructor
    public PatientHistoryCSV(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    // Method to write a list of patienthistory objects to a CSV file
    public void writeCSV(List<PatientHistory> patientHistory, String fileName) {
        // Local variable
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (PatientHistory ph : patientHistory) {
                // Accessing patienthistory object's methods
                fileWriter.append(ph.getPatientID());
                fileWriter.append(delimiter);
                fileWriter.append(Double.toString(ph.getHeight()));
                fileWriter.append(delimiter);
                fileWriter.append(Double.toString(ph.getWeight()));
                fileWriter.append(delimiter);
                fileWriter.append(ph.getHistoryOfIllness());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getFamilyDiseases());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getChildhoodDiseases());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getAllergies());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getMedicineHistory());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getProcedureHistory());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getProcedureHistory());
                fileWriter.append(delimiter);
                fileWriter.append(ph.getSexualHistory());
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

    // Method to read a CSV file and return a list of patienthistory objects
    public List<PatientHistory> readCSV(String fileName) {
        // Local variables
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<PatientHistory> patienthistories = new ArrayList<PatientHistory>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    // Creating patienthistory object and adding to the list
                    PatientHistory ph = new PatientHistory(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9]);
                    patienthistories.add(ph);
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
        return patienthistories;
    }

    // Method to create a new patienthistory object and add it to the CSV file
    public void create(PatientHistory p) {
        List<PatientHistory> patienthistories = this.readCSV(PatientHistory.filename);
        patienthistories.add(p);
        Collections.sort(patienthistories);
        writeCSV(patienthistories, PatientHistory.filename);
    }

    // Method to update an existing patienthistory object in the CSV file
    public void update(String ID, double height, double weight, String pastIllness, String familyDisease, String childhoodDisease, String allergies, String medicineHistory, String procedureHistory, String sexualHistory) {
        List<PatientHistory> patienthistories = readCSV(PatientHistory.filename);
        for (PatientHistory ph : patienthistories) {
            if (ph.getPatientID().equals(ID)) {
                // Updating properties of the patienthistory object
                ph.setHeight(height);
                ph.setWeight(weight);
                ph.setHistoryOfIllness(pastIllness);
                ph.setFamilyDiseases(familyDisease);
                ph.setChildhoodDiseases(childhoodDisease);
                ph.setAllergies(allergies);
                ph.setMedicineHistory(medicineHistory);
                ph.setProcedureHistory(procedureHistory);
                ph.setSexualHistory(sexualHistory);
                break;
            }
        }
        Collections.sort(patienthistories);
        writeCSV(patienthistories, PatientHistory.filename);
    }

    public void update(PatientHistory p, String fileName) {
        List<PatientHistory> patienthistories = readCSV(PatientHistory.filename);
        for (PatientHistory ph : patienthistories) {
            if (ph.getPatientID().equals(p.getPatientID())) {
                ph.setHeight(p.getHeight());
                ph.setWeight(p.getWeight());
                ph.setHistoryOfIllness(p.getHistoryOfIllness());
                ph.setFamilyDiseases(p.getFamilyDiseases());
                ph.setChildhoodDiseases(p.getChildhoodDiseases());
                ph.setAllergies(p.getAllergies());
                ph.setMedicineHistory(p.getMedicineHistory());
                ph.setProcedureHistory(p.getProcedureHistory());
                ph.setSexualHistory(p.getSexualHistory());
                break;
            }
        }
        Collections.sort(patienthistories);
        writeCSV(patienthistories, fileName);
    }

    public void update(List<PatientHistory> patienthistories, PatientHistory p, String fileName) {
        patienthistories = readCSV(fileName);
        for (PatientHistory ph : patienthistories) {
            if (ph.getPatientID().equals(p.getPatientID())) {
                ph.setHeight(p.getHeight());
                ph.setWeight(p.getWeight());
                ph.setHistoryOfIllness(p.getHistoryOfIllness());
                ph.setFamilyDiseases(p.getFamilyDiseases());
                ph.setChildhoodDiseases(p.getChildhoodDiseases());
                ph.setAllergies(p.getAllergies());
                ph.setMedicineHistory(p.getMedicineHistory());
                ph.setProcedureHistory(p.getProcedureHistory());
                ph.setSexualHistory(p.getSexualHistory());
                break;
            }
        }
        Collections.sort(patienthistories);
        writeCSV(patienthistories, fileName);
    }

    // Method to delete a patienthistory object from the CSV file
    public void delete(String id, String fileName) {
        List<PatientHistory> patienthistories = readCSV(fileName);
        for (PatientHistory p : patienthistories) {
            if (p.getPatientID().equals(id)) {
                patienthistories.remove(p);
                break;
            }
        }
        Collections.sort(patienthistories);
        writeCSV(patienthistories, fileName);
    }
}
