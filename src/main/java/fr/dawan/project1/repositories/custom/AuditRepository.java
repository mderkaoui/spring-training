package fr.dawan.project1.repositories.custom;

import java.util.List;

public interface AuditRepository<T> {
    List<T> getRevisions(Long id);
}
