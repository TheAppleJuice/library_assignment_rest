package se.lexicon.sebastianbocaciu.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.sebastianbocaciu.booklender.dto.BookDto;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;
import se.lexicon.sebastianbocaciu.booklender.repository.BookRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    BookService testObject;
    BookDto bookDto;
    BookDto bookDto2;

    @Autowired
    public void setTestObject(BookService testObject) {
        this.testObject = testObject;
    }

    @BeforeEach
    void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("Test title");
        bookDto.setAvailable(true);
        bookDto.setReserved(false);
        bookDto.setFinePerDay(BigDecimal.TEN);
        bookDto.setMaxLoanDays(60);
        bookDto.setDescription("Test description");

        testObject.create(bookDto);

        bookDto2 = new BookDto();
        bookDto2.setTitle("Test title2");
        bookDto2.setAvailable(false);
        bookDto2.setReserved(true);
        bookDto2.setFinePerDay(BigDecimal.TEN);
        bookDto2.setMaxLoanDays(60);
        bookDto2.setDescription("Test description 2");

        testObject.create(bookDto2);
    }


    @Test
    void findByReserved() {
        assertEquals(bookDto.getTitle(), testObject.findByReserved(false).get(0).getTitle());
    }

    @Test
    void findByAvailable() {
        assertEquals(bookDto.getTitle(), testObject.findByAvailable(true).get(0).getTitle());

    }

    @Test
    void findByTitle() {
        assertEquals(bookDto.getDescription(), testObject.findByTitle("Test title").get(0).getDescription());
    }

    @Test
    public void findById()  {
        try {
            assertEquals(bookDto.getTitle(), testObject.findById(testObject.findAll().get(0).getBookId()).getTitle());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());

    }

    @Test
    void create() {
        assertEquals("Test title", testObject.create(bookDto).getTitle());
    }

    @Test
    void update() throws DataNotFoundException {
        bookDto.setBookId(1);
        bookDto.setDescription("Updated description");
        assertEquals("Updated description", testObject.update(bookDto).getDescription());
    }

    @Test
    void delete() throws DataNotFoundException {
       assertEquals(2, testObject.findAll().size());
        testObject.delete(1);
        assertEquals(1, testObject.findAll().size());

    }
}