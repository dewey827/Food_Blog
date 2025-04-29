package project.myBatis.foodBlog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class RecipeDTO {

    private Long id;
    private String title;
    private int fileAttached;
    private List<MultipartFile> visualImage;
    private String caption;
    private int Hits;
    private String foodSize;
    private String cookTime;
    private String difficulty;
    private String ingre1;
    private String ingre2;
    private String ingre3;
    private String ingre4;
    private String ingre5;
    private String ingre6;
    private String ingre7;
    private String ingre8;
    private String grams1;
    private String grams2;
    private String grams3;
    private String grams4;
    private String grams5;
    private String grams6;
    private String grams7;
    private String grams8;
    private String ytLink;
    private String step1;
    private String step2;
    private String step3;
    private String step4;
    private String step5;
    private String step6;
    private String step7;
    private String step8;
    private List<MultipartFile> thumb1;
    private List<MultipartFile> thumb2;
    private List<MultipartFile> thumb3;
    private List<MultipartFile> thumb4;
    private List<MultipartFile> thumb5;
    private List<MultipartFile> thumb6;
    private List<MultipartFile> thumb7;
    private List<MultipartFile> thumb8;
}