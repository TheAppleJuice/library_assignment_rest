package se.lexicon.sebastianbocaciu.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanDtoTest {

    LoanDto testObject;
    BookDto bookDto;
    LibraryUserDto libraryUserDto;

    LoanDto actualTestObject;
    BookDto actualBookDto;
    LibraryUserDto actualLibraryUserDto;


    @BeforeEach
    void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("Test title");
        bookDto.setAvailable(true);
        bookDto.setReserved(false);
        bookDto.setFinePerDay(BigDecimal.TEN);
        bookDto.setMaxLoanDays(60);
        bookDto.setDescription("Test description");

        testObject = new LoanDto();
        testObject.setBook(bookDto);
        testObject.setLoanTaker(libraryUserDto);
        testObject.setLoanDate(LocalDate.now());
        testObject.setTerminated(false);
    }

    @Test
    public void create (){
        assertEquals(60, testObject.getBook().getMaxLoanDays());
        assertEquals(libraryUserDto, testObject.getLoanTaker());

    }

    @Test
    void testHashCode() {

        actualBookDto = new BookDto();
        actualBookDto.setTitle("Test title");
        actualBookDto.setAvailable(true);
        actualBookDto.setReserved(false);
        actualBookDto.setFinePerDay(BigDecimal.TEN);
        actualBookDto.setMaxLoanDays(60);
        actualBookDto.setDescription("Test description");

        actualTestObject = new LoanDto();
        actualTestObject.setBook(actualBookDto);
        actualTestObject.setLoanTaker(actualLibraryUserDto);
        actualTestObject.setLoanDate(LocalDate.now());
        actualTestObject.setTerminated(false);

        assertEquals(actualTestObject.hashCode(),testObject.hashCode());


    }
}