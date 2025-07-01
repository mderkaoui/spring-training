package fr.dawan.project1.repositories;

import fr.dawan.project1.entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //req utilisant les règles de nommage de Spring DATA JPA
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<Category> findAllByNameContainingIgnoreCase(String name, Pageable pageable);



}
