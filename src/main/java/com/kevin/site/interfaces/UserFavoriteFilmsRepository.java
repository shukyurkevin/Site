package com.kevin.site.interfaces;

import com.kevin.site.entity.UserFavoriteFilm;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserFavoriteFilmsRepository extends CrudRepository<UserFavoriteFilm, Long> {
 UserFavoriteFilm findByUserIdAndFilmId(Long UserId,Long FilmId);

 @Query("SELECT film_id FROM user_favorite_films WHERE user_id = :userId")
 List<Long> findAllFilmIdsByUserId(Long userId);
}