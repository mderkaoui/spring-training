package fr.dawan.project1.services.impl;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.entities.Product;
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
public class ProductServiceImplOld  { //implements ProductService
/*
    private ProductRepository repository;

    @Autowired
    public ProductServiceImplOld(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductDto> findAll(int page, int size, String search) throws Exception {
        List<Product> lp = null;
        if(search==null)
            lp = repository.findAll(PageRequest.of(page,size)).toList();
        else
            lp = repository.findAllByNameContainingIgnoreCase(search, PageRequest.of(page,size));

        //conversion d'entities vers DTO
        return lp.stream().map(p->{
            ProductDto d = null;
            try {
                d = DtoTools.convert(p,ProductDto.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return d;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto dto) throws Exception {
        Product p= repository.saveAndFlush(DtoTools.convert(dto, Product.class));
        return DtoTools.convert(p,ProductDto.class);
    }

    @Override
    public void delete(long id) throws Exception {
        repository.deleteById(id);

    }

    @Override
    public ProductDto findById(long id) throws Exception {
        Optional<Product> opt = repository.findById(id);
        if(opt.isPresent())
            return DtoTools.convert(opt.get(), ProductDto.class);

        return null;
    }

    @Override
    public CountDto count()  throws Exception{
        long res = repository.count();
        CountDto result = new CountDto();
        result.setResult(res);
        return result;
    }
    */

}
