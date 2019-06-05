package io.pivotal.rest.springpoc.service;

import io.pivotal.rest.springpoc.bean.User;
import io.pivotal.rest.springpoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public Optional<User> findOne(Integer id) {
        return userRepository.findById(id);
    }

    public User deleteUser(Integer id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return user.get();
        }
        return null;
    }

}
