package com.kevin.site.data;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.site.entity.FilmEntity;
import com.kevin.site.entity.UserDropFilm;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.entity.UserFavoriteFilm;
import com.kevin.site.entity.UserOnWatchFilm;
import com.kevin.site.entity.UserToWatchFilm;
import com.kevin.site.interfaces.FilmRepository;
import com.kevin.site.interfaces.UserDropFilmsRepository;
import com.kevin.site.interfaces.UserFavoriteFilmsRepository;
import com.kevin.site.interfaces.UserOnWatchFilmsRepository;
import com.kevin.site.interfaces.UserToWatchFilmsRepository;
import com.kevin.site.models.FilmModel;
import com.kevin.site.models.UserModel;
import com.kevin.site.interfaces.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDataService {
  final
  UserRepository userRepository;
  final
  UserFavoriteFilmsRepository userFavoriteFilmsRepository;
  final
  UserOnWatchFilmsRepository userOnWatchFilmsRepository;
  final
  UserToWatchFilmsRepository userToWatchFilmsRepository;
  final UserDropFilmsRepository userDropFilmsRepository;
  final
  FilmRepository filmRepository;
  @Autowired
  private ObjectMapper objectMapper;
  ModelMapper modelMapper = new ModelMapper();
  @Autowired
  public UserDataService(UserRepository userRepository,
                         UserFavoriteFilmsRepository userFavoriteFilmsRepository,
                         UserOnWatchFilmsRepository userOnWatchFilmsRepository,
                         UserToWatchFilmsRepository userToWatchFilmsRepository,
                         UserDropFilmsRepository userDropFilmsRepository,
                         FilmRepository filmRepository) {
    this.userRepository = userRepository;
    this.userFavoriteFilmsRepository = userFavoriteFilmsRepository;
    this.userOnWatchFilmsRepository = userOnWatchFilmsRepository;
    this.userToWatchFilmsRepository = userToWatchFilmsRepository;
    this.userDropFilmsRepository = userDropFilmsRepository;
    this.filmRepository = filmRepository;
  }


  public UserModel getById(Long id) {
    UserEntity entity = userRepository.findById(id).orElse(null);
    return modelMapper.map(entity,UserModel.class);
  }


  public List<UserModel> getUsers() {
    Iterable<UserEntity> userEntities = userRepository.findAll();
    List<UserModel> users = new ArrayList<>();
    userEntities.forEach(user->users.add(modelMapper.map(user,UserModel.class)));
    return users;
  }


  public List<UserModel> searchUsers(String searchTerm) {
    List<UserEntity> userEntities = userRepository.findByUsernameIgnoreCase(searchTerm);
    List<UserModel> users = new ArrayList<>();
    userEntities.forEach(user->users.add(modelMapper.map(user,UserModel.class)));
    return users;
  }


  public Long addOne(UserModel newUser) {
    UserEntity userEntity = modelMapper.map(newUser, UserEntity.class);
    UserEntity result = userRepository.save(userEntity);
    return 1L;
  }


  public boolean deleteOne(Long id) {
    return userRepository.deleteUserById(id);
  }


  public UserModel updateOne(Long id, UserModel userModel) {
    UserEntity existingUser = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (userModel.getUsername() != null && !userModel.getUsername().isBlank()) {
      existingUser.setUsername(userModel.getUsername());
    }

    if (userModel.getPassword() != null && !userModel.getPassword().isBlank()) {

      existingUser.setPassword(userModel.getPassword());
    }

    if (userModel.getEmail() != null && !userModel.getEmail().isBlank()) {
      existingUser.setEmail(userModel.getEmail());
    }

    if (userModel.getRoles() != null) {
      existingUser.setRoles(userModel.getRoles());
    }

    if (userModel.getPfpUrl() != null && !userModel.getPfpUrl().isBlank()) {
      existingUser.setPfpUrl(userModel.getPfpUrl());
    }

    if (userModel.getPhoneNumber() != null && !userModel.getPhoneNumber().isBlank()) {
      existingUser.setPhoneNumber(userModel.getPhoneNumber());
    }

    if (userModel.getReSub() != null && !userModel.getReSub().isBlank()) {
      existingUser.setReSub(userModel.getReSub());
    }

    if (userModel.getWatchProgress() != null) {
      existingUser.setWatchProgress(userModel.getWatchProgress());
    }
    if (userModel.getSubscriptionEndDate() != null && !userModel.getSubscriptionEndDate().isBlank()) {
      existingUser.setSubscriptionEndDate(userModel.getSubscriptionEndDate());
    }

    if (userModel.getSubscription() != null && !userModel.getSubscription().isBlank()) {
      existingUser.setSubscription(userModel.getSubscription());
    }


    UserEntity updatedUser = userRepository.save(existingUser);

    return modelMapper.map(updatedUser, UserModel.class);
  }
 public boolean existByUsername(String username){
   return userRepository.existsByUsername(username);
 }
 public boolean existById(Long id){
    return userRepository.existsById(id);
 }
  public boolean existByNameOrEmail(String username,String email){
    return userRepository.existsByUsernameOrEmail(username,email);
  }
 public boolean existByEmail(String email){
    return userRepository.existsByEmail(email);
 }
 public UserModel findByUsername(String username){
   UserEntity result = userRepository.findByUsername(username);

   if (result == null) {
     return null;
   }
    return modelMapper.map(result,UserModel.class);
 }
  public UserModel findByEmail(String email){
    UserEntity result = userRepository.findByEmail(email);
    if (result == null) {
      return null;
    }
    return modelMapper.map(result,UserModel.class);
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
      filmRepository.findById(filmId).ifPresent(favoriteFilmsEntity::add);
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
      filmRepository.findById(filmId).ifPresent(dropFilmsEntity::add);
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
      filmRepository.findById(filmId).ifPresent(toWatchFilmsEntity::add);
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
      filmRepository.findById(filmId).ifPresent(onWatchFilmsEntity::add);
    }
    List<FilmModel> onWatchFilms = new ArrayList<>();
    onWatchFilmsEntity.forEach(film->onWatchFilms.add(modelMapper.map(film,FilmModel.class)));

    return onWatchFilms;
  }
  public void updateWatchProgress(Long userId, List<Map<String, Object>> watchProgress) throws Exception {
    UserEntity user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));


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
    userRepository.save(user);
  }
  public List<Map<String, Object>> getWatchProgress(Long userId) throws Exception {

    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (user.getWatchProgress() == null || user.getWatchProgress().isEmpty()) {
      return new ArrayList<>();
    }

    return objectMapper.readValue(user.getWatchProgress(), new TypeReference<List<Map<String, Object>>>() {});
  }



}
