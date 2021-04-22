package se.lexicon.sebastianbocaciu.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.sebastianbocaciu.booklender.entity.LibraryUser;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
}
