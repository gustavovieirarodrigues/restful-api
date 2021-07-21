package com.desafio.cadastroelogin.utils;

import com.desafio.cadastroelogin.model.User;
import com.desafio.cadastroelogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Data {
    @Autowired
    UserRepository userRepository;

    // @PostConstruct
    public void saveUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setName("Enzo Falvo");
        user1.setPassword("123");

        User user2 = new User();
        user2.setName("José Rodrigues");
        user2.setPassword("123");

        users.add(user1);
        users.add(user2);

        for (User x : users) {
            userRepository.save(x);
            System.out.println("Usuário registrado com sucesso!");
        }
    }
}
