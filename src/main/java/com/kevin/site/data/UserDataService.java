package com.kevin.site.data;

import com.kevin.site.entity.FilmEntity;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.models.FilmModel;
import com.kevin.site.models.UserModel;
import com.kevin.site.interfaces.UserDataAccessInterface;
import com.kevin.site.interfaces.UserRepositoryInterface;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataService implements UserDataAccessInterface<UserModel> {
  final
  UserRepositoryInterface userRepositoryInterface;
  ModelMapper modelMapper = new ModelMapper();
  @Autowired
  public UserDataService(UserRepositoryInterface userRepositoryInterface) {
    this.userRepositoryInterface = userRepositoryInterface;
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
    if (result == null){
      return 0L;
    }else return 1L;
  }

  @Override
  public boolean deleteOne(Long id) {
    return userRepositoryInterface.deleteUserById(id);
  }

  @Override
  public UserModel updateOne(Long id, UserModel userModel) {
    UserEntity entity = modelMapper.map(userModel, UserEntity.class);
    UserEntity result = userRepositoryInterface.save(entity);
    return modelMapper.map(result,UserModel.class);
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
}