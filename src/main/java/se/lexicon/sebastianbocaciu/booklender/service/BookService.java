package se.lexicon.sebastianbocaciu.booklender.service;

import se.lexicon.sebastianbocaciu.booklender.dto.BookDto;
import se.lexicon.sebastianbocaciu.booklender.entity.Book;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);
    List<BookDto> findByAvailable(boolean available);
    List<BookDto> findByTitle(String title);
    BookDto findById(int bookId) throws DataNotFoundException;
    List<BookDto> findAll();
    BookDto create (BookDto bookDto);
    BookDto update (BookDto bookDto) throws DataNotFoundException;
    boolean delete (int bookId);
}
