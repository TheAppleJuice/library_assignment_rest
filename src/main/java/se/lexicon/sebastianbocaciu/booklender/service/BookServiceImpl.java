package se.lexicon.sebastianbocaciu.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.sebastianbocaciu.booklender.dto.BookDto;
import se.lexicon.sebastianbocaciu.booklender.entity.Book;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;
import se.lexicon.sebastianbocaciu.booklender.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

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
        return bookRepository.findBooksByReserved(reserved).stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {

        return bookRepository.findBooksByAvailable(available).stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("Title should not be null");
        return bookRepository.findBookByTitle(title).stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(int bookId) throws DataNotFoundException {
        if (bookId == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()){
            BookDto convertToDto = modelMapper.map(optionalBook.get(), BookDto.class);
            return convertToDto;
        } else throw new DataNotFoundException("BookDto not found");

    }

    @Override
    public List<BookDto> findAll() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        List<BookDto> bookDtoList = bookList
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
        return bookDtoList;
    }

    @Transactional
    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("BookDto should not be null");
        if (bookDto.getBookId() != 0) throw new IllegalArgumentException("Id should be null");
        Book bookEntity = modelMapper.map(bookDto, Book.class);
        Book savedBookToEntity = bookRepository.save(bookEntity);
        BookDto convertBookToDto = modelMapper.map(savedBookToEntity, BookDto.class);
        return convertBookToDto;
    }

    @Transactional
    @Override
    public BookDto update(BookDto bookDto) throws DataNotFoundException {
        if (bookDto == null) throw new IllegalArgumentException("BookDto should not be null");
        if (bookDto.getBookId() == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
        if (optionalBook.isPresent()) {
            Book bookEntity = modelMapper.map(bookDto, Book.class);
            Book savedBookToEntity = bookRepository.save(bookEntity);
            BookDto convertBookToDto = modelMapper.map(savedBookToEntity, BookDto.class);
            return convertBookToDto;

        } else throw new DataNotFoundException("BookDto not found");

    }

    @Override
    public void delete(int bookId) throws DataNotFoundException{
        /*if (bookId == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book bookEntity = modelMapper.map(optionalBook, Book.class);
            bookRepository.delete(bookEntity);
            return true;

        } else throw new IllegalArgumentException("Book not deleted");*/

        bookRepository.delete(modelMapper.map(bookRepository.findById(bookId)
                .orElseThrow(()->new DataNotFoundException("Id ")),Book.class));
    }
}
