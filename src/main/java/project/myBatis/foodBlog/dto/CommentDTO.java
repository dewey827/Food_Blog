package project.myBatis.foodBlog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {

    private Long id;
    private Long articleId;

    private String name;
    private Long password;
    private String content;


}



