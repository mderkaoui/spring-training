package fr.dawan.project1.services.impl;

import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.services.GenericService;
import fr.dawan.project1.mappers.GenericMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GenericServiceImpl<T,TDto> implements GenericService<TDto> {

    private final JpaRepository<T, Long> repository;
    private final GenericMapper<T,TDto> mapper;

    public GenericServiceImpl(JpaRepository<T, Long> repository, GenericMapper<T, TDto> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<TDto> findAll(int page, int size) throws Exception {
        return repository.findAll(PageRequest.of(page,size)).map(obj -> {
            try {
                return mapper.toDto(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public TDto saveOrUpdate(TDto dto) throws Exception {
        T entity = mapper.toEntity(dto);
        T obj = repository.saveAndFlush(entity);
        return mapper.toDto(obj);
    }

    @Override
    public void delete(long id) throws Exception {
        if(repository.existsById(id))
            repository.deleteById(id);
        else throw new Exception("Id not found");
    }

    @Override
    public TDto findById(long id) throws Exception {
        return mapper.toDto(repository.findById(id).orElseThrow(()->new Exception("Id not found")));
    }

    @Override
    public CountDto count() throws Exception {
        long res = repository.count();
        CountDto result = new CountDto();
        result.setResult(res);
        return result;
    }
}