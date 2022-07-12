package com.proyectointegrador.proyecto_Integrador_CTD.repository;

import com.proyectointegrador.proyecto_Integrador_CTD.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Override
    boolean existsById(Long aLong);

   /* @Query("SELECT a FROM AppUser a WHERE a.email = ?1 OR a.name = ?1")
    Optional<User> findByEmailOrUsername(String emailOrUsername);*/

    //ENABLE USER
    @Transactional
    @Modifying
    //enable user with this confirmation token
    @Query("UPDATE User u SET u.enabled = true WHERE u.id = (SELECT c.user.id FROM ConfirmationToken c WHERE c.token = ?1)")
    void enableUser(String confirmationToken);

}
