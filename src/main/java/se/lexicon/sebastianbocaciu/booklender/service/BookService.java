package se.lexicon.sebastianbocaciu.booklender.service;

import se.lexicon.sebastianbocaciu.booklender.dto.BookDto;
import se.lexicon.sebastianbocaciu.booklender.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);
    List<BookDto> findByAvailable(boolean available);
    List<BookDto> findByTitle(String title);
    BookDto findById(int bookId);
    List<BookDto> findAll();
    BookDto create (BookDto bookDto);
    BookDto update (BookDto bookDto);
    boolean delete (int bookId);
}
