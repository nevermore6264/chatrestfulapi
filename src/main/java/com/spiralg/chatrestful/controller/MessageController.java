package com.spiralg.chatrestful.controller;

import com.google.gson.Gson;
import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.service.MessageService;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/message")
public class MessageController {

    private MessageService messageService;
    private UserService userService;
    private Gson gson = new Gson();
    private static String pattern = "\\d{4}-\\d{1,2}-\\d{1,2}";

    @Autowired
    public MessageController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

//    @GetMapping(value = {""})
//    public ResponseEntity<List<Message>> getAll(@RequestParam("sender") String sender, @RequestParam("receiver") String receiver, @RequestParam("date") String date){
//
//
//    }
}
