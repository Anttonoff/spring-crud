package com.javacourse.curso.controllers;

import com.javacourse.curso.dao.UserDao;
import com.javacourse.curso.models.User;
import com.javacourse.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value= "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){

        User userLogged = userDao.getUserByCredentials(user);
        if (userLogged != null){
            String token = jwtUtil.create(String.valueOf(userLogged.getId()), userLogged.getEmail());

            return token;
        }

        return "FAIL";
    }
}
