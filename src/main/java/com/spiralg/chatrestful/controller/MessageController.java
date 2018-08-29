package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.service.MessageService;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;
    private UserService userService;
    private static String pattern = "\\d{4}-\\d{1,2}-\\d{1,2}";

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(value = "sender", defaultValue = "", required = false) String sender, @RequestParam(value = "receiver", defaultValue = "", required = false) String receiver, @RequestParam(value = "date", defaultValue = "", required = false) String create_date) {
        User senderUser = userService.findByName( sender );
        User receiverUser = userService.findByName( receiver );

        if (sender.trim().equals( "" ) || receiver.trim().equals( "" ) || senderUser != null || receiverUser != null) {
            if (create_date.matches( pattern ) || sender.trim().equals( "" ) || receiver.trim().equals( "" ) || create_date == null) {
                List<Message> messages = messageService.getAll( sender, receiver, create_date );
                return new ResponseEntity<>( new getMessage( messages.size(), messages ), HttpStatus.OK );
            } else {
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
            }
        } else {
            if (senderUser == null || receiverUser == null ) {
                return new ResponseEntity<>( HttpStatus.NOT_FOUND );
            } else {
                List<Message> messages = messageService.getAll( sender, receiver, create_date );
                return new ResponseEntity<>( new getMessage( messages.size(), messages ), HttpStatus.OK );
            }
        }
    }

    @GetMapping(value = {"/date"})
    public ResponseEntity<Object> getAllByDate(@RequestParam("date") String create_date) {
        if (create_date.matches( pattern )) {
            List<Message> messages = messageService.getAllByDay( create_date );
            return new ResponseEntity<>( new getMessage( messages.size(), messages ), HttpStatus.OK );
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
    }

    class getMessage {
        public getMessage(int total, List<Message> items) {
            this.total = total;
            this.items = items;
        }

        public int getTotal() {
            return total;
        }

        public List<Message> getItems() {
            return items;
        }

        private int total;
        private List<Message> items;
    }
}
