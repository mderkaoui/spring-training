package fr.dawan.project1.repositories;

import fr.dawan.project1.entities.Product;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //req utilisant les règles de nommage de Spring DATA JPA
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    //req JP-QL : https://docs.oracle.com/cd/E17904_01/apirefs.1111/e13946/ejb3_langref.html
    //https://docs.redhat.com/fr/documentation/JBoss_Enterprise_Application_Platform/5/html/hibernate_core_reference_guide/queryhql
    @Query(value = "SELECT p FROM Product p WHERE p.name LIKE :name")
    List<Product> searchByName(@Param("name") String name, Pageable pageable);

    //Requête avec du SQL natif
    @Query(value = "SELECT * FROM t_products p WHERE p.name LIKE :name", nativeQuery = true)
    List<Product> searchByName2(@Param("name") String name, Pageable pageable);

    @Query(value = "FROM Product p WHERE p.qtyInStock < :min")
    List<Product> findOutOfStock(@Param("min") int min);
}
