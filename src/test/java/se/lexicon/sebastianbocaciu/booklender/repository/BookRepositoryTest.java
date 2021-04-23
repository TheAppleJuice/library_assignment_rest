package se.lexicon.sebastianbocaciu.booklender.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.sebastianbocaciu.booklender.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    Book testBook;
    Book testBook2;

    BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    public void setUp() {
        testBook = new Book();
        testBook.setTitle("Test title");
        testBook.setAvailable(true);
        testBook.setReserved(false);
        testBook.setMaxLoanDays(60);
        testBook.setFinePerDay(BigDecimal.ONE);
        testBook.setDescription("Test description");

        bookRepository.save(testBook);

        testBook2 = new Book();
        testBook2.setTitle("Test title 2");
        testBook2.setAvailable(false);
        testBook2.setReserved(true);
        testBook2.setMaxLoanDays(60);
        testBook2.setFinePerDay(BigDecimal.ONE);
        testBook2.setDescription("Test description 2");

        bookRepository.save(testBook2);

    }

    @Test
    public void test_findById (){
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        Integer expectedId = bookList.get(0).getBookId();
        Optional<Book> actualId= bookRepository.findById(expectedId);

        assertEquals("Test title", actualId.get().getTitle());
    }

    @Test
    public void test_findAll(){
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);

        assertEquals(2, bookList.size());
    }

    @Test
    public void test_delete(){
        List<Book> bookList = new ArrayList<>();
        bookRepository.delete(testBook);
        bookRepository.delete(testBook2);
        List<Book> emptyList= new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        assertEquals(emptyList,bookList);


    }

    @Test
    void test_findBooksByAvailable() {
        assertEquals(60, bookRepository.findBooksByAvailable(true).get(0).getMaxLoanDays());
    }

    @Test
    void test_findBooksByReserved() {
        assertEquals(60, bookRepository.findBooksByReserved(false).get(0).getMaxLoanDays());
    }

    @Test
    void test_findBookByTitle() {
        assertEquals(60, bookRepository.findBookByTitle("Test title").get(0).getMaxLoanDays());
    }
}