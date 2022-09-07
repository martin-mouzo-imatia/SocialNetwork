import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Post {

    private String title;
    private Date postDate;
    private List<Comment> commentList;

    public Post(String title, Date postDate) {
        this.title = title;
        this.postDate = postDate;
    }

    public Post(String title, Date postDate, List<Comment> commentList) {
        this.title = title;
        this.postDate = postDate;
        this.commentList = commentList;
    }
}
