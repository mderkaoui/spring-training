package fr.dawan.project1.services.impl;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.entities.Category;
import fr.dawan.project1.entities.Product;
import fr.dawan.project1.mappers.CategoryMapper;
import fr.dawan.project1.mappers.ProductMapper;
import fr.dawan.project1.repositories.CategoryRepository;
import fr.dawan.project1.repositories.ProductRepository;
import fr.dawan.project1.services.CategoryService;
import fr.dawan.project1.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryDto> implements CategoryService {

    private CategoryRepository repository;
    private CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        super(repository,mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDto> findAll(int page, int size, String search) throws Exception {
        List<Category> lp = null;
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
