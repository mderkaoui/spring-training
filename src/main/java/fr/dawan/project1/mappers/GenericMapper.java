package fr.dawan.project1.mappers;

public interface GenericMapper<T,TDto> {

    TDto toDto(T entity) throws Exception;

    T toEntity(TDto dto) throws Exception;
}
