//Class declaration
public class User extends HelloApplication{
    // Instance variables (encapsulation)
    private String username;
    private AccessLevel accessLevel;

    public User(){}

    // Constructor (encapsulation, abstraction)
    public User(String username, AccessLevel accessLevel){
        this.username=username;
        this.accessLevel=accessLevel;
    }

    // Getters and setters (encapsulation)
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setAccessLevel(String accessLevel){
        this.accessLevel= AccessLevel.valueOf(accessLevel);
    }
    public AccessLevel getAccessLevel(){
        return accessLevel;
    }
    public enum AccessLevel {
        DOCTOR,
        NURSE,
        PHARMACIST

    }
}
