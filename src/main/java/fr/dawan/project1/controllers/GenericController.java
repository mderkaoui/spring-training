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

public class GenericController<TDto> {

    private final GenericService<TDto> service;

    public GenericController(GenericService<TDto> service) {
        this.service = service;
    }

    @GetMapping(value={"/{page}/{size}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TDto> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size) throws Exception {
        return service.findAll(page, size);
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public TDto findById(@PathVariable("id") long id) throws Exception {
        return service.findById(id);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TDto> save(@RequestBody TDto p) throws Exception {
        TDto dto = service.saveOrUpdate(p);
        return ResponseEntity.status(HttpStatus.CREATED) //201
                              .body(dto);
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TDto update(@RequestBody TDto p) throws Exception {
        return service.saveOrUpdate(p);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remove(@PathVariable("id") long id) throws Exception {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value="/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public CountDto count() throws Exception {
        return service.count();
    }

}
