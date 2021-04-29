package se.lexicon.sebastianbocaciu.booklender.service;

import se.lexicon.sebastianbocaciu.booklender.dto.LoanDto;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;

import java.util.List;

public interface LoanService {

    LoanDto findById(long loanId) throws DataNotFoundException;
    List<LoanDto> findByBookId(int bookId);
    List<LoanDto> findByUserId (int userId);
    List<LoanDto> findByTerminated (boolean terminated);
    List<LoanDto> findAll ();
    LoanDto create(LoanDto loanDto);
    LoanDto update(LoanDto loanDto) throws DataNotFoundException;
    boolean delete(long loanId);
}
