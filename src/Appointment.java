import java.time.LocalDate;

// Class declaration
class Appointment implements Comparable<Appointment> {

    // Instance variables (encapsulation)
    private String appointmentID, patientID, appointmentType, doctorID, appointmentReason;
    private LocalDate appointmentDate;
    private String appointmentTime;

    // Class variable (encapsulation)
    public final static String fileName = "appointment.csv";

    // Constructor (encapsulation, abstraction)
    public Appointment(String id, String patient, String doctor, String reason, String type, LocalDate date, String time) {
        this.appointmentID = id;
        this.patientID = patient;
        this.doctorID = doctor;
        this.appointmentReason = reason;
        this.appointmentType = type;
        this.appointmentDate = date;
        this.appointmentTime = time;
    }

    // Getters and setters (encapsulation)
    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getAppointmentReason() {
        return appointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    // Method overriding (polymorphism)
    @Override
    public int compareTo(Appointment u) {
        if (getAppointmentID() == null || u.getAppointmentID() == null) {
            return 0;
        }
        return getAppointmentID().compareTo(u.getAppointmentID());
    }

    // Static method (encapsulation)
    public static String getFilename() {
        return fileName;
    }
}

