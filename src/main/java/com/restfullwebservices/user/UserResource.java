package com.restfullwebservices.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {

    @Autowired
    private UserDaoService service;

     public void UserDaoService(UserDaoService service){
      this.service = service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
         return service.findUsers();
     }
    //EntityModel
    //WebMvcLinkBuilder
    //GET /users/ id
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
      User user = service.findOne(id);

      if(user==null)
          throw new UserNotFoundException("id:"+ id);
      EntityModel<User> entityModel = EntityModel.of(user);
      WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
      entityModel.add(link.withRel("all-users"));
      return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
       service.deleteById(id);

    }
    //POST Method to return correct HTTP Status Code and Location
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saveUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }


}
