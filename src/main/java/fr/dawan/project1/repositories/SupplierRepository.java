package fr.dawan.project1.repositories;

import fr.dawan.project1.dto.SupplierProdDto;
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

    @Query("SELECT new SupplierProdDto(s.name, p.name) FROM Supplier s JOIN FETCH s.products p")
    List<SupplierProdDto> findSpecific();

}


