package se.lexicon.sebastianbocaciu.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.sebastianbocaciu.booklender.dto.BookDto;
import se.lexicon.sebastianbocaciu.booklender.dto.LibraryUserDto;
import se.lexicon.sebastianbocaciu.booklender.dto.LoanDto;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class LoanServiceImplTest {


    LoanDto loanDto;
    LoanDto loanDto2;
    LibraryUserDto loanTaker;
    BookDto bookDto;

    LoanService testObject;
    BookService testBookService;
    LibraryUserService testLibraryUserService;


    @Autowired
    public void setTestObject(LoanService testObject) {
        this.testObject = testObject;
    }

    @Autowired
    public void setTestBookService(BookService testBookService) {
        this.testBookService = testBookService;
    }

    @Autowired
    public void setTestLibraryUserService(LibraryUserService testLibraryUserService) {
        this.testLibraryUserService = testLibraryUserService;
    }

    @BeforeEach
    void setUp() {

        bookDto = new BookDto();
        bookDto.setDescription("test description");
        bookDto.setMaxLoanDays(60);
        bookDto.setReserved(false);
        bookDto.setAvailable(true);
        bookDto.setTitle("test book title");
        bookDto.setFinePerDay(BigDecimal.TEN);

        testBookService.create(bookDto);

        loanTaker = new LibraryUserDto();
        loanTaker.setName("test library user name");
        loanTaker.setEmail("test@test.se");
        loanTaker.setRegDate(LocalDate.now());

        testLibraryUserService.create(loanTaker);


        loanDto = new LoanDto();
        loanDto.setLoanDate(LocalDate.now());
        loanDto.setLoanTaker(loanTaker);
        loanDto.setBook(bookDto);
        loanDto.setTerminated(true);

        testObject.create(loanDto);
    }

    @Test
    void findById() throws DataNotFoundException {
        assertEquals(1, testObject.findById(1).getLoanId());
    }

    @Test
    void findByBookId() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void findByTerminated() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
        assertEquals(loanTaker, testObject.create(loanDto).getLoanTaker());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}