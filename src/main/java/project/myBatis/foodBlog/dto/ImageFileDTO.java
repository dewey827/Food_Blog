package project.myBatis.foodBlog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageFileDTO {

    private Long id;
    private Long articleId;
    private String originalFileName;
    private String storedFileName;
}
