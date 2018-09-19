package pl.coderslab.service;

import pl.coderslab.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUserName(String name);

    User findByLogin(String login);

    void saveUser(User user);

    User getCurrentUser();


}
