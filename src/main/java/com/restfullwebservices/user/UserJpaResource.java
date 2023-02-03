package com.restfullwebservices.user;

import com.restfullwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaResource {

    private UserDaoService service;

    @Autowired
    private UserRepository repository;

     public void UserDaoService(UserDaoService service,UserRepository repository){
      this.service = service;
      this.repository = repository;
    }

    //GET /users
    @GetMapping("jpa/users")
    public List<User> retrieveAllUsers(){
         return repository.findAll();
     }
    //EntityModel
    //WebMvcLinkBuilder
    //GET /users/ id
    @GetMapping("jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
      Optional<User> user = repository.findById(id);

      if(user.isEmpty())
          throw new UserNotFoundException("id:"+ id);
      EntityModel<User> entityModel = EntityModel.of(user.get());
      WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
      entityModel.add(link.withRel("all-users"));
      return entityModel;
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
       repository.deleteById(id);
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post1> retrievePostsUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+ id);
       return user.get().getPosts();
    }

    //POST Method to return correct HTTP Status Code and Location
    @PostMapping("jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saveUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }
}
