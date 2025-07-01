package fr.dawan.project1.mappers;

import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.entities.Product;
import fr.dawan.project1.repositories.CategoryRepository;
import fr.dawan.project1.tools.DtoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements GenericMapper<Product, ProductDto>{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto toDto(Product entity) throws Exception {
        return DtoTools.convert(entity,ProductDto.class);
    }

    @Override
    public Product toEntity(ProductDto productDto) throws Exception {
        Product p = DtoTools.convert(productDto,Product.class);
        p.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
        return p;
    }
}
