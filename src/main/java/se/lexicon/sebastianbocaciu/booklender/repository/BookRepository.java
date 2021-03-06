package se.lexicon.sebastianbocaciu.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sebastianbocaciu.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository <Book, Integer> {

    List<Book> findByAvailable (boolean status);
    List<Book> findByReserved (boolean status);
    List<Book> findBookByTitle (String title);


}
