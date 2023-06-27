import java.io.*;
import java.util.*;

public class CSVProcedure {
    // Class-level variables
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "PatientID, procedureID, procedureName, procedureType, procedureDescription, duration";

    // constructors
    public CSVProcedure() {
    }
    
    public CSVProcedure(String header, String delimiter, String newline) {
        this.header = header;
        this.delimiter = delimiter;
        this.newline = newline;
    }

    // Method to write a list of Procedure objects to a CSV file
    public void writeCSV(List<ProcedureMedicine> procedures, String fileName) {
        // Local variable
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (ProcedureMedicine procedure : procedures) {
                // Accessing Procedure object's methods
                fileWriter.append(procedure.getPatientIDprocedure());
                fileWriter.append(delimiter);
                fileWriter.append(procedure.getProcedureID());
                fileWriter.append(delimiter);
                fileWriter.append(procedure.getProcedureName());
                fileWriter.append(delimiter);
                fileWriter.append(procedure.getProcedureType());
                fileWriter.append(delimiter);
                fileWriter.append(procedure.getProcedureDescription());
                fileWriter.append(delimiter);
                fileWriter.append(Double.toString(procedure.getDuration()));

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

    // Method to read a CSV file and return a list of Procedure objects
    public List<ProcedureMedicine> readCSV(String fileName) {
        // Local variables
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<ProcedureMedicine> procedures = new ArrayList<ProcedureMedicine>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    // Creating Procedure object and adding to the list
                    ProcedureMedicine procedure = new ProcedureMedicine(tokens[0],tokens[1],tokens[2], tokens[3], tokens[4], Double.parseDouble(tokens[5]));
                    procedures.add(procedure);
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
        return procedures;
    }

    // Method to create a new Procedure object and add it to the CSV file
    public void create(ProcedureMedicine procedure) {
        // Local variable
        List<ProcedureMedicine> procedures = this.readCSV(ProcedureMedicine.fileName);
        procedures.add(procedure);
        Collections.sort(procedures);
        writeCSV(procedures, ProcedureMedicine.fileName);
    }

    // Method to update an existing Procedure object in the CSV file using ID as the identifier
    public void update(String patientIDprocedure, String procedureID, String procedureName, String procedureType, String procedureDescription, double duration, String fileName) {
        // Local variable
        List<ProcedureMedicine> procedures = readCSV(fileName);
        for (ProcedureMedicine procedure : procedures) {
            if (procedure.getPatientIDprocedure().equals(patientIDprocedure)) {
                // Updating properties of the Procedure object
                procedure.setProcedureID(procedureID);
                procedure.setProcedureName(procedureName);
                procedure.setProcedureType(procedureType);
                procedure.setProcedureDescription(procedureDescription);
                procedure.setDuration(duration);
                break;
            }
        }
        Collections.sort(procedures);
        writeCSV(procedures, fileName);
    }

    public void update(ProcedureMedicine pM, String fileName) {
        List<ProcedureMedicine> procedures = readCSV(fileName);
        for (ProcedureMedicine procedure : procedures) {
            if (procedure.getPatientIDprocedure().equals(pM.getPatientIDprocedure())) {
                procedure.setProcedureID(pM.getProcedureID());
                procedure.setProcedureType(pM.getProcedureType());
                procedure.setProcedureName(pM.getProcedureName());
                procedure.setProcedureDescription(pM.getProcedureDescription());
                procedure.setDuration(pM.getDuration());
                break;
            }
        }
        Collections.sort(procedures);
        writeCSV(procedures, fileName);
    }

    public void update(List<ProcedureMedicine> procedures, ProcedureMedicine pM, String fileName) {
        procedures = readCSV(fileName);
        for (ProcedureMedicine procedure : procedures) {
            if (procedure.getPatientIDprocedure().equals(pM.getPatientIDprocedure())) {
                procedure.setProcedureID(pM.getProcedureID());
                procedure.setProcedureName(pM.getProcedureName());
                procedure.setProcedureType(pM.getProcedureType());
                procedure.setProcedureDescription(pM.getProcedureDescription());
                procedure.setDuration(pM.getDuration());
                break;
            }
        }
        Collections.sort(procedures);
        writeCSV(procedures, fileName);
    }

    // Method to delete a Medicine object using ID from the CSV file
    public void delete(String id, String fileName) {
        // Local variable
        List<ProcedureMedicine> procedures = readCSV(fileName);
        for (ProcedureMedicine procedure : procedures) {
            if (procedure.getPatientIDprocedure().equals(id)) {
                procedures.remove(procedure);
                break;
            }
        }
        Collections.sort(procedures);
        writeCSV(procedures, fileName);
    }
}
