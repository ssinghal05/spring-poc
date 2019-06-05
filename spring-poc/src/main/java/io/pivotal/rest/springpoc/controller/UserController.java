package io.pivotal.rest.springpoc.controller;

import io.pivotal.rest.springpoc.bean.Post;
import io.pivotal.rest.springpoc.bean.User;
import io.pivotal.rest.springpoc.exception.UserNotFoundException;
import io.pivotal.rest.springpoc.service.PostService;
import io.pivotal.rest.springpoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @GetMapping("/users")
    public List<User> listAllUsers() {
        return userService.findAll();

    }

    @GetMapping("/users/{id}")
    public Resource<User> retreiveUser(@PathVariable final Integer id) {
        Optional<User> user = userService.findOne(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User " + id + " not found");

        }
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).listAllUsers());
        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
        User savedUser = userService.saveUser(user);

        URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uriLocation).build();

    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable final Integer id) {
        User user = userService.deleteUser(id);
        if (user == null) {
            throw new UserNotFoundException("User " + id + " not found");
        }
        return user;
    }


    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id) {
        Optional<User> user = userService.findOne(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User " + id + " not found");

        }
        return user.get().getPosts();
    }


    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> savePOst(@PathVariable Integer id, @RequestBody Post post) {
        Optional<User> user = userService.findOne(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User " + id + " not found");

        }

        post.setUser(user.get());

        postService.savePost(post);

        URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uriLocation).build();
    }
}
