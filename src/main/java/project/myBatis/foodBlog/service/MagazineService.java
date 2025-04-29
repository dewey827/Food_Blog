package project.myBatis.foodBlog.service;

import project.myBatis.foodBlog.dto.MagazineDTO;
import project.myBatis.foodBlog.dto.MagazineFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.myBatis.foodBlog.repository.BlogRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MagazineService {

    private final BlogRepository blogRepository;

    public void save(MagazineDTO magazineDTO) throws IOException {
       if (magazineDTO.getVisualImage().get(0).isEmpty()){
           magazineDTO.setFileAttached(0);
           blogRepository.magazineSave(magazineDTO);
       }else {
           magazineDTO.setFileAttached(1);
           MagazineDTO saveMagazine = blogRepository.magazineSave(magazineDTO);
           for(MultipartFile magazineFile : magazineDTO.getVisualImage()){
               String originalFilename = magazineFile.getOriginalFilename();
               System.out.println("originalFilename = " + originalFilename);

               System.out.println(System.currentTimeMillis());
               String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
               System.out.println("storedFileName = " + storedFileName);

               MagazineFileDTO magazineFileDto = new MagazineFileDTO();
               magazineFileDto.setOriginalFileName(originalFilename);
               magazineFileDto.setStoredFileName(storedFileName);
               magazineFileDto.setArticleId(saveMagazine.getId());

               String savePath = "C:\\Users\\goott6\\Desktop\\foodBlog\\upload" + storedFileName;
               magazineFile.transferTo(new File(savePath));
               blogRepository.saveMagazineFile(magazineFileDto);
           }
       }
   }

    public List<MagazineDTO> findAll() {
       return blogRepository.magazineFindAll();
    }

    public void updateHits(Long id) {
        blogRepository.magazineUpdateHits(id);
    }

    public MagazineDTO findById(Long id) {
        return blogRepository.magazineFindById(id);
    }

    public List<MagazineFileDTO> findFile(Long id) {
        return blogRepository.magazineFileFind(id);
    }

    public void update(MagazineDTO magazineDTO) {
        blogRepository.magazineUpdate(magazineDTO);
    }

    public void delete(Long id) {
        blogRepository.magazineDelete(id);
    }

    public List<MagazineDTO> findBest() {
        return blogRepository.magazineFindBest();
    }
}