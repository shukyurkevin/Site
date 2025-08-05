package com.kevin.site.controllers;

import com.kevin.site.models.FilmModel;
import com.kevin.site.models.UserModel;
import com.kevin.site.security.UserPrincipal;
import com.kevin.site.services.FilmServiceImp;
import com.kevin.site.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserRestController {

  UserService userService;
  FilmServiceImp filmServiceImp;
  @Autowired
  public UserRestController(UserService userService, FilmServiceImp filmServiceImp){
    super();
    this.userService = userService;
    this.filmServiceImp = filmServiceImp;
  }
  @GetMapping("/")
  public UserModel UserData(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return userService.findByUsername(userPrincipal.getUsername());
  }

  @GetMapping("/getId")
  public String UserIdTest(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return userPrincipal.getId().toString();
  }

  @PutMapping("/{userId}/progress")
  public ResponseEntity<?> updateWatchProgress(
      @PathVariable Long userId,
      @RequestBody List<Map<String, Object>> progress) {
    try {
      userService.updateWatchProgress(userId, progress);
      return ResponseEntity.ok("Progress updated successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating progress");
    }
  }
  @GetMapping("/{userId}/progress")
  public ResponseEntity<?> getWatchProgress(@PathVariable Long userId) {
    try {

      List<Map<String, Object>> progress = userService.getWatchProgress(userId);

      for (Map<String, Object> entry : progress) {
        Long filmId = ((Number) entry.get("filmId")).longValue();
        FilmModel film = filmServiceImp.getById(filmId);


        entry.put("film", film);
      }


      return ResponseEntity.ok(progress);

    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving progress");
    }
  }
  @PutMapping("/update")
  public UserModel updateUser(@RequestBody UserModel model,
                              @AuthenticationPrincipal UserPrincipal userPrincipal) {
    Long userId = userPrincipal.getId();
    return userService.updateUser(userId, model);
  }
  @GetMapping("/profile")
  public UserModel userProfile(@AuthenticationPrincipal UserPrincipal userPrincipal){
    Long userId = userPrincipal.getId();
    return userService.getUserById(userId);
  }
}
