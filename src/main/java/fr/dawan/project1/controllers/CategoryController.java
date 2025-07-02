package fr.dawan.project1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.services.CategoryService;
import fr.dawan.project1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends GenericController<CategoryDto> {

    private CategoryService categoryService;



    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService=categoryService;
    }

    @GetMapping(value={"/{page}/{size}/{search}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> findAllBySearch(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable(value="search", required = false) Optional<String> search) throws Exception {
        if(search.isPresent())
            return categoryService.findAll(page, size, search.get());
        else
            return categoryService.findAll(page, size, null);
    }

    @PostMapping(value="/save-with-image", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> saveWithImage(@RequestParam("category") String categoryStr,
                                                     @RequestPart("file") MultipartFile file) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED) //201
                .body(categoryService.saveOrUpdateWithImage(categoryStr,file));

    }

    @PutMapping(value="/update-with-image", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> updateWithImage(@RequestParam("category") String categoryStr,
                                                     @RequestPart("file") MultipartFile file) throws Exception {

        return ResponseEntity.status(HttpStatus.OK) //201
                .body(categoryService.saveOrUpdateWithImage(categoryStr,file));


    }


}
