package sg.edu.np.mad.practical3;

public class Mad {
    private String Username;
    private String Password;

    public Mad(String username, String password) {
        Username = username;
        Password = password;
    }

    public Mad() {
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
