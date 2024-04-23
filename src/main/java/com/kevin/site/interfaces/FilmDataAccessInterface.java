package com.kevin.site.interfaces;

import java.util.List;

public interface FilmDataAccessInterface <T> {
  public T getById(Long id);
  public List<T> getFilms();
  public List<T> searchFilms(String searchTerm);
  public Long addOne(T newOrder);
  public boolean deleteOne(Long id);
  public T updateOne(Long id, T filmModel);

}
