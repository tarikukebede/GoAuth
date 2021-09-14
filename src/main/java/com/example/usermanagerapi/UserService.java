package com.example.usermanagerapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User addUser(User user){
        System.out.println(user.getEmail());
        user.setUserCode(UUID.randomUUID().toString());
        return repository.save(user);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }
}
