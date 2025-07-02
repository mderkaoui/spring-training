package fr.dawan.project1.repositories.custom;

import fr.dawan.project1.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MyProductCustomRepoImpl implements MyProductCustomRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByFilters(Map<String, String> filters) {
        StringBuilder jpql = new StringBuilder("FROM Product p WHERE 1 ");

        for(String key : filters.keySet()){
            if(key!=null) {
                jpql.append("AND "+ key + "LIKE %:"+ key + "% ");
            }
        }
        Query q = entityManager.createQuery(jpql.toString());
        for(String key : filters.keySet()){
            if(key!=null) {
                q.setParameter(key,filters.get(key));
            }
        }
        return q.getResultList();
    }
}
