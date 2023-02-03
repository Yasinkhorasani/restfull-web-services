package com.restfullwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    //JPA/Hibernate > Database
    //UserDaoService > Static List

    private static List<User> users =new ArrayList<>();

    private static int usersCount = 0;

    /*static {
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Yasin", LocalDate.now().minusYears(27)));
        users.add(new User(++usersCount,"Hasti", LocalDate.now().minusYears(33)));
    }*/

    public static List<User> findUsers() {
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
