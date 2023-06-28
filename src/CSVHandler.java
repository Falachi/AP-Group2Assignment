import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class CSVHandler {
    // Class-level variables
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "patientid, name, ic, bloodtype, age, gender, dob, natinonality, condition, occupation, maritalstatus, address, contactno, email";

    // constructors
    public CSVHandler() {
    }

    public CSVHandler(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    // Method to write a list of Patient objects to a CSV file
    public void writeCSV(List<Patient> patients, String fileName) {
        // Local variable
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (Patient patient : patients) {
                // Accessing Patient object's methods
                fileWriter.append(patient.getPatientId());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getName());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getIcNumber());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getBloodType());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getAge());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getGender());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getDateOfBirth().toString());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getNationality());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getCondition());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getOccupation());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getMaritalStatus());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getAddress());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getContactNumber());
                fileWriter.append(delimiter);
                fileWriter.append(patient.getEmail());
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

    // Method to read a CSV file and return a list of Patient objects
    public List<Patient> readCSV(String fileName) {
        // Local variables
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Patient> patients = new ArrayList<Patient>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    // Creating Patient object and adding to the list
                    Patient patient = new Patient(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], LocalDate.parse(tokens[6]), tokens[7], tokens[8], tokens[9], tokens[10], tokens[11], tokens[12], tokens[13]);
                    patients.add(patient);
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
        return patients;
    }

    // Method to create a new Patient object and add it to the CSV file
    public void create(Patient p) {
        // Local variable
        List<Patient> patients = this.readCSV(Patient.fileName);
        patients.add(p);
        Collections.sort(patients);
        writeCSV(patients, Patient.fileName);
    }
    
    // Method to update an existing Patient object in the CSV file using ID as the identifier
    public void update(String id, String newName, String newicNumber, String newbloodType, String newAge, String newgender, LocalDate newdateofBirth, String newnationality, String newcondition, String newoccupation, String newmaritalStatus, String newaddress, String newcontactNumber, String newemail, String fileName) {
        // Local variable
        List<Patient> patients = readCSV(fileName);
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(id)) {
                // Updating properties of the Patient object
                patient.setName(newName);
                patient.setIcNumber(newicNumber);
                patient.setBloodType(newbloodType);
                patient.setAge(newAge);
                patient.setGender(newgender);
                patient.setDateOfBirth(newdateofBirth);
                patient.setNationality(newnationality);
                patient.setCondition(newcondition);
                patient.setOccupation(newoccupation);
                patient.setMaritalStatus(newmaritalStatus);
                patient.setAddress(newaddress);
                patient.setContactNumber(newcontactNumber);
                patient.setEmail(newemail);
                break;
            }
        }
        Collections.sort(patients);
        writeCSV(patients, fileName);
    }

    public void update(Patient p, String fileName) {
        List<Patient> patients = readCSV(fileName);
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(p.getPatientId())) {
                patient.setName(p.getName());
                patient.setBloodType(p.getBloodType());
                patient.setAge(p.getAge());
                patient.setGender(p.getGender());
                patient.setDateOfBirth(p.getDateOfBirth());
                patient.setNationality(p.getNationality());
                patient.setCondition(p.getCondition());
                patient.setOccupation(p.getOccupation());
                patient.setMaritalStatus(p.getMaritalStatus());
                patient.setAddress(p.getAddress());
                patient.setContactNumber(p.getContactNumber());
                patient.setEmail(p.getEmail());
                break;
            }
        }
        Collections.sort(patients);
        writeCSV(patients, fileName);
    }

    public void update(List<Patient> patients, Patient p, String fileName) {
        patients = readCSV(fileName);
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(p.getPatientId())) {
                patient.setName(p.getName());
                patient.setBloodType(p.getBloodType());
                patient.setAge(p.getAge());
                patient.setGender(p.getGender());
                patient.setDateOfBirth(p.getDateOfBirth());
                patient.setNationality(p.getNationality());
                patient.setCondition(p.getCondition());
                patient.setOccupation(p.getOccupation());
                patient.setMaritalStatus(p.getMaritalStatus());
                patient.setAddress(p.getAddress());
                patient.setContactNumber(p.getContactNumber());
                patient.setEmail(p.getEmail());
                break;
            }
        }
        Collections.sort(patients);
        writeCSV(patients, fileName);
    }

    // Method to delete a Patient object using ID from the CSV file
    public void delete(String id, String fileName) {
        // Local variable
        List<Patient> patients = readCSV(fileName);
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(id)) {
                patients.remove(patient);
                break;
            }
        }
        Collections.sort(patients);
        writeCSV(patients, fileName);
    }
}


