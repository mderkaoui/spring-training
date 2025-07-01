package fr.dawan.project1.controllers;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/products")
public class ProductControllerOld {

    @Autowired
    private ProductService productService;

    // Paramètre d'url non nommé : GET /api/v1/products/1/50 ou /api/v1/products/1/50/table
    @GetMapping(value={"/{page}/{size}","/{page}/{size}/{search}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable(value="search", required = false) Optional<String> search) throws Exception {
        if(search.isPresent())
            return productService.findAll(page, size, search.get());
        else
            return productService.findAll(page, size, null);
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public ProductDto findById(@PathVariable("id") long id) throws Exception {
        return productService.findById(id);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto p) throws Exception { //id, version, fullDescription, price
        return ResponseEntity.status(HttpStatus.CREATED) //201
                              .body(productService.saveOrUpdate(p));
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto update(@RequestBody ProductDto p) throws Exception { //id, version, fullDescription, price
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remove(@PathVariable("id") long id) throws Exception {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value="/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public CountDto count() throws Exception {
        return productService.count();
    }

}
