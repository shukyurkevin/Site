package com.kevin.site.interfaces;

import com.kevin.site.entity.FilmEntity;
import com.kevin.site.models.FilmModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface FilmRepository extends CrudRepository<FilmEntity, Long> {

  List<FilmEntity> findByTitleContainingIgnoreCase(String title);
  List<FilmEntity> findByGenresContainingIgnoreCase(String genres);

  List<FilmEntity> findByTypeContainingIgnoreCaseAndGenresContainingIgnoreCase(String type, String genres);
  Long save(FilmModel newFilm);
  default boolean deleteFilmById(Long id) {
    if (existsById(id)) {
      deleteById(id);
      return true;
    }
    return false;
  }
  List<FilmEntity> findAllByOrderByIdDesc();
  List<FilmEntity> findByTypeContainingIgnoreCase(String type);

}
