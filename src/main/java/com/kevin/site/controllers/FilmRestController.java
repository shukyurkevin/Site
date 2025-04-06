package com.kevin.site.controllers;

import com.kevin.site.models.FilmModel;
import com.kevin.site.services.FilmServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/films")
public class FilmRestController {
  FilmServiceInterface filmService;

  @Autowired
  public FilmRestController(FilmServiceInterface filmService) {
    super();
    this.filmService = filmService;
  }

  @GetMapping("/")
  public List<FilmModel> showAllFilms(){

    return filmService.getFilms();
  }

  @GetMapping("/search/")
  public List<FilmModel> emptySearch() {
    return new ArrayList<>();
  }
  @GetMapping("/genres/{searchTerm}")
  public List<FilmModel> searchByGenres(@PathVariable(name = "searchTerm") String x){

    return filmService.searchByGenres(x);
  }

  @GetMapping("/search/{searchTerm}")
  public List<FilmModel> searchFilms(@PathVariable(name = "searchTerm") String x){


    return filmService.searchFilms(x);
  }

  @PostMapping("/add")
  public long addFilm(@RequestBody FilmModel model){
    return filmService.addOne(model);
  }


  @GetMapping("/{id}")
  public FilmModel getById(@PathVariable(name = "id") Long x){

    return filmService.getById(x);
  }

  @GetMapping("/delete/{id}")
  public boolean deleteFilms(@PathVariable(name = "id") Long x){

    return filmService.deleteOne(x);
  }

  @PutMapping("/update/{id}")
  public FilmModel addFilm(@RequestBody FilmModel model, @PathVariable(name = "id") Long x){

    return filmService.updateOne(x,model);
  }
  @GetMapping("/latest")
  public List<FilmModel> getLatestFilms(){
    return filmService.getLatestFilms();
  }
  @GetMapping("/onlyFilms")
  public List<FilmModel> getOnlyFilms(){
    return filmService.getOnlyFilms();
  }
  @GetMapping("/onlySeries")
  public List<FilmModel> getSeries(){
    return filmService.getSeries();
  }

}
