package com.spring.Config;

import com.spring.Entities.User;
import com.spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("teste")
public class TesteConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("brenno", "brenno@gmail.com", "9999999", "12345");

        userRepository.save(user);
    }
}
