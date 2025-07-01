package fr.dawan.project1.tools;

import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.entities.Product;
import org.modelmapper.ModelMapper;

public class DtoTools {

    private static ModelMapper myMapper = new ModelMapper();

    public static <TSource,TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) throws Exception {
        //Ajouter des règles personnalisées si besoin
        return myMapper.map(obj, clazz);
    }
}
