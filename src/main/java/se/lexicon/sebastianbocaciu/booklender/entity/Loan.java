package se.lexicon.sebastianbocaciu.booklender.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
//@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    private LibraryUser loanTaker;
    private Book book;
    private LocalDate loanDate;
    private boolean terminated;


}
