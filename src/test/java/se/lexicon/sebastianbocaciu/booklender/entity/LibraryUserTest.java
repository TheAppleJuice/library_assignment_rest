package se.lexicon.sebastianbocaciu.booklender.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LibraryUserTest {

    LibraryUser testObject;

    @BeforeEach
    public void setup(){
        testObject = new LibraryUser();
        testObject.setName("Test User");
        testObject.setRegDate(LocalDate.now());
        testObject.setEmail("test@test.se");
    }

    @Test
    public void test_create(){
        Assertions.assertEquals("Test User", testObject.getName());
        Assertions.assertEquals(LocalDate.now(), testObject.getRegDate());
    }

    @Test
    public void test_hashCode(){
        LibraryUser expectedResult= new LibraryUser();
        expectedResult.setName("Test User");
        expectedResult.setRegDate(LocalDate.now());
        expectedResult.setEmail("test@test.se");

        Assertions.assertEquals(expectedResult.hashCode(), testObject.hashCode());

    }
}
