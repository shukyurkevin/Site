package com.kevin.site.services;

import com.kevin.site.dto.UserLoginDTO;
import com.kevin.site.dto.UserRegistrationDTO;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.models.LoginResponse;
import com.kevin.site.models.UserModel;
import com.kevin.site.security.JwtIssuer;
import com.kevin.site.security.UserPrincipal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtIssuer jwtIssuer;
  private final EmailService emailService;
  ModelMapper modelMapper = new ModelMapper();
  public String auth(UserLoginDTO userDto) {

    UserModel user = userService.findByUsername(userDto.getUsername());

    if (user == null) {
      user = userService.findByEmail(userDto.getUsername());
    }

    if (user == null) {
      return "Wrong username or email";
    }

    if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
      return "Wrong password";
    }

    var token = jwtIssuer.issueAccessToken(user.getUserId(),user.getUsername(), List.of(user.getRoles()));

    return token;
  }
  public String registerUser(@RequestBody UserRegistrationDTO userDto) {
    if (userService.existsByUsername(userDto.getUsername())) {
      return "Username is already taken!";
    }

    if (userService.existsByEmail(userDto.getEmail())) {
      return "Email is already taken!";
    }

    UserEntity user = new UserEntity();
    user.setUsername(userDto.getUsername());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setEmail(userDto.getEmail());
    user.setRoles("ROLE_USER");
    UserModel userModel = modelMapper.map(user, UserModel.class);
    userService.createUser(userModel);

    emailService.sendEmail(userDto.getEmail(),"Welcome to Cine-X","Hello "+userDto.getUsername()
        +" ! Welcome to Cine-X");

    return"User registered successfully!";
  }


}
