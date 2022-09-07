import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ImgPost extends Post{

    private String dimension;

    public ImgPost(String title, Date postDate) {
        super(title, postDate);
    }

    public ImgPost(String title, Date postDate, String dimension) {
        super(title, postDate);
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "ImgPost{" +
                "dimension='" + dimension + '\'' +
                "} " + super.toString();
    }
}
