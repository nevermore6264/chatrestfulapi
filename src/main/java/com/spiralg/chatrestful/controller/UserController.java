package com.spiralg.chatrestful.controller;


import com.spiralg.chatrestful.model.Users;
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
@RequestMapping(path ="/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private static String pattern1 = "\\d{4}-\\d{1,2}-\\d{1,2}";
    private static String pattern2 = "\\d{4}";
    Pattern pattern = Pattern.compile("^[a-zA-Z]{1,50}$");

//    @PostMapping(value = {""})
//    public ResponseEntity<String> save(@RequestBody String jsonUser) {
//
//        Users user = gson.fromJson(jsonUser, Users.class);
//        if (user.getUser_name() != null) {
//            Matcher matcher = pattern.matcher(user.getUser_name());
//            if (!matcher.matches()) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            if (user.getUser_name().trim().equals("")) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//            if (userService.findByUsername(user.getUser_name()) == null) {
//                userService.save(user);
//                return new ResponseEntity<>("Create success", HttpStatus.CREATED);
//            } else
//                return new ResponseEntity<>(HttpStatus.CONFLICT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


//    @GetMapping(value = {"/{id}/messages/{date}"})
//    public ResponseEntity<List<Message>> getAllByDay(@PathVariable("id") int id, @PathVariable("date") String date) {
//
//        List<Message> messages = null;
//        if (!date.matches(pattern1)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else {
//            if (userService.findById(id) == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            messages = messageService.getAllByDay(id, date);
//            if (messages.size() == 0)
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(messages, HttpStatus.OK);
//
//    }
//
//    @GetMapping(value = {"/{id}/messages"}, consumes = "application/json", produces = "application/json")
//    public ResponseEntity<List<Message>> getAllById(@PathVariable("id") int id) {
//
//        List<Message> messages = messageService.getAllById(id);
//        if (userService.findById(id) == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else if (messages == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(messages, HttpStatus.OK);
//
//    }

    @GetMapping(value = {""})
    public ResponseEntity<List<Users>> getAllUserByName(@RequestParam("username") String name) {
        System.out.println("da den day roi");
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            List<Users> users = userService.findAllByName(name.trim());
            if (users.size() == 0) {
                return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
