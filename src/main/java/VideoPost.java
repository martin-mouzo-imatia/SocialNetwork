import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VideoPost extends Post {

    private String calidad;
    private int duration;

    public VideoPost(String title, Date postDate) {
        super(title, postDate);
    }

    public VideoPost(String title, Date postDate, String calidad, int duration) {
        super(title, postDate);
        this.calidad = calidad;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "VideoPost{" +
                "calidad='" + calidad + '\'' +
                ", duration=" + duration +
                "} " + super.toString();
    }
}
