package com.kevin.site.dto;

import com.kevin.site.entity.FilmEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreFilmsDto {

  private String genre;
  private List<FilmEntity> films;

  public GenreFilmsDto(String genre, List<FilmEntity> films) {
    this.genre = genre;
    this.films = films;
  }


}
