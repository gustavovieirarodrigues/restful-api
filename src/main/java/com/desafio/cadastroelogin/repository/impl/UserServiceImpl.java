package com.desafio.cadastroelogin.repository.impl;


import com.desafio.cadastroelogin.model.User;
import com.desafio.cadastroelogin.repository.UserRepository;
import com.desafio.cadastroelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean findByName(String name) {
        Optional<User> obj = userRepository.findByName(name);

        return obj.isPresent();
    }

    @Override
    public boolean findByNameAndPassword(String name, String password) {
        Optional<User> obj = userRepository.findByNameAndPassword(name, password);

        return obj.isPresent();
    }
}
