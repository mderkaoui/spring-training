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

/**
 * JPA Repository for Supplier
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Search by name with pagination support
     * @param name              a supplier name
     * @param pageable          for pagination
     * @return                  a list of {@See Supplier}
     *
     */
    List<Supplier> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("FROM Supplier s JOIN FETCH s.products WHERE s.id= :id")
    Optional<Supplier> findById(@Param("id") long id);

    @Query("SELECT new fr.dawan.project1.dto.SupplierProdDto(s.name, p.name) FROM Supplier s INNER JOIN s.products p")
    List<SupplierProdDto> findSpecific();

}


