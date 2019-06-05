package io.pivotal.rest.springpoc.service;

import io.pivotal.rest.springpoc.bean.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserService {

    private static List<User> users = new ArrayList<>();

    private static Integer userCount = 3;


    static {

        users.add(new User(Integer.valueOf(1), "John", LocalDate.of(2000,1,2)));
        users.add(new User(Integer.valueOf(2), "Mark", LocalDate.of(1989,1,2)));
        users.add(new User(Integer.valueOf(3), "Tim", LocalDate.of(1995,1,2)));

    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        if(user != null) {
            user.setId(++userCount);
            users.add(user);
        }
        return user;
    }

    public User findOne(Integer id){
        for(User user: users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteUser(Integer id){
        Iterator<User> iterator = users.iterator();
        while ((iterator.hasNext())){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return  user;
            }
        }
        return  null;
    }

}
