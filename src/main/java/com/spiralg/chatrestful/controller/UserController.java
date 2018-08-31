package com.spiralg.chatrestful.controller;

import com.google.gson.Gson;
import com.spiralg.chatrestful.common.ItemUser;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private Gson gson = new Gson();
    Pattern pattern = Pattern.compile( "^[a-zA-Z]{1,50}$" );

    @PostMapping
    public ResponseEntity<String> save(@RequestBody String jsonUser) {
        User user = gson.fromJson( jsonUser, User.class );
        System.out.println( user.getUserName() );
        if (user.getUserName() != null) {
            Matcher matcher = pattern.matcher( user.getUserName() );
            if (!matcher.matches()) {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
            }
            if (user.getUserName().trim().equals( "" )) {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
            }
            if (userService.findByName( user.getUserName() ) == null) {
                userService.save( user );
                return new ResponseEntity<>( "Create success", HttpStatus.CREATED );
            } else
                return new ResponseEntity<>( HttpStatus.CONFLICT );
        } else {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUserByName(@RequestParam(value = "username") String name) {
        List<User> users = userService.findAllByName( name.trim() );
        return new ResponseEntity<Object>( new ItemUser( users.size(), users ), HttpStatus.OK );
    }


}
