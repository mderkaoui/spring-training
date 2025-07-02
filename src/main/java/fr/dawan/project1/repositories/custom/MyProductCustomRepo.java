package fr.dawan.project1.repositories.custom;

import fr.dawan.project1.entities.Product;

import java.util.List;
import java.util.Map;

public interface MyProductCustomRepo {

    List<Product> findByFilters(Map<String, String> filters);
}
