package project.myBatis.foodBlog.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import project.myBatis.foodBlog.dto.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BlogRepository {

    private final SqlSessionTemplate sql;
    //=============================================================
    //레시피
    //★C. 새글 추가
    public RecipeDTO recipeSave(RecipeDTO recipeDTO){
        sql.insert("FoodBlog.recipeSave", recipeDTO);
        return recipeDTO;
    }

    //R. 전체목록 조회
    public List<RecipeDTO> recipeFindAll(){

        return sql.selectList("FoodBlog.recipeFindAll");
    }

    //R. 게시글 개별 조회
    public RecipeDTO recipeFindById(Long id){

        return sql.selectOne("FoodBlog.recipeFindById", id);
    }

    //U. 게시글 업데이트
    public void recipeUpdate(RecipeDTO recipeDTO){

        sql.update("FoodBlog.recipeUpdate", recipeDTO);
    }

    //D. 게시글 삭제
    public void recipeDelete(Long id){

        sql.delete("FoodBlog.recipeDelete", id);
    }

    //조회수 업데이트
    public void recipeHitsUpdate(Long id){

        sql.update("FoodBlog.recipeHitsUpdate", id);
    }

    //파일 save
    public void recipeFileSave(ImageFileDTO imageFileDTO){

        sql.insert("FoodBlog.recipeFileSave", imageFileDTO);
    }

    //파일 find
    public List<ImageFileDTO> recipeFileFind(Long id){

        return sql.selectList("FoodBlog.recipeFileFind", id);
    }

    public List<RecipeDTO> recipeFindBest() {
        return sql.selectList("FoodBlog.recipeFindBest");
    }


    //=============================================================
    //매거진
    public MagazineDTO magazineSave(MagazineDTO magazineDTO){
        sql.insert("FoodBlog.magazineSave", magazineDTO);
        return magazineDTO;
    }

    public void saveMagazineFile(MagazineFileDTO magazineFileDto){
        sql.insert("FoodBlog.saveMagazineFile", magazineFileDto);
    }

    public List<MagazineDTO> magazineFindAll() {
        return sql.selectList("FoodBlog.magazineFindAll");
    }

    public void magazineUpdateHits(Long id) {
        sql.update("FoodBlog.findById", id);
    }

    public MagazineDTO magazineFindById(Long id) {
        return sql.selectOne("FoodBlog.magazineFindById", id);
    }

    public List<MagazineFileDTO> magazineFileFind(Long id) {
        return sql.selectList("FoodBlog.magazineFileFind", id);
    }

    public void magazineUpdate(MagazineDTO magazineDTO) {
        sql.update("FoodBlog.magazineUpdate", magazineDTO);
    }

    public void magazineDelete(Long id) {
        sql.delete("FoodBlog.magazineDelete", id);
    }

    public List<MagazineDTO> magazineFindBest() {
        return sql.selectList("FoodBlog.magazineFindBest");
    }

    //=============================================================
    //C. 새 방명록 추가
    public CommentDTO commentSave(CommentDTO commentDTO){
        sql.insert("FoodBlog.commentSave", commentDTO);
        return commentDTO;
    }

    //R. 방명록 전체 조회
    public List<CommentDTO> commentFindAll(){
        return sql.selectList("FoodBlog.commentFindAll");
    }

    //D. 방명록 삭제
    public void commentDelete(Long id){
        sql.delete("FoodBlog.commentDelete", id);
    }
}