import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private List<Post> publications;
    private String username;
    private List<User> followers;

    public User(String username, List<User> followers, List<Post> publications) {
        this.username = username;
        this.followers = new ArrayList<>();
        this.publications = new ArrayList<>();
    }
}