package com.kevin.site.services;

import com.kevin.site.data.FilmDataServiceRepository;
import com.kevin.site.dto.GenreFilmsDto;
import com.kevin.site.dto.GenreSeriesDto;
import com.kevin.site.models.FilmModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FilmServiceImp {
  final
  FilmDataServiceRepository filmDAO;
  @Autowired
  public FilmServiceImp(FilmDataServiceRepository filmDAO) {
    this.filmDAO = filmDAO;
  }



  public void test() {
    System.out.println("Film Service is working");
  }


  public void init() {
    System.out.println("init filmService");
  }


  public void destroy() {
    System.out.println("destroy filmService");

  }


  public List<FilmModel> getFilms() {

    return filmDAO.getFilms();

  }


  public List<FilmModel> searchFilms(String searchTerm) {
    return filmDAO.searchFilms(searchTerm);
  }

  public List<FilmModel> searchByGenres(String searchTerm){
    return filmDAO.searchByGenres(searchTerm);
  }

  public Long addOne(FilmModel newFilm) {

    return filmDAO.addOne(newFilm);
  }


  public boolean deleteOne(Long id) {

    return filmDAO.deleteOne(id);
  }


  public FilmModel updateOne(Long idToUpdate, FilmModel newOrder) {

    return filmDAO.updateOne(idToUpdate,newOrder);
  }


  public FilmModel getById(Long id) {

    return filmDAO.getById(id);
  }

  public List<FilmModel> getLatestFilms() {
    return filmDAO.getLatestFilms();
  }

  public List<FilmModel> getLatestOnlyFilms() {
    return filmDAO.getLatestOnlyFilms();
  }

  public List<FilmModel> getLatestOnlySeries() {
    return filmDAO.getLatestOnlySeries();
  }


  public List<FilmModel> getSeries(){
    return filmDAO.getSeries();
  }

  public List<FilmModel> getOnlyFilms(){
    return filmDAO.getOnlyFilms();
  }


  public List<GenreFilmsDto> getFilmsByGenres(){
    return filmDAO.getFilmsByGenres();
  }


  public List<GenreSeriesDto> getSeriesByGenres() {
    return filmDAO.getSeriesByGenres();
  }


  public List<FilmModel> getHighestRated() {

    return filmDAO.getHighestRated();
  }


  public List<FilmModel> getHighestRatedMovies() {
    return filmDAO.getHighestRatedMovies();
  }


  public List<FilmModel> getHighestRatedSeries() {
    return filmDAO.getHighestRatedSeries();
  }

}
