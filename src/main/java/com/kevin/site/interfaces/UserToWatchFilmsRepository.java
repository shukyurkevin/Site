package com.kevin.site.interfaces;

import com.kevin.site.entity.UserToWatchFilm;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserToWatchFilmsRepository extends CrudRepository<UserToWatchFilm, Long> {
  UserToWatchFilm findByUserIdAndFilmId(Long UserId, Long FilmId);

  @Query("SELECT film_id FROM user_to_watch_films WHERE user_id = :userId")
  List<Long> findAllFilmIdsByUserId(Long userId);
}
