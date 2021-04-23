package se.lexicon.sebastianbocaciu.booklender.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

}
