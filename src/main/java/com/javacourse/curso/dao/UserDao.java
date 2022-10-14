package com.javacourse.curso.dao;

import com.javacourse.curso.models.User;

import java.util.List;

public interface UserDao {

    // Como debe ser la función, la clase que use la interface estan obligadas a crear la funcion
    List<User> getUsers();

    void delete(Long id);

    void register(User user);

    User getUserByCredentials(User user);
}
