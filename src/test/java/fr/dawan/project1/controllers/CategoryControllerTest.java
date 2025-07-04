package fr.dawan.project1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.project1.dto.CategoryDto;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAll() throws Exception {
        //restTemplate.getForEntity("/api/v1/categories")
        //ou mockMvc.perform(get("/api/v1/categories")

        mockMvc.perform(get("/api/v1/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(3)))
                .andExpect(jsonPath("$[0].name", CoreMatchers.is("Mobilier de bureau")));

    }

    @Test
    void testFindById() throws Exception {
        String rep = mockMvc.perform(get("/api/v1/categories/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        CategoryDto dto = objectMapper.readValue(rep,CategoryDto.class);
        Assertions.assertEquals("Mobilier de bureau", dto.getName());
    }
}
