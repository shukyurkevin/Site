package com.kevin.site.interfaces;

import com.kevin.site.entity.UserEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepositoryInterface extends CrudRepository<UserEntity,Long> {
  List<UserEntity> findByUsernameIgnoreCase(String username);
  boolean existsByUsernameOrEmail(String username, String email);

  default boolean deleteUserById(Long id) {
    if (existsById(id)) {
      deleteById(id);
      return true;
    }
    return false;
  }
  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
  UserEntity findByUsername(String username);
  UserEntity findByEmail(String email);

}
