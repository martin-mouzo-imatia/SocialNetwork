import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private List<User> followers;
    private List<Post> publications;

    public User(String username, List<User> followers, List<Post> publications) {
        this.username = username;
        this.followers = followers;
        this.publications = publications;
    }
}
