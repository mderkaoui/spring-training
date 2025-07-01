package fr.dawan.project1.controllers;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.services.GenericService;
import fr.dawan.project1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends GenericController<ProductDto> {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService service) {
        super(service);
        this.productService=service;
    }

    // Paramètre d'url non nommé : GET /api/v1/products/1/50 ou /api/v1/products/1/50/table
    @GetMapping(value={"/{page}/{size}/{search}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> findAllBySearch(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable(value="search", required = false) Optional<String> search) throws Exception {
        if(search.isPresent())
            return productService.findAll(page, size, search.get());
        else
            return productService.findAll(page, size, null);
    }



}
