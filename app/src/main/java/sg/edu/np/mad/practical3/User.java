package sg.edu.np.mad.practical3;

public class User {
    private String name;
    private String description;
    private int id;
    private boolean followed;


    public User(String userName, String userDescription, int userId, boolean userFollowed){
        name = userName;
        description = userDescription;
        id = userId;
        followed = userFollowed;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String names) {
        name = names;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptions) {
        description = descriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int ids) {
        id = ids;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean follows) {
        followed = follows;
    }
}
