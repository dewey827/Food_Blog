package project.myBatis.foodBlog.controller;

import project.myBatis.foodBlog.dto.CommentDTO;
import project.myBatis.foodBlog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //C. 댓글 작성 - 댓글 저장 - 리다이렉트
    @PostMapping("/list")
    public String commentSave(CommentDTO commentDTO)  {
        commentService.save(commentDTO);
        System.out.println(commentDTO);
        return "redirect:/comment/list";
    }

    //R. 전체 댓글목록 조회
    @GetMapping("/list")
    public String commentFindAll(Model model) {
        List<CommentDTO> commentDTOS = commentService.findAll();
        model.addAttribute("commentList", commentDTOS);
        System.out.println(commentDTOS);
        return "guestBook";
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam("c_id") Long id) {
        commentService.delete(id); // 댓글 삭제 로직 호출
        return "redirect:/comment/list"; // 삭제 후 댓글 목록 페이지로 리다이렉트
    }
}