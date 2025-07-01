package fr.dawan.project1.controllers;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.services.CategoryService;
import fr.dawan.project1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
