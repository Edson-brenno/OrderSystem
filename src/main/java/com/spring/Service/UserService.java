package com.spring.Service;

import com.spring.Entities.User;
import com.spring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByLikeEmail(String email) {
        return userRepository.findByEmailLike(email);
    }

    public void newUser(User user) {
        userRepository.save(user);
    }
}
