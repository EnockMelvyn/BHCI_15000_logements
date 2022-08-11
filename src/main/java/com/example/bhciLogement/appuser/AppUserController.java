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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/auth/")
public class AppUserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    AppUserService appUserService ;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping(path = "users")
    public List<AppUser> listUsers(){
        return this.appUserService.listUsers();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser userDetails = (AppUser) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (appUserRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (appUserRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        AppUser user = new AppUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.bCryptPasswordEncoder().encode(signUpRequest.getPassword()) );

        Set<String> strRoles = signUpRequest.getRole();
//        Set<AppUserRole> roles = new HashSet<>();

        if (strRoles == null) {
            AppUserRole userRole = AppUserRole.ROLE_USER;
            user.setAppUserRole(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        AppUserRole adminRole = AppUserRole.ROLE_ADMIN;
                        user.setAppUserRole(adminRole);

                        break;

                    default:
                        AppUserRole userRole = AppUserRole.ROLE_USER;
                        user.setAppUserRole(userRole);
                }
            });
        }

//        user.setAppUserRole();
        appUserRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
