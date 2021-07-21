package com.desafio.cadastroelogin.service;

import com.desafio.cadastroelogin.model.User;

public interface UserService {
    User save(User user);

    boolean findByName(String name);

    boolean findByNameAndPassword(String name, String password);
}
