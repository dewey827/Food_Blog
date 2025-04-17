package project.myBatis.foodBlog.controller;

import project.myBatis.foodBlog.dto.ImageFileDTO;
import project.myBatis.foodBlog.dto.RecipeDTO;
import project.myBatis.foodBlog.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    //C. 게시글 작성 - 작성용 폼 렌더링
    @GetMapping("/save")
    public String recipeSave() {
        return "recipeSave";
    }

    //C. 게시글 작성 - 게시글 저장 - 리다이렉트
    @PostMapping("/save")
    public String recipeSave(RecipeDTO recipeDTO) throws IOException {

        recipeService.recipeSave(recipeDTO);
        System.out.println(recipeDTO);
        return "redirect:/recipe/list";
    }

    //R. 전체 글목록 조회
    @GetMapping("/list")
    public String recipeFindAll(Model model) {
        List<RecipeDTO> recipeDTOList = recipeService.recipeFindAll();
        model.addAttribute("recipeList", recipeDTOList);

        List<RecipeDTO> bestRecipes = recipeService.findBest();
        model.addAttribute("bestRecipeList", bestRecipes);

        Map<Long, List<ImageFileDTO>> recipeMap = new HashMap<>();
        for (RecipeDTO recipeDTO : recipeDTOList) {
            List<ImageFileDTO> recipeFileDTOList = recipeService.recipeFileFind(recipeDTO.getId());
            recipeMap.put(recipeDTO.getId(), recipeFileDTOList);
        }
        model.addAttribute("recipeFileDTOList", recipeMap);

        Map<Long, List<ImageFileDTO>> bestRecipeMap = new HashMap<>();
        for (RecipeDTO recipeDTO : bestRecipes){
            List<ImageFileDTO> recipeFileDTOList = recipeService.recipeFileFind(recipeDTO.getId());
            bestRecipeMap.put(recipeDTO.getId(), recipeFileDTOList);
        }
        model.addAttribute("bestRecipeFileDTOList", bestRecipeMap);


        return "recipeList";
    }


    //R. 상세 게시글 조회
    @GetMapping("/{id}")
    public String recipeFindById(@PathVariable("id") Long id, Model model) {
        //조회수 처리
        recipeService.recipeHitsUpdate(id);

        //상세내용
        RecipeDTO recipeDTO = recipeService.recipeFindById(id);
        model.addAttribute("recipe", recipeDTO);

//        if (recipeDTO.getFileAttached() == 1) {
//            List<ImageFileDTO> imageFileDTOList = recipeService.recipeFileFind(id);
//            model.addAttribute("recipeFileList", imageFileDTOList);
//            System.out.println(imageFileDTOList);
//        }
        List<ImageFileDTO> imageFileDTOList = recipeService.recipeFileFind(id);
        model.addAttribute("recipeFileList", imageFileDTOList);
        System.out.println(imageFileDTOList);

        return "recipeDetail";
    }

    //U. 게시글 수정 - 수정용 폼 렌더링
    @GetMapping("/update/{id}")
    public String recipeUpdate(@PathVariable("id") Long id, Model model) {
        RecipeDTO recipeDTO = recipeService.recipeFindById(id);
        model.addAttribute("recipe", recipeDTO);
        return "recipeUpdate";
    }

    //U. 게시글 수정 - 수정된 게시글 조회 - 리다이렉트
    @PostMapping("/update/{id}")
    public String recipeUpdate(@PathVariable("id") Long id, RecipeDTO recipeDTO, Model model) {
        recipeService.recipeUpdate(recipeDTO);
        RecipeDTO dto = recipeService.recipeFindById(id);
        model.addAttribute("recipe",dto);

        return "redirect:/recipe/list";
    }

    //D. 게시글 삭제 - 리다이렉트
    @GetMapping("/delete/{id}")
    public String recipeDelete(@PathVariable("id") Long id) {
        recipeService.recipeDelete(id);
        return "redirect:/recipe/list";
    }
}
