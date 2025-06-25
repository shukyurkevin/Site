package com.kevin.site.interfaces;

import com.kevin.site.dto.GenreFilmsDto;
import com.kevin.site.dto.GenreSeriesDto;
import com.kevin.site.models.FilmModel;
import java.util.List;

public interface FilmDataAccessInterface <T> {
  public T getById(Long id);
  public List<T> getFilms();
  public List<T> searchFilms(String searchTerm);
  public List<T> searchByGenres(String searchTerm);
  public Long addOne(T newOrder);
  public boolean deleteOne(Long id);
  public T updateOne(Long id, T filmModel);
  public List<FilmModel> getLatestFilms();
  public List<FilmModel> getSeries();
  public List<FilmModel> getOnlyFilms();

  public List<GenreFilmsDto> getFilmsByGenres();

  public List<GenreSeriesDto> getSeriesByGenres();
  public List<FilmModel> getHighestRated();
  public List<FilmModel> getHighestRatedMovies();
  public List<FilmModel> getHighestRatedSeries();

}
