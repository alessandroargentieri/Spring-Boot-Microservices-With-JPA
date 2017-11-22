package com.quicktutorialz.learnmicroservices.ToDoMicroservice.daos;

import com.quicktutorialz.learnmicroservices.ToDoMicroservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserDao extends JpaRepository<User, String> {
    //name strategy
    Optional<User> findUserByEmail(String email);

    //@query annotation
  //  @Query(value="SELECT * FROM users WHERE email=:email", nativeQuery = true)
  //  Optional<User> findUserByTheEmail(@Param("email") String email);

    //native method
  //  User findOne(String email);



}
