package com.kevin.site.services;

import com.kevin.site.interfaces.FilmDataAccessInterface;
import com.kevin.site.models.FilmModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FilmService implements FilmServiceInterface {
  final
  FilmDataAccessInterface<FilmModel> filmDAO;
  @Autowired
  public FilmService(FilmDataAccessInterface<FilmModel> filmDAO) {
    this.filmDAO = filmDAO;
  }


  @Override
  public void test() {
    System.out.println("Film Service is working");
  }

  @Override
  public void init() {
    System.out.println("init filmService");
  }

  @Override
  public void destroy() {
    System.out.println("destroy filmService");

  }

  @Override
  public List<FilmModel> getFilms() {

    return filmDAO.getFilms();

  }

  @Override
  public List<FilmModel> searchFilms(String searchTerm) {
    return filmDAO.searchFilms(searchTerm);
  }
  @Override
  public List<FilmModel> searchByGenres(String searchTerm){
    return filmDAO.searchByGenres(searchTerm);
  }
  @Override
  public Long addOne(FilmModel newFilm) {

    return filmDAO.addOne(newFilm);
  }

  @Override
  public boolean deleteOne(Long id) {

    return filmDAO.deleteOne(id);
  }

  @Override
  public FilmModel updateOne(Long idToUpdate, FilmModel newOrder) {

    return filmDAO.updateOne(idToUpdate,newOrder);
  }

  @Override
  public FilmModel getById(Long id) {

    return filmDAO.getById(id);
  }
  @Override
  public List<FilmModel> getLatestFilms() {
    return filmDAO.getLatestFilms();
  }

  @Override
  public List<FilmModel> getSeries(){
    return filmDAO.getSeries();
  }
  @Override
  public List<FilmModel> getOnlyFilms(){
    return filmDAO.getOnlyFilms();
  }
}
