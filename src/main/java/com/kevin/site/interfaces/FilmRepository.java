package com.kevin.site.interfaces;

import com.kevin.site.entity.FilmEntity;
import com.kevin.site.models.FilmModel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.data.repository.CrudRepository;



public interface FilmRepository extends CrudRepository<FilmEntity, Long> {

  List<FilmEntity> findByTitleContainingIgnoreCase(String title);
  default List<FilmEntity> findByGenresIgnoreSpace(String genres) {
    String normalized = genres.replace(" ", "").toLowerCase();
    Iterable<FilmEntity> allFilms = this.findAll();
    Stream<FilmEntity> stream = StreamSupport.stream(allFilms.spliterator(), false);

    return stream.filter(film -> film.getGenres() != null &&
            film.getGenres().replace(" ", "").toLowerCase().contains(normalized))
        .collect(Collectors.toList());
  }

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
