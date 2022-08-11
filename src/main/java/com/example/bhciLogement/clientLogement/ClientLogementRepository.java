package com.example.bhciLogement.clientLogement;

import com.example.bhciLogement.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientLogementRepository extends JpaRepository<ClientLogement, Long> {

    @Query("SELECT c FROM ClientLogement c where c.client=?1")
    List<ClientLogement> findClientLogementByClient(Client client);

    @Query("SELECT c FROM ClientLogement c where c.isValidate=null")
    List<ClientLogement> findClientLogementWaitValidation();

    @Query("SELECT c FROM ClientLogement c where c.isValidate=true")
    List<ClientLogement> findClientLogementValidate();

    @Query("SELECT c FROM ClientLogement c where c.isValidate=false")
    List<ClientLogement> findClientLogementNotValidate();
}
