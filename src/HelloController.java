import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class HelloController extends HelloApplication{

    // JavaFX controls (inheritance)
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button PatientRecordsButton;
    @FXML
    private Button PatientHistoryButton;
    @FXML
    private Button DiagnosisButton;
    @FXML
    private Button TreatmentCourseButton;
    @FXML
    private Button ProMedButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button backButton;
    private User currentUser;

    // Instance variables
    public static String user;
    public void loginButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();

        if(usernameField.getText().isBlank()==true || passwordField.getText().isBlank()==true) {
            loginLabel.setText("username and password cannot be empty");
        }
        else if(usernameField.getText().toString().equals("emr1") || usernameField.getText().toString().equals("emr2") || usernameField.getText().toString().equals("emr3") && passwordField.getText().toString().equals("123"))
            loginLabel.setText("login success");
            else {
                loginLabel.setText("incorrect login credentials");
            }

            user = usernameField.getText();
            if(user.equals("emr1")){
                System.out.println("Access Level: DOCTOR");
                currentUser=new User(user, User.AccessLevel.DOCTOR);
            }
            else if(user.equals("emr2")){
                currentUser=new User(user, User.AccessLevel.NURSE);
                System.out.println("Access Level: NURSE");
            }
            else if(user.equals("emr3")){
                currentUser=new User(user, User.AccessLevel.PHARMACIST);
                System.out.println("Access Level: PHARMACIST");
            }
        app.changeScene("home.fxml");

        }



    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void PatientRecordsButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("Main.fxml");
    }
    public void PatientHistoryButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("patient-history.fxml");
    }
    public void DiagnosisButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        if (user.equals("emr2")) {
            System.out.println("Nurse doesn't have access");
            return;
        }
        app.changeScene("diagnosis.fxml");
        }

    public void TreatmentCourseButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("treatment-course.fxml");
    }
    public void ProMedButton(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("procedure-medicine.fxml");
    }
    public void backButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("home.fxml");
    }
    public void logoutButtonOnAction(ActionEvent e) throws IOException {
        HelloApplication app = new HelloApplication();
        app.changeScene("hello-view.fxml");
    }
}