package fr.dawan.project1.repositories.custom;
import jakarta.persistence.EntityManager;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuditRepositoryImpl<T> implements AuditRepository<T> {

    @Autowired
    private EntityManager entityManager;

    private final Class<T> entityType;

    public AuditRepositoryImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public List<T> getRevisions(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        return auditReader.createQuery()
                .forRevisionsOfEntity(entityType, false, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();
    }
}
