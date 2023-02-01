package com.restfullwebservices.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    //GET /users/ id
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
      User user = service.findOne(id);

      if(user==null)
          throw new UserNotFoundException("id:"+ id);
      return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
       service.deleteById(id);

    }
    //POST Method to return correct HTTP Status Code and Location
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User saveUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }


}
