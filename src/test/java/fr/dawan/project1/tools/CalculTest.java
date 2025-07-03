package fr.dawan.project1.tools;

import org.junit.jupiter.api.*;

public class CalculTest {

    @BeforeEach
    void beforeEach(){

    }

    @AfterEach
    void afterEach(){

    }

    @BeforeAll
    static void beforeAll(){

    }

    @AfterAll
    static void afterAll(){

    }

    @Test
    void testSomme(){
        Assertions.assertEquals(5, Calcul.somme(2,3));
    }

    @Test
    void testSomme2(){
        Assertions.assertNotEquals(5, Calcul.somme(2,4));
    }
}
