package com.kevin.site.data;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.site.entity.FilmEntity;
import com.kevin.site.entity.UserDropFilm;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.entity.UserFavoriteFilm;
import com.kevin.site.entity.UserOnWatchFilm;
import com.kevin.site.entity.UserToWatchFilm;
import com.kevin.site.interfaces.FilmRepositoryInterface;
import com.kevin.site.interfaces.UserDropFilmsRepository;
import com.kevin.site.interfaces.UserFavoriteFilmsRepository;
import com.kevin.site.interfaces.UserOnWatchFilmsRepository;
import com.kevin.site.interfaces.UserToWatchFilmsRepository;
import com.kevin.site.models.FilmModel;
import com.kevin.site.models.UserModel;
import com.kevin.site.interfaces.UserDataAccessInterface;
import com.kevin.site.interfaces.UserRepositoryInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDataService implements UserDataAccessInterface<UserModel> {
  final
  UserRepositoryInterface userRepositoryInterface;
  final
  UserFavoriteFilmsRepository userFavoriteFilmsRepository;
  final
  UserOnWatchFilmsRepository userOnWatchFilmsRepository;
  final
  UserToWatchFilmsRepository userToWatchFilmsRepository;
  final UserDropFilmsRepository userDropFilmsRepository;
  final
  FilmRepositoryInterface filmRepositoryInterface;
  @Autowired
  private ObjectMapper objectMapper;
  ModelMapper modelMapper = new ModelMapper();
  @Autowired
  public UserDataService(UserRepositoryInterface userRepositoryInterface,
                         UserFavoriteFilmsRepository userFavoriteFilmsRepository,
                         UserOnWatchFilmsRepository userOnWatchFilmsRepository,
                         UserToWatchFilmsRepository userToWatchFilmsRepository,
                         UserDropFilmsRepository userDropFilmsRepository,
                         FilmRepositoryInterface filmRepositoryInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
    this.userFavoriteFilmsRepository = userFavoriteFilmsRepository;
    this.userOnWatchFilmsRepository = userOnWatchFilmsRepository;
    this.userToWatchFilmsRepository = userToWatchFilmsRepository;
    this.userDropFilmsRepository = userDropFilmsRepository;
    this.filmRepositoryInterface = filmRepositoryInterface;
  }

  @Override
  public UserModel getById(Long id) {
    UserEntity entity = userRepositoryInterface.findById(id).orElse(null);
    return modelMapper.map(entity,UserModel.class);
  }

  @Override
  public List<UserModel> getUsers() {
    Iterable<UserEntity> userEntities = userRepositoryInterface.findAll();
    List<UserModel> users = new ArrayList<>();
    userEntities.forEach(user->users.add(modelMapper.map(user,UserModel.class)));
    return users;
  }

  @Override
  public List<UserModel> searchUsers(String searchTerm) {
    List<UserEntity> userEntities = userRepositoryInterface.findByUsernameIgnoreCase(searchTerm);
    List<UserModel> users = new ArrayList<>();
    userEntities.forEach(user->users.add(modelMapper.map(user,UserModel.class)));
    return users;
  }

  @Override
  public Long addOne(UserModel newUser) {
    UserEntity userEntity = modelMapper.map(newUser, UserEntity.class);
    UserEntity result = userRepositoryInterface.save(userEntity);
    return 1L;
  }

  @Override
  public boolean deleteOne(Long id) {
    return userRepositoryInterface.deleteUserById(id);
  }

  @Override
  public UserModel updateOne(Long id, UserModel userModel) {
    UserEntity existingUser = userRepositoryInterface.findById(id)
        .orElse(null);
    if (existingUser == null)return null;

    existingUser.setUsername(userModel.getUsername());
    existingUser.setPassword(userModel.getPassword());
    existingUser.setEmail(userModel.getEmail());
    existingUser.setRoles(userModel.getRoles());

    UserEntity updatedUser = userRepositoryInterface.save(existingUser);

    return modelMapper.map(updatedUser, UserModel.class);
  }
 public boolean existByUsername(String username){
   return userRepositoryInterface.existsByUsername(username);
 }
 public boolean existById(Long id){
    return userRepositoryInterface.existsById(id);
 }
 public boolean existByEmail(String email){
    return userRepositoryInterface.existsByEmail(email);
 }
 public UserModel findByUsername(String username){
    UserModel result = modelMapper.map(userRepositoryInterface.findByUsername(username),UserModel.class);
   if (result == null) {
     return null;
   }
    return result;
 }
  @Transactional
 public void addToFavorite(Long userId, Long filmId){
    if (userFavoriteFilmsRepository.findByUserIdAndFilmId(userId,filmId) != null){
      return;
    }
    UserFavoriteFilm userFavoriteFilm = UserFavoriteFilm.builder()
        .userId(userId)
        .filmId(filmId)
        .build();
    userFavoriteFilmsRepository.save(userFavoriteFilm);

 }

 public void deleteFromFavorite(Long userId, Long filmId) {
   Long result = userFavoriteFilmsRepository.findByUserIdAndFilmId(userId,filmId).getId();
    userFavoriteFilmsRepository.deleteById(result);
 }

  @Transactional
  public void addToOnWatchList(Long userId, Long filmId) {
    if (userOnWatchFilmsRepository.findByUserIdAndFilmId(userId,filmId) != null){
      return;
    }
    UserOnWatchFilm userOnWatchFilm = UserOnWatchFilm.builder()
        .userId(userId)
        .filmId(filmId)
        .build();
    userOnWatchFilmsRepository.save(userOnWatchFilm);

  }
  @Transactional
  public void deleteFromOnWatchList(Long userId, Long filmId) {
    Long result = userOnWatchFilmsRepository.findByUserIdAndFilmId(userId,filmId).getId();
    userOnWatchFilmsRepository.deleteById(result);

  }
  @Transactional
  public void addToToWatchList(Long userId, Long filmId) {
    if (userToWatchFilmsRepository.findByUserIdAndFilmId(userId,filmId) != null){
      return;
    }
    UserToWatchFilm userOnWatchFilm = UserToWatchFilm.builder()
        .userId(userId)
        .filmId(filmId)
        .build();
    userToWatchFilmsRepository.save(userOnWatchFilm);

  }
  @Transactional
  public void deleteFromToWatchList(Long userId, Long filmId) {
    Long result = userToWatchFilmsRepository.findByUserIdAndFilmId(userId,filmId).getId();
    userToWatchFilmsRepository.deleteById(result);
  }
  @Transactional
  public void addToDrop(Long userId, Long filmId){
    if (userDropFilmsRepository.findByUserIdAndFilmId(userId,filmId) != null){
      return;
    }
    UserDropFilm userDropFilm = UserDropFilm.builder()
        .userId(userId)
        .filmId(filmId)
        .build();
    userDropFilmsRepository.save(userDropFilm);

  }

  public void deleteFromDrop(Long userId, Long filmId) {
    Long result = userDropFilmsRepository.findByUserIdAndFilmId(userId,filmId).getId();
    userDropFilmsRepository.deleteById(result);
  }
  @Transactional
  public List<FilmModel> getFavoriteFilms(Long userId){
    List<Long> favoriteListInId = userFavoriteFilmsRepository.findAllFilmIdsByUserId(userId);
    List<FilmEntity> favoriteFilmsEntity  = new ArrayList<>();

    for(Long filmId : favoriteListInId){
      filmRepositoryInterface.findById(filmId).ifPresent(favoriteFilmsEntity::add);
    }
    List<FilmModel> favoriteFilms = new ArrayList<>();
    favoriteFilmsEntity.forEach(film->favoriteFilms.add(modelMapper.map(film,FilmModel.class)));

    return favoriteFilms;
  }
  @Transactional
  public List<FilmModel> getDropFilms(Long userId){
    List<Long> dropListInId = userDropFilmsRepository.findAllFilmIdsByUserId(userId);
    List<FilmEntity> dropFilmsEntity  = new ArrayList<>();

    for(Long filmId : dropListInId){
      filmRepositoryInterface.findById(filmId).ifPresent(dropFilmsEntity::add);
    }
    List<FilmModel> dropFilms = new ArrayList<>();
    dropFilmsEntity.forEach(film->dropFilms.add(modelMapper.map(film,FilmModel.class)));

    return dropFilms;
  }
  @Transactional
  public List<FilmModel> getToWatchFilms(Long userId){

    List<Long> toWatchListInId = userToWatchFilmsRepository.findAllFilmIdsByUserId(userId);
    List<FilmEntity> toWatchFilmsEntity  = new ArrayList<>();

    for(Long filmId : toWatchListInId){
      filmRepositoryInterface.findById(filmId).ifPresent(toWatchFilmsEntity::add);
    }
    List<FilmModel> toWatchFilms = new ArrayList<>();
    toWatchFilmsEntity.forEach(film->toWatchFilms.add(modelMapper.map(film,FilmModel.class)));

    return toWatchFilms;
  }
  @Transactional
  public List<FilmModel> getOnWatchFilms(Long userId){
    List<Long> onWatchListInId = userOnWatchFilmsRepository.findAllFilmIdsByUserId(userId);
    List<FilmEntity> onWatchFilmsEntity  = new ArrayList<>();

    for(Long filmId : onWatchListInId){
      filmRepositoryInterface.findById(filmId).ifPresent(onWatchFilmsEntity::add);
    }
    List<FilmModel> onWatchFilms = new ArrayList<>();
    onWatchFilmsEntity.forEach(film->onWatchFilms.add(modelMapper.map(film,FilmModel.class)));

    return onWatchFilms;
  }
  public void updateWatchProgress(Long userId, List<Map<String, Object>> watchProgress) throws Exception {
    UserEntity user = userRepositoryInterface.findById(userId).orElseThrow(()->new RuntimeException("user not found"));


    String currentProgressString = user.getWatchProgress();
    List<Map<String, Object>> currentProgress = new ArrayList<>();
    if (currentProgressString != null && !currentProgressString.isEmpty()) {
      currentProgress = objectMapper.readValue(currentProgressString, List.class);
    }

    for (Map<String, Object> newEntry : watchProgress) {
      Long filmId = ((Number) newEntry.get("filmId")).longValue();
      Integer timing = ((Number) newEntry.get("timing")).intValue();

      boolean updated = false;
      for (Map<String, Object> existingEntry : currentProgress) {
        Long existingFilmId = ((Number) existingEntry.get("filmId")).longValue();
        if (existingFilmId.equals(filmId)) {
          existingEntry.put("timing", timing);
          updated = true;
          break;
        }
      }

      if (!updated) {
        currentProgress.add(newEntry);
      }
    }
    if (currentProgress.size() > 8) {
      currentProgress = currentProgress.subList(currentProgress.size() - 8, currentProgress.size());
    }

    String updatedProgressString = objectMapper.writeValueAsString(currentProgress);
    user.setWatchProgress(updatedProgressString);
    userRepositoryInterface.save(user);
  }
  public List<Map<String, Object>> getWatchProgress(Long userId) throws Exception {

    UserEntity user = userRepositoryInterface.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (user.getWatchProgress() == null || user.getWatchProgress().isEmpty()) {
      return new ArrayList<>();
    }

    return objectMapper.readValue(user.getWatchProgress(), new TypeReference<List<Map<String, Object>>>() {});
  }


}
