import java.time.LocalDate;

//Class declaration
public class TreatmentCourse implements Comparable<TreatmentCourse> {
    // Instance variables (encapsulation)
    private String treatmentID, treatmentDescription, patientID, treatmentType;
    private LocalDate startDate, endDate;
    // Class variable (encapsulation)
    public final static String fileName = "treatmentcourse.csv";

    // Constructor (encapsulation, abstraction)
    public TreatmentCourse(String id,String patient, String type, String description,LocalDate start, LocalDate end){
        this.treatmentID = id;
        this.patientID = patient;
        this.treatmentType = type;
        this.treatmentDescription = description;
        this.startDate = start;
        this.endDate = end;
    }

    // Getters and setters (encapsulation)
    public String getTreatmentID() {
        return treatmentID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setTreatmentID(String treatmentID) {
        this.treatmentID = treatmentID;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    // Method overriding (polymorphism)
    @Override
    public int compareTo(TreatmentCourse u) {
      if (getTreatmentID() == null || u.getTreatmentID() == null) {
        return 0;
      }
      return getTreatmentID().compareTo(u.getTreatmentID());
    }
    
}