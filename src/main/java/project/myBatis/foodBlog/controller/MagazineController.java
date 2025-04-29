package project.myBatis.foodBlog.controller;

import project.myBatis.foodBlog.dto.MagazineDTO;
import project.myBatis.foodBlog.dto.MagazineFileDTO;
import project.myBatis.foodBlog.service.MagazineService;
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
@RequestMapping("/magazine")
@RequiredArgsConstructor
public class MagazineController {

    private final MagazineService magazineService;

    //C. 매거진 작성 - 작성용 폼 렌더링
    @GetMapping("/save")
    public String magazineSave() {
        return "magazineSave";
    }

    //C. 매거진 작성 - 게시글 저장 - 리다이렉트
    @PostMapping("/save")
    public String magazineSave(MagazineDTO magazineDTO) throws IOException {
        System.out.println("MagazineDTO : " + magazineDTO);
        magazineService.save(magazineDTO);
        return "redirect:/magazine/list";
    }

    //R. 전체 매거진 목록 조회
    @GetMapping("/list")
    public String magazineFindAll(Model model) {
        List<MagazineDTO> magazineDTOList = magazineService.findAll();
        model.addAttribute("magazineList", magazineDTOList);

        List<MagazineDTO> bestMagazines = magazineService.findBest();
        model.addAttribute("bestMagazines", bestMagazines);

        Map<Long, List<MagazineFileDTO>> magazineMap = new HashMap<>();
        for (MagazineDTO magazineDTO : magazineDTOList) {
            List<MagazineFileDTO> magazineFileDTOList = magazineService.findFile(magazineDTO.getId());
            magazineMap.put(magazineDTO.getId(),magazineFileDTOList);
        }
        model.addAttribute("magazineFileDTOList", magazineMap);

        Map<Long, List<MagazineFileDTO>> bestMagazineMap = new HashMap<>();
        for (MagazineDTO magazineDTO : bestMagazines) {
            List<MagazineFileDTO> magazineFileDTOList = magazineService.findFile(magazineDTO.getId());
            bestMagazineMap.put(magazineDTO.getId(),magazineFileDTOList);
        }
        model.addAttribute("bestMagazineFileDTOList", bestMagazineMap);

        return "magazineList";
    }

    //R. 상세 매거진 조회
    @GetMapping("/{id}")
    public String magazineFindById(@PathVariable("id") Long id, Model model) {
//        magazineService.updateHits(id);
        MagazineDTO magazineDTO = magazineService.findById(id);
        model.addAttribute("magazine",magazineDTO);
        System.out.println("magazineDto : " + magazineDTO);

        if(magazineDTO.getFileAttached() == 1){
            List<MagazineFileDTO> magazineFileDTOList = magazineService.findFile(id);
            model.addAttribute("magazineFileDTOList", magazineFileDTOList);
        }
        return "magazineDetail";
    }

    //U. 매거진 수정 - 수정용 폼 렌더링
    @GetMapping("/update/{id}")
    public String magazineUpdate(@PathVariable("id") Long id, Model model) {
        MagazineDTO magazineDTO = magazineService.findById(id);
        model.addAttribute("magazine", magazineDTO);

        if(magazineDTO.getFileAttached() == 1){
            List<MagazineFileDTO> magazineFileDTOList = magazineService.findFile(id);
            model.addAttribute("magazineFileDTOList", magazineFileDTOList);
        }

        return "magazineUpdate";
    }

    //U. 매거진 수정 - 수정된 매거진 저장 - 리다이렉트
    @PostMapping("/update/{id}")
    public String magazineUpdate(@PathVariable("id")Long id, MagazineDTO magazineDTO, Model model) {
        magazineService.update(magazineDTO);
        MagazineDTO dto = magazineService.findById(magazineDTO.getId());
        model.addAttribute("magazine", dto);

        if(magazineDTO.getFileAttached() == 1){
            List<MagazineFileDTO> magazineFileDTOList = magazineService.findFile(id);
            model.addAttribute("magazineFileDTOList", magazineFileDTOList);
        }

        return "magazineDetail";
    }

    //D. 매거진 삭제 - 리다이렉트
    @GetMapping("/delete/{id}")
    public String magazineDelete(@PathVariable("id") Long id) {
        magazineService.delete(id);
        return "redirect:/magazine/list";
    }
}