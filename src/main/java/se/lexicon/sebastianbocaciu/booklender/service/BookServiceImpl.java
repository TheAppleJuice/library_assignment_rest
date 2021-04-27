package se.lexicon.sebastianbocaciu.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.sebastianbocaciu.booklender.dto.BookDto;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;
import se.lexicon.sebastianbocaciu.booklender.repository.BookRepository;

import java.util.List;

public class BookServiceImpl implements BookService{

    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return null;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return null;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        return null;
    }

    @Override
    public BookDto findById(int bookId) throws DataNotFoundException {
        if (bookId == 0) throw new IllegalArgumentException("Id should not be null");
        return modelMapper.map(bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException("BookDto not found")),BookDto.class);
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException();
        if (bookDto.getBookId() != 0) throw new IllegalArgumentException();
        //TODO: finish method implementation
        return null;
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return null;
    }

    @Override
    public boolean delete(int bookId) {
        return false;
    }
}
