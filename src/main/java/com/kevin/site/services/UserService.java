package com.kevin.site.services;

import com.kevin.site.data.UserDataService;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.interfaces.UserDataAccessInterface;
import com.kevin.site.models.UserModel;
import java.util.List;
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

  public UserModel findByUsername(String username) {
   return userDao.findByUsername(username);
  }
}
