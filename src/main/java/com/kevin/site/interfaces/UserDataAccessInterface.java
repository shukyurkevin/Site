package com.kevin.site.interfaces;

import java.util.List;

public interface UserDataAccessInterface <T>{
  public T getById(Long id);
  public List<T> getUsers();
  public List<T> searchUsers(String searchTerm);
  public Long addOne(T newUser);
  public boolean deleteOne(Long id);
  public T updateOne(Long id, T userModel);

}
