package se.lexicon.sebastianbocaciu.booklender.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BookTest {

    Book testObject;

    @BeforeEach
    public void setup() {
        testObject = new Book();
        testObject.setTitle("Test title");
        testObject.setAvailable(true);
        testObject.setReserved(false);
        testObject.setMaxLoanDays(60);
        testObject.setFinePerDay(BigDecimal.ONE);
        testObject.setDescription("Test description");
    }

    @Test
    public void test_create() {
        Assertions.assertEquals("Test title", testObject.getTitle());
        Assertions.assertEquals(true, testObject.isAvailable());
        Assertions.assertEquals(60, testObject.getMaxLoanDays());

    }

    @Test
    public void test_hashCode() {
        Book expectedResult = new Book();
        expectedResult.setTitle("Test title");
        expectedResult.setAvailable(true);
        expectedResult.setReserved(false);
        expectedResult.setMaxLoanDays(60);
        expectedResult.setFinePerDay(BigDecimal.ONE);
        expectedResult.setDescription("Test description");

        Assertions.assertEquals(expectedResult.hashCode(), testObject.hashCode());

    }

}
