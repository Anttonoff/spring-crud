package com.javacourse.curso.controllers;

import com.javacourse.curso.dao.UserDao;
import com.javacourse.curso.models.User;
import com.javacourse.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value= "api/usuarios", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value="Authorization") String token){

        if(!validityToken(token)){
            return null;
        }

        return userDao.getUsers();
    }

    @RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
    public void userRegister(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.register(user);
    }

    // @PathVariable se puede usar para manejar variables de plantilla en el mapeo de URI de
    // solicitud y establecerlas como parámetros de método.
    @RequestMapping(value= "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if(!validityToken(token)){
            return;
        }
        userDao.delete(id);
    }

    private boolean validityToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }
}
