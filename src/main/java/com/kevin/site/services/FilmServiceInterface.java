package com.kevin.site.services;

import com.kevin.site.models.FilmModel;
import java.util.List;

public interface FilmServiceInterface {
  public void test();
  public void init();
  public void destroy();

  public List<FilmModel> getFilms();
  public List<FilmModel> searchFilms(String searchTerm);
  public Long addOne(FilmModel newFilm);
  public boolean deleteOne(Long id);
  public FilmModel updateOne(Long id, FilmModel newOrder);
  public FilmModel getById(Long id);
}
