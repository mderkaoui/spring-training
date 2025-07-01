package fr.dawan.project1.services;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.dto.ProductDto;

import java.util.List;

public interface ProductService extends GenericService<ProductDto> {

    List<ProductDto> findAll(int page, int size, String search) throws Exception;


}
