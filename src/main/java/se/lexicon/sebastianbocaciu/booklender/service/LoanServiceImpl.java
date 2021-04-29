package se.lexicon.sebastianbocaciu.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.sebastianbocaciu.booklender.dto.LoanDto;
import se.lexicon.sebastianbocaciu.booklender.entity.Loan;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;
import se.lexicon.sebastianbocaciu.booklender.repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class LoanServiceImpl implements LoanService {

    LoanRepository loanRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public LoanDto findById(long loanId) throws DataNotFoundException {
        if (loanId == 0) throw new IllegalArgumentException("Id should not be null");
        return modelMapper.map(loanRepository.findById(loanId)
                .orElseThrow(() -> new DataNotFoundException("LoanDto not found")), LoanDto.class);
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        if (bookId == 0) throw new IllegalArgumentException("Id should not be null");
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findLoansByBook_BookId(bookId).iterator().forEachRemaining(loanList::add);
        List<LoanDto> loanDtoList = loanList
                .stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        if (userId == 0) throw new IllegalArgumentException("Id should not be null");
        return loanRepository.findLoansByLoanTakerUserId(userId)
                .stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findByTerminated(boolean terminated) {
        return loanRepository.findLoansByTerminated(terminated)
                .stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        List<LoanDto> loanDtoList = loanList
                .stream()
                .map(loan -> modelMapper.map(loan, LoanDto.class))
                .collect(Collectors.toList());
        return loanDtoList;
    }

    @Transactional
    @Override
    public LoanDto create(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("LoanDto should not be null");
        if (loanDto.getLoanId() != 0) throw new IllegalArgumentException("Id should  be null");
        Loan loanEntity = modelMapper.map(loanDto, Loan.class);
        Loan savedToEntity = loanRepository.save(loanEntity);
        LoanDto convertToDto = modelMapper.map(savedToEntity, LoanDto.class);
        return convertToDto;
    }

    @Transactional
    @Override
    public LoanDto update(LoanDto loanDto) throws DataNotFoundException {
        if (loanDto == null) throw new IllegalArgumentException("LoanDto should not be null");
        if (loanDto.getLoanId() == 0) throw new IllegalArgumentException("Id should not be null");

        return null;
    }

    @Override
    public boolean delete(long loanId) {
        if (loanId==0) throw new IllegalArgumentException("Id should not be null");
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()){
            Loan loanEntity = modelMapper.map(optionalLoan, Loan.class);
            loanRepository.delete(loanEntity);
            return true;
        } else throw new IllegalArgumentException("Loan not found");

    }
}
