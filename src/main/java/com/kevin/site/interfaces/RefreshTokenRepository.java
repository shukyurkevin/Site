package com.kevin.site.interfaces;

import com.kevin.site.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);
  void deleteByUserId(Long userId);
  void deleteAllByUserId(Long userId);


}
