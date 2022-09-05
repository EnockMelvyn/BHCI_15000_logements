package com.example.bhciLogement.appuser;

import com.example.bhciLogement.payload.request.LoginRequest;
import com.example.bhciLogement.payload.request.SignupRequest;
import com.example.bhciLogement.payload.response.JwtResponse;
import com.example.bhciLogement.payload.response.MessageResponse;
import com.example.bhciLogement.security.PasswordEncoder;
import com.example.bhciLogement.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    AppUserService appUserService ;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping(path = "list")
    public List<AppUser> listUsers(){
        return this.appUserService.listUsers();
    }

    }
