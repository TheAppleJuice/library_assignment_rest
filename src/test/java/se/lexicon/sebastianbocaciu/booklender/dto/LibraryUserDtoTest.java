package se.lexicon.sebastianbocaciu.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryUserDtoTest {

    LibraryUserDto testObject;


    @BeforeEach
    void setUp() {
        testObject = new LibraryUserDto();
        testObject.setName("test name");
        testObject.setEmail("test@test.se");
        testObject.setRegDate(LocalDate.now());
    }

    @Test
    public void create (){
        assertEquals("test name",testObject.getName());
    }

    @Test
    public void testHashCode() {
        LibraryUserDto actual = new LibraryUserDto();
        actual.setName("test name");
        actual.setEmail("test@test.se");
        actual.setRegDate(LocalDate.now());
        assertEquals(actual.hashCode(),testObject.hashCode());
    }
}