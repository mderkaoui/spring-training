package fr.dawan.project1.services.impl;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.SupplierDto;
import fr.dawan.project1.entities.Category;
import fr.dawan.project1.entities.Supplier;
import fr.dawan.project1.mappers.CategoryMapper;
import fr.dawan.project1.mappers.SupplierMapper;
import fr.dawan.project1.repositories.CategoryRepository;
import fr.dawan.project1.repositories.SupplierRepository;
import fr.dawan.project1.services.CategoryService;
import fr.dawan.project1.services.SupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl extends GenericServiceImpl<Supplier, SupplierDto> implements SupplierService {

    private SupplierRepository repository;
    private SupplierMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository repository, SupplierMapper mapper) {
        super(repository,mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<SupplierDto> findAll(int page, int size, String search) throws Exception {
        List<Supplier> lp = null;
        if(search==null)
            lp = repository.findAll(PageRequest.of(page,size)).toList();
        else
            lp = repository.findAllByNameContainingIgnoreCase(search, PageRequest.of(page,size));

        //conversion d'entities vers DTO
        return lp.stream().map(obj -> {
            try {
                return mapper.toDto(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }


}
