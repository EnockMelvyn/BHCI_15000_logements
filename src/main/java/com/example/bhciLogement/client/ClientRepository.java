package com.example.bhciLogement.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository
        extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c where c.email=?1")
    Optional<Client> findClientByEmail(String email);

//    @Query("update client_logement u set u.is_validate = true where u.id = ?1")
  // void validateLogementClient(Long id);

}
