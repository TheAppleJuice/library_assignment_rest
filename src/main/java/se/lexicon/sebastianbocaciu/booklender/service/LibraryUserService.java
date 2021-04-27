package se.lexicon.sebastianbocaciu.booklender.service;

import se.lexicon.sebastianbocaciu.booklender.dto.LibraryUserDto;
import se.lexicon.sebastianbocaciu.booklender.entity.LibraryUser;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById (int userId);
    LibraryUserDto findByEmail (String email);
    List<LibraryUserDto> findAll ();
    LibraryUserDto create (LibraryUserDto libraryUserDto);
    LibraryUserDto update (LibraryUserDto libraryUserDto);
    boolean delete (int userId);
}
