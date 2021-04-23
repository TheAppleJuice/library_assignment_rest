package se.lexicon.sebastianbocaciu.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sebastianbocaciu.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository <Book, Integer> {

    List<Book> findBooksByAvailable (boolean status);
    List<Book> findBooksByReserved (boolean status);
    List<Book> findBookByTitle (String title);


}
