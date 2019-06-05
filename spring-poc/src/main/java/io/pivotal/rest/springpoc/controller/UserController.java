package io.pivotal.rest.springpoc.controller;

import io.pivotal.rest.springpoc.bean.User;
import io.pivotal.rest.springpoc.exception.UserNotFoundException;
import io.pivotal.rest.springpoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import static  org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> listAllUsers(){
       return  userService.findAll();

    }

    @GetMapping("/users/{id}")
    public Resource<User> retreiveUser(@PathVariable final Integer id){
        User user = userService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("User " +id + " not found");

        }
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).listAllUsers());
        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user){
        User savedUser = userService.saveUser(user);

        URI uriLocation =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uriLocation).build();

    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable final Integer id){
        User user = userService.deleteUser(id);
        if(user == null){
            throw new UserNotFoundException("User " +id + " not found");
        }
        return user;
    }
}
