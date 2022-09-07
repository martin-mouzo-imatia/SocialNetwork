import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TextPost extends Post{

    private String content;


    public TextPost(String title, Date postDate) {
        super(title, postDate);
    }

    public TextPost(String title, Date postDate, String content) {
        super(title, postDate);
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextPost{" +
                "content='" + content + '\'' +
                "} " + super.toString();
    }
}
