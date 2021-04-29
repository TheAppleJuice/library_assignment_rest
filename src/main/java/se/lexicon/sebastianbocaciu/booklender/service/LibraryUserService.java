package se.lexicon.sebastianbocaciu.booklender.service;

import se.lexicon.sebastianbocaciu.booklender.dto.LibraryUserDto;
import se.lexicon.sebastianbocaciu.booklender.entity.LibraryUser;
import se.lexicon.sebastianbocaciu.booklender.exception.DataNotFoundException;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById (int userId) throws DataNotFoundException;
    LibraryUserDto findByEmail (String email);
    List<LibraryUserDto> findAll ();
    LibraryUserDto create (LibraryUserDto libraryUserDto);
    LibraryUserDto update (LibraryUserDto libraryUserDto)throws DataNotFoundException;
    boolean delete (int userId);
}
