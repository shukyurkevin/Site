package com.kevin.site.interfaces;

import com.kevin.site.entity.FilmEntity;
import com.kevin.site.models.FilmModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface FilmRepositoryInterface extends CrudRepository<FilmEntity, Long> {

  List<FilmEntity> findByTitleContainingIgnoreCase(String title);
  Long save(FilmModel newFilm);
  default boolean deleteFilmById(Long id) {
    if (existsById(id)) {
      deleteById(id);
      return true;
    }
    return false;
  }
  List<FilmEntity> findTop6ByOrderByIdDesc();

}
