package com.kevin.site.services;


import com.kevin.site.entity.RefreshToken;
import com.kevin.site.interfaces.RefreshTokenRepository;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
  public final RefreshTokenRepository refreshTokenRepository;
  public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
  }

  public RefreshToken createRefreshToken(Long userId){
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setUserId(userId);
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setExpiryDate(Instant.now().plusSeconds(30L * 24 * 60 * 60));

    return refreshTokenRepository.save(refreshToken);
  }

  public RefreshToken deleteRefreshToken(Long userId){
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setUserId(userId);
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setExpiryDate(Instant.now().plusSeconds(1)); // 1 секунда жизни


   return refreshTokenRepository.save(refreshToken);
  }


  public boolean isValid(String token){
    Optional<RefreshToken> storedToken = refreshTokenRepository.findByToken(token);
    return storedToken.isPresent() && storedToken.get().getExpiryDate().isAfter(Instant.now());
  }

  public void deleteAllByUserId(Long userId) {
    refreshTokenRepository.deleteAllByUserId(userId);
  }
}
