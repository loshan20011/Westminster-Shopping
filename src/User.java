public class User {
    private final String userName;
    private final String password;

    public User(String userName, String password){
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
