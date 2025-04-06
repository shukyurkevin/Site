package com.kevin.site.services;

import com.kevin.site.data.UserDataService;
import com.kevin.site.models.FilmModel;
import com.kevin.site.models.UserModel;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
  final
  UserDataService userDao;
 @Autowired
  public UserService(UserDataService userDao) {
    this.userDao = userDao;
  }


  public Long createUser(UserModel user) {
    return userDao.addOne(user);
  }

  public UserModel getUserById(Long id) {
    return userDao.getById(id);
  }

  public List<UserModel> getAllUsers() {
    return userDao.getUsers();
  }

  public UserModel updateUser(Long id, UserModel newUser) {
    return userDao.updateOne(id,newUser);
  }

  public void deleteUserById(Long id) {
    if (userDao.existById(id)) {
      userDao.deleteOne(id);
    } else {
      throw new NoSuchElementException("User not found with id: " + id);
    }
  }
  public boolean existsByUsername(String username){
   return userDao.existByUsername(username);
  }
  public boolean existsByEmail(String email){
   return userDao.existByEmail(email);
  }
  public boolean existsByUsernameOrEmail(String username, String email){
    return userDao.existByNameOrEmail(username,email);
  }

  public UserModel findByUsername(String username) {
   return userDao.findByUsername(username);
  }
  public UserModel findByEmail(String email) {
    return userDao.findByEmail(email);
  }

  public List<FilmModel> getAllFavorites(Long userId){
   return userDao.getFavoriteFilms(userId);
  }
  public List<FilmModel> getAllDrop(Long userId){
    return userDao.getDropFilms(userId);
  }
  public List<FilmModel> getAllToWatch(Long userId){
    return userDao.getToWatchFilms(userId);
  }
  public List<FilmModel> getAllOnWatch(Long userId){
    return userDao.getOnWatchFilms(userId);
  }
  public void addToFavorite(Long userId, Long filmId){
    userDao.addToFavorite(userId,filmId);
  }
  public void deleteFromFavorite(Long userId, Long filmId){
   userDao.deleteFromFavorite(userId,filmId);
  }
  public void addToOnWatchList(Long userId, Long filmId){
   userDao.addToOnWatchList(userId,filmId);
  }
  public void deleteFromOnWatchList(Long userId, Long filmId){
    userDao.deleteFromOnWatchList(userId,filmId);
  }
  public void addToToWatchList(Long userId, Long filmId){
    userDao.addToToWatchList(userId,filmId);
  }
  public void deleteFromToWatchList(Long userId, Long filmId) {
    userDao.deleteFromToWatchList(userId, filmId);
  }
  public void addToDrop(Long userId, Long filmId){
    userDao.addToDrop(userId,filmId);
  }
  public void deleteFromDrop(Long userId, Long filmId) {
    userDao.deleteFromDrop(userId, filmId);
  }
  public void updateWatchProgress(Long userId, List<Map<String, Object>> watchProgress)
      throws Exception {
   userDao.updateWatchProgress(userId,watchProgress);
  }

  public List<Map<String, Object>> getWatchProgress(Long userId) throws Exception{
   return userDao.getWatchProgress(userId);
  }
}
