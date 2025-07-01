package fr.dawan.project1.repositories;

import fr.dawan.project1.entities.Product;
import fr.dawan.project1.entities.Supplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("FROM Supplier s JOIN FETCH s.products WHERE s.id= :id")
    Optional<Supplier> findById(@Param("id") long id);

}
/*
{
    "id":1,
        "name":"fournisseur 1",
        "products":[
        {
            "id":12,
            "name":"table",
            "price":300
        },
        {
        "id":15,
        "name":"table",
        "price":50
        }
       ]

}

TO DTO :
        {
        "id":1,
        "name":"fournisseur 1",
        "productsId":[12,15]
        }
*/




