package fr.dawan.project1.mappers;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.SupplierDto;
import fr.dawan.project1.entities.Category;
import fr.dawan.project1.entities.Supplier;
import fr.dawan.project1.tools.DtoTools;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SupplierMapper implements GenericMapper<Supplier, SupplierDto>{

    @Override
    public SupplierDto toDto(Supplier entity) throws Exception {
        SupplierDto dto = DtoTools.convert(entity,SupplierDto.class);
        Set<Long> idsSet = entity.getProducts().stream().map(obj->obj.getId()).collect(Collectors.toSet());
        dto.setProductsId(idsSet);
        return dto;
    }

    @Override
    public Supplier toEntity(SupplierDto dto) throws Exception {
        return DtoTools.convert(dto,Supplier.class);
    }
}
