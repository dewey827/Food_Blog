package project.myBatis.foodBlog.service;

import project.myBatis.foodBlog.dto.ImageFileDTO;
import project.myBatis.foodBlog.dto.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.myBatis.foodBlog.repository.BlogRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final BlogRepository blogRepository;

    //C. 새글 작성
    public void recipeSave(RecipeDTO recipeDTO) throws IOException {
        if(recipeDTO.getVisualImage().get(0).isEmpty()){
            //파일 없을 때
            recipeDTO.setFileAttached(0);
            blogRepository.recipeSave(recipeDTO);
        } else{
            //파일 있을 때
            recipeDTO.setFileAttached(1);
            //게시글 저장 후 id값 활용을 위해 리턴 받음.
            RecipeDTO savedRecipe = blogRepository.recipeSave(recipeDTO);


            // 파일만 따로 가져오기
            for(MultipartFile visualImage: recipeDTO.getVisualImage()){
                // 파일 이름 가져오기
                String originalFilename = visualImage.getOriginalFilename();

                // 저장용 이름 만들기
                String storedFileName = System.currentTimeMillis() + "-" + originalFilename;

                // ImageFileDTO 세팅
                ImageFileDTO imageFileDTO = new ImageFileDTO();
                imageFileDTO.setOriginalFileName(originalFilename);
                imageFileDTO.setStoredFileName(storedFileName);
                imageFileDTO.setArticleId(savedRecipe.getId());

                // 파일 저장용 폴더에 파일 저장 처리
                String savePath = "C:\\Users\\goott6\\Desktop\\foodBlog\\upload" + storedFileName;
                visualImage.transferTo(new File(savePath));
                // image_file_table 저장 처리
                blogRepository.recipeFileSave(imageFileDTO);
            }
        }
    }

    //R. 전체 글 목록 조회
    public List<RecipeDTO> recipeFindAll(){
        return blogRepository.recipeFindAll();
    }

    //R. 게시글 개별 조회
    public RecipeDTO recipeFindById(Long id){
        return  blogRepository.recipeFindById(id);
    }

    //U. 게시글 업데이트
    public void recipeUpdate(RecipeDTO recipeDTO){
        blogRepository.recipeUpdate(recipeDTO);
    }

    //D. 게시글 삭제
    public void recipeDelete(Long id){
        blogRepository.recipeDelete(id);
    }

    //조회수 업데이트
    public void recipeHitsUpdate(Long id){
        blogRepository.recipeHitsUpdate(id);
    }

    //파일 찾아서 가져오기
    public List<ImageFileDTO> recipeFileFind(Long id){
        return blogRepository.recipeFileFind(id);
    }

    public List<RecipeDTO> findBest() {
        return blogRepository.recipeFindBest();
    }
}