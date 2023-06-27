//Class declaration
public class PatientHistory implements Comparable<PatientHistory>{
    // Instance variables (encapsulation)
    private String patientID, historyOfIllness, procedureHistory, familyDiseases, childhoodDiseases, allergies, medicineHistory, sexualHistory;
    private double height, weight;
    // Class variable (encapsulation)
    public final static String filename = "patienthistory.csv";

    // Constructor (encapsulation, abstraction)
    public PatientHistory(String ID, double height, double weight, String pastIllness, String familyDisease, String childhoodDisease, String allergies, String medicineHistory, String procedureHistory, String sexualHistory) {
        this.patientID = ID;
        this.height = height;
        this.weight = weight;
        this.historyOfIllness = pastIllness;
        this.familyDiseases = familyDisease;
        this.childhoodDiseases = childhoodDisease;
        this.allergies = allergies;
        this.medicineHistory = medicineHistory;
        this.procedureHistory = procedureHistory;
        this.sexualHistory = sexualHistory;
    }
    public PatientHistory(){}

    // Getters and setters (encapsulation)
    public String getPatientID(){
        return patientID;
    }
    public void setPatientID(String patientID){
        this.patientID = patientID;
    }
    public String getHistoryOfIllness(){
        return historyOfIllness;
    }
    public void setHistoryOfIllness(String historyOfIllness){
        this.historyOfIllness = historyOfIllness;
    }

    public String getProcedureHistory(){
        return procedureHistory;
    }
    public void setProcedureHistory(String procedureHistory){
        this.procedureHistory = procedureHistory;
    }

    public String getFamilyDiseases(){
        return familyDiseases;
    }
    public void setFamilyDiseases(String familyDiseases){
        this.familyDiseases = familyDiseases;
    }

    public String getChildhoodDiseases(){
        return childhoodDiseases;
    }
    public void setChildhoodDiseases(String childhoodDiseases){
        this.childhoodDiseases = childhoodDiseases;
    }

    public String getAllergies(){
        return allergies;
    }
    public void setAllergies(String allergies){
        this.allergies = allergies;
    }

    public String getMedicineHistory(){
        return medicineHistory;
    }
    public void setMedicineHistory(String medicineHistory){
        this.medicineHistory = medicineHistory;
    }

    public String getSexualHistory(){
        return sexualHistory;
    }
    public void setSexualHistory(String sexualHistory){
        this.sexualHistory = sexualHistory;
    }

    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height = height;
    }

    public double getWeight(){
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    // Method overriding (polymorphism)
    @Override
    public int compareTo(PatientHistory u) {
        if (getPatientID() == null || u.getPatientID() == null) {
            return 0;
        }
        return getPatientID().compareTo(u.getPatientID());
    }

}