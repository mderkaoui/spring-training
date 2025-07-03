package fr.dawan.project1.services;

import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.CountDto;
import fr.dawan.project1.entities.Category;
import fr.dawan.project1.repositories.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @MockitoBean
    private CategoryRepository repository;

    private static List<Category> categories;
    @BeforeAll
    public static void beforeAll() throws Exception{
        categories = new ArrayList<>();
        categories.add(new Category("cat1",null));
        categories.add(new Category("cat2",null));
        categories.add(new Category("cat3",null));

    }


    @Test
    void testCount()  {
        try {
            CountDto dto = new CountDto();
            dto.setResult(3L);
            Assertions.assertEquals(dto, service.count());
        }catch (Exception ex){
            ex.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void testSave() throws Exception {

        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(categories.get(0)));

        CategoryDto dto = new CategoryDto();
        //....
        CategoryDto savedDto = service.saveOrUpdate(dto);
        CategoryDto objInDb = service.findById(savedDto.getId());
        Assertions.assertNotNull(objInDb);
        //faire les vérifications

        //supprimer celui qui est inséré
        service.delete(savedDto.getId());
    }
}
