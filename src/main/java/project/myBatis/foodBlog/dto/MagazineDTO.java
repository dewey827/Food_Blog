package project.myBatis.foodBlog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class MagazineDTO {

    private Long id;
    private String title;
    private String caption;
    private int Hits;
    private int fileAttached;
    private List<MultipartFile> visualImage;
    private String contentText;

}