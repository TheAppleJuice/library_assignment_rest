package se.lexicon.sebastianbocaciu.booklender.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LibraryUserDto {
    private int userId;
    private LocalDate regDate;
    private String name;
    private String email;

}
