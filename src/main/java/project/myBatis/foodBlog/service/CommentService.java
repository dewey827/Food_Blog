package project.myBatis.foodBlog.service;

import project.myBatis.foodBlog.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.myBatis.foodBlog.repository.BlogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BlogRepository blogRepository;

    //C. 방명록 작성
    public void  save(CommentDTO commentDTO){
        blogRepository.commentSave(commentDTO);
    }

    //R. 전체 방명록 조회
    public List<CommentDTO> findAll(){
        return blogRepository.commentFindAll();
    }

    //D. 방명록 삭제
    public void delete(Long id){
        blogRepository.commentDelete(id);
    }
}