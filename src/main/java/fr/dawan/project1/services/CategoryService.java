package fr.dawan.project1.services;

import fr.dawan.project1.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService extends GenericService<CategoryDto> {

    List<CategoryDto> findAll(int page, int size, String search) throws Exception;

    CategoryDto saveOrUpdateWithImage(String categoryStr, MultipartFile file) throws Exception;
}
