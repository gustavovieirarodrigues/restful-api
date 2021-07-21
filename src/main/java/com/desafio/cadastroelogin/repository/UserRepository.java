package com.desafio.cadastroelogin.repository;

import com.desafio.cadastroelogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    public Optional<User> findByNameAndPassword(String name, String password);

}
