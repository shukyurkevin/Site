package com.kevin.site.controllers;

import com.kevin.site.models.FilmModel;
import com.kevin.site.security.UserPrincipal;
import com.kevin.site.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user/lists")
public class UserListsController {
  private final UserService userService;
  @Autowired
  public UserListsController(UserService userService){
    super();
    this.userService = userService;
  }
  @GetMapping("/favorite/")
  public List<FilmModel> getAllFavorite(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return userService.getAllFavorites(userPrincipal.getId());
  }
  @PostMapping("/favorite/add/{id}")
  public ResponseEntity<Void> addToFavoriteFilm(@AuthenticationPrincipal UserPrincipal userPrincipal
      , @PathVariable(name = "id") Long filmId) {
    userService.addToFavorite(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/favorite/delete/{id}")
  public ResponseEntity<Void> deleteFilmFromFavorite(@AuthenticationPrincipal UserPrincipal userPrincipal
      ,@PathVariable(name = "id") Long filmId) {
    userService.deleteFromFavorite(userPrincipal.getId(),filmId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/drop/")
  public List<FilmModel> getAllDrop(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return userService.getAllDrop(userPrincipal.getId());
  }
  @GetMapping("/toWatch/")
  public List<FilmModel> getAllToWatch(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return userService.getAllToWatch(userPrincipal.getId());
  }
  @GetMapping("/onWatch/")
  public List<FilmModel> getAllOnWatch(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return userService.getAllOnWatch(userPrincipal.getId());
  }
  @PostMapping("/onWatch/add/{filmId}")
  public ResponseEntity<Void> addToOnWatchList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @PathVariable(name = "filmId") Long filmId) {
    userService.addToOnWatchList(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/onWatch/delete/{filmId}")
  public ResponseEntity<Void> deleteFromOnWatchList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                    @PathVariable(name = "filmId") Long filmId) {
    userService.deleteFromOnWatchList(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/toWatch/add/{filmId}")
  public ResponseEntity<Void> addToToWatchList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @PathVariable(name = "filmId") Long filmId) {
    userService.addToToWatchList(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/toWatch/delete/{filmId}")
  public ResponseEntity<Void> deleteFromToWatchList(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                    @PathVariable(name = "filmId") Long filmId) {
    userService.deleteFromToWatchList(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/drop/add/{filmId}")
  public ResponseEntity<Void> addToDrop(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                               @PathVariable(name = "filmId") Long filmId) {
    userService.addToDrop(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/drop/delete/{filmId}")
  public ResponseEntity<Void> deleteFromDrop(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                    @PathVariable(name = "filmId") Long filmId) {
    userService.deleteFromDrop(userPrincipal.getId(), filmId);
    return ResponseEntity.ok().build();
  }

}
