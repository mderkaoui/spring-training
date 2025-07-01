package fr.dawan.project1.services;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.SupplierDto;

import java.util.List;

public interface SupplierService extends GenericService<SupplierDto> {

    List<SupplierDto> findAll(int page, int size, String search) throws Exception;


}
