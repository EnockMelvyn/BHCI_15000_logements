package com.example.bhciLogement.appuser;

import com.example.bhciLogement.registration.token.ConfirmationToken;
import com.example.bhciLogement.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{


    private final static String USER_NOT_FOUND=
            "L'utilisateur n'existe pas";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }


    public String signUpUser(AppUser appUser) {
       boolean userExists =  appUserRepository
               .findByUsername(appUser.getUsername())
                .isPresent();

       if (userExists) {
           throw new IllegalStateException("Email déjà utilisé");
       }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

       appUser.setPassword(encodedPassword);

       appUserRepository.save(appUser);

       String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken
        );

        // TODO: send email
        return token;
    }

    public void enableAppUser( String username){

        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));

        appUser.setEnabled(Boolean.TRUE);

        appUserRepository.save(appUser);

    }

    public List<AppUser> listUsers(){
        return this.appUserRepository.findAll();
    }
}
