import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVAppointmentHandler {
    // Class-level variables
    private String delimiter = ",";
    private String newline = "\n";
    private String header = "appointmentID, patientID, doctorID, appointmentType, appointmentDescription,Date, time";

    // Constructor
    public CSVAppointmentHandler() {
    }

    // Method to write a list of Appointment objects to a CSV file
    public void writeCSV(List<Appointment> appointments, String fileName) {
        // Local variable
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            fileWriter.append(header);
            fileWriter.append(newline);

            for (Appointment appointment : appointments) {
                // Accessing Appointment object's methods
                fileWriter.append(appointment.getAppointmentID());
                fileWriter.append(delimiter);
                fileWriter.append(appointment.getPatientID());
                fileWriter.append(delimiter);
                fileWriter.append(appointment.getDoctorID());
                fileWriter.append(delimiter);
                fileWriter.append(appointment.getAppointmentReason());
                fileWriter.append(delimiter);
                fileWriter.append(appointment.getAppointmentType());
                fileWriter.append(delimiter);
                fileWriter.append(appointment.getAppointmentDate().toString());
                fileWriter.append(delimiter);
                fileWriter.append(appointment.getAppointmentTime());
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

    // Method to read a CSV file and return a list of Appointment objects
    public List<Appointment> readCSV(String fileName) {
        // Local variables
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Appointment> appointments = new ArrayList<Appointment>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] data = line.split(delimiter);
                if (data.length > 0) {
                    // Creating Appointment object and adding to the list
                    Appointment appointment = new Appointment(data[0],data[1],data[2],data[3],data[4],LocalDate.parse(data[5]),data[6]);
                    appointments.add(appointment);
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
        return appointments;
    }

    // Method to convert LocalDate to a formatted String
    public String dateConverter(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String localDateString = date.format(dateTimeFormatter);
        return localDateString;
    }

    // Method to create a new Appointment object and add it to the CSV file
    public void createAppointment(Appointment a) {
        List<Appointment> appointment = this.readCSV(Appointment.fileName);
        appointment.add(a);
        Collections.sort(appointment);
        writeCSV(appointment, Appointment.fileName);
    }

    // Method to update an existing Appointment object using ID in the CSV file
    public void updateappointment(String id, String patient, String doctor, String type, String reason, String time, String fileName) {
        List<Appointment> appointments = readCSV(fileName);
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(id)) {
                // Updating properties of the Appointment object
                appointment.setPatientID(patient);
                appointment.setDoctorID(doctor);
                appointment.setAppointmentType(type);
                appointment.setAppointmentReason(reason);
                appointment.setAppointmentTime(time);
                break;
            }
        }
        Collections.sort(appointments);
        writeCSV(appointments, fileName);
    }

    public void updateappointment2(Appointment Appointments, String fileName) {
        List<Appointment> appointments = readCSV(fileName);
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(Appointments.getAppointmentID())) {
                appointment.setPatientID(Appointments.getPatientID());
                appointment.setDoctorID(Appointments.getDoctorID());
                appointment.setAppointmentType(Appointments.getAppointmentType());
                appointment.setAppointmentReason(Appointments.getAppointmentReason());
                appointment.setAppointmentTime(Appointments.getAppointmentTime());
                break;
            }
        }
        Collections.sort(appointments);
        writeCSV(appointments, fileName);
    }

    public void updateappointment3(List<Appointment> appointments, Appointment Appointments, String fileName) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(Appointments.getAppointmentID())) {
                appointment.setPatientID(Appointments.getPatientID());
                appointment.setDoctorID(Appointments.getDoctorID());
                appointment.setAppointmentType(Appointments.getAppointmentType());
                appointment.setAppointmentReason(Appointments.getAppointmentReason());
                appointment.setAppointmentTime(Appointments.getAppointmentTime());
                break;
            }
        }
        Collections.sort(appointments);
        writeCSV(appointments, fileName);
    }

    // Method to delete an Appointment object using ID from the CSV file
    public void deleteappointment(String id, String fileName) {
        List<Appointment> appointments = readCSV(fileName);
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(id)) {
                appointments.remove(appointment);
                break;
            }
        }
        Collections.sort(appointments);
        writeCSV(appointments, fileName);
    }
}





