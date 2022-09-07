import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private String text;
    private Date commentDate;
    private User author;

    public Comment(String text, Date commentDate, User author) {
        this.text = text;
        this.commentDate = commentDate;
        this.author = author;
    }
}
