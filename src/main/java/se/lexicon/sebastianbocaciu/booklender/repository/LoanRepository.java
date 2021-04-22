package se.lexicon.sebastianbocaciu.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sebastianbocaciu.booklender.entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {
}
