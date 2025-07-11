package com.kevin.site.services;

import com.kevin.site.dto.GenreFilmsDto;
import com.kevin.site.dto.GenreSeriesDto;
import com.kevin.site.models.FilmModel;
import java.util.List;

public interface FilmServiceInterface {
  public void test();
  public void init();
  public void destroy();

  public List<FilmModel> getFilms();
  public List<FilmModel> searchFilms(String searchTerm);
  public List<FilmModel> searchByGenres(String searchTerm);
  public Long addOne(FilmModel newFilm);
  public boolean deleteOne(Long id);
  public FilmModel updateOne(Long id, FilmModel newOrder);
  public FilmModel getById(Long id);
  public List<FilmModel> getLatestFilms();
  public List<FilmModel> getSeries();
  public List<FilmModel> getOnlyFilms();
  public List<GenreFilmsDto> getFilmsByGenres();
  public List<GenreSeriesDto> getSeriesByGenres();
  public List<FilmModel> getHighestRated();
  public List<FilmModel> getHighestRatedMovies();
  public List<FilmModel> getHighestRatedSeries();
}
