package fr.dawan.project1.mappers;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.entities.Category;
import fr.dawan.project1.entities.Product;
import fr.dawan.project1.tools.DtoTools;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements GenericMapper<Category, CategoryDto>{

    @Override
    public CategoryDto toDto(Category entity) throws Exception {
        return DtoTools.convert(entity,CategoryDto.class);
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) throws Exception {
        return DtoTools.convert(categoryDto,Category.class);
    }
}
