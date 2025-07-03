package fr.dawan.project1.services;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.exceptions.IdNotFoundException;

import java.util.List;

/**
 * Generic interface for methods
 * @param <TDto>    a data transfer object type
 * @author Mohamed
 * @version 1.0
 */
public interface GenericService<TDto> {

    /**
     * retrieve a paginated listed of dto
     * @param page  zero indexed number
     * @param size  number of elements in one page
     * @return      a list of objects
     * @throws Exception    if error
     */
    List<TDto> findAll(int page, int size) throws Exception;

    /**
     *
     * @param dto
     * @return
     * @throws Exception
     */
    TDto saveOrUpdate(TDto dto) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    void delete(long id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    TDto findById(long id) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    CountDto count() throws Exception;
}
