package fr.dawan.project1.controllers;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.SupplierDto;
import fr.dawan.project1.entities.Supplier;
import fr.dawan.project1.services.CategoryService;
import fr.dawan.project1.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController extends GenericController<SupplierDto> {

    private SupplierService service;

    @Autowired
    public SupplierController(SupplierService service) {
        super(service);
        this.service=service;
    }

    @GetMapping(value={"/{page}/{size}/{search}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplierDto> findAllBySearch(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable(value="search", required = false) Optional<String> search) throws Exception {
        if(search.isPresent())
            return service.findAll(page, size, search.get());
        else
            return service.findAll(page, size, null);
    }



}
