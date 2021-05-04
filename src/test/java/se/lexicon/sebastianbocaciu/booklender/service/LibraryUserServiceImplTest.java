package se.lexicon.sebastianbocaciu.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.sebastianbocaciu.booklender.dto.LibraryUserDto;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;
import se.lexicon.sebastianbocaciu.booklender.repository.LibraryUserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryUserServiceImplTest {

    LibraryUserService testObject;
    LibraryUserDto libraryUserDto;
    LibraryUserDto libraryUserDto2;


    @Autowired
    public void setTestObject(LibraryUserService testObject) {
        this.testObject = testObject;
    }

    @BeforeEach
    void setUp() {
        libraryUserDto = new LibraryUserDto();
        libraryUserDto.setEmail("Sebbe@test.se");
        libraryUserDto.setRegDate(LocalDate.now());
        libraryUserDto.setName("test name");

        libraryUserDto2 = new LibraryUserDto();
        libraryUserDto2.setEmail("test2@test.se");
        libraryUserDto2.setRegDate(LocalDate.now());
        libraryUserDto2.setName("test name 2");

        testObject.create(libraryUserDto);
        testObject.create(libraryUserDto2);

    }

    //todo: titta över varför vissa test inte fungerar.

    @Test
    void findById() throws DataNotFoundException {
        assertEquals("test name", testObject.findById(1).getName());
    }

    @Test
    void findByEmail() {
        assertEquals("test name", testObject.findByEmail("Sebbe@test.se"));

    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
    }

    @Test
    void create() {
        assertEquals("test@test.se", testObject.create(libraryUserDto).getEmail());
    }

    @Test
    void update() throws DataNotFoundException {
        libraryUserDto.setUserId(1);
        libraryUserDto.setName("updated name");
        assertEquals("updated name", testObject.update(libraryUserDto).getName());
    }

    @Test
    void delete() {
        testObject.create(libraryUserDto2);

        assertEquals(2, testObject.findAll().size());
        testObject.delete(2);
        assertEquals(1, testObject.findAll().size());
    }
}