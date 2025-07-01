package fr.dawan.project1.services;

import fr.dawan.project1.dto.CountDto;

import java.util.List;

public interface GenericService<TDto> {

    List<TDto> findAll(int page, int size) throws Exception;

    TDto saveOrUpdate(TDto dto) throws Exception;

    void delete(long id) throws Exception;

    TDto findById(long id) throws Exception;

    CountDto count() throws Exception;
}
