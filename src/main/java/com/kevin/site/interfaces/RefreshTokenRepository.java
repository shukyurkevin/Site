package com.kevin.site.interfaces;

import com.kevin.site.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);
  void deleteByUserId(Long userId);
  void deleteAllByUserId(Long userId);
@Modifying
@Query("DELETE FROM refresh_tokens rt where rt.token = :token")
  void deleteRefreshTokenByToken(String token);

}
