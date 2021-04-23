package se.lexicon.sebastianbocaciu.booklender.repository;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.sebastianbocaciu.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findLoansByLoanTakerUserId (int userId);
    List<Loan> findLoansByBook_BookId (int bookId);
    List<Loan> findLoansByTerminatedIgnoreCase (boolean status);
}
