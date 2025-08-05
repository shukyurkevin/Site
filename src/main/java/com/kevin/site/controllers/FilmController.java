package com.kevin.site.controllers;

import com.kevin.site.models.FilmModel;
import com.kevin.site.services.FilmServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/films")
public class FilmController {
  FilmServiceImp filmService;

  @Autowired
  public FilmController(FilmServiceImp filmService) {
    this.filmService = filmService;
  }

  @GetMapping("/")
  public String showAllOrders(Model model){

  List<FilmModel> films = filmService.getFilms();

  model.addAttribute("title", "Film list");
  model.addAttribute("films",films);

    return "films";
  }

}
