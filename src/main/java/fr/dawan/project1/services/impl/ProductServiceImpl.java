package fr.dawan.project1.services.impl;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.entities.Product;
import fr.dawan.project1.mappers.ProductMapper;
import fr.dawan.project1.repositories.ProductRepository;
import fr.dawan.project1.services.ProductService;
import fr.dawan.project1.tools.DtoTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl extends GenericServiceImpl<Product,ProductDto> implements ProductService {

    private ProductRepository repository;
    private ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        super(repository,mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> findAll(int page, int size, String search) throws Exception {
        List<Product> lp = null;
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
