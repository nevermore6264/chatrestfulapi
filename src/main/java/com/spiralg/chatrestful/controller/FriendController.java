package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.Token;
import com.spiralg.chatrestful.model.api.form.FriendForm;
import com.spiralg.chatrestful.service.FriendService;
import com.spiralg.chatrestful.service.TokenService;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/")
public class FriendController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @PostMapping(path = {"users/{id}/friends"})
    public ResponseEntity addFriend(@PathVariable("id") int id,@RequestBody FriendForm friendForm, HttpServletRequest request) throws Exception {
        Token token = tokenService.getByUserId(id);
        String t = request.getHeader( "token");
        System.out.println(token+"--"+t+"--"+friendForm.getUserFriend());
        if(token.getKey().equals(t)){
            Friend friend = new Friend();
            friend.setUserId(id);
            friend.setUserFriend(userService.findIdByUsername(friendForm.getUserFriend()));
            System.out.println(userService.findIdByUsername( friendForm.getUserFriend() ));
            friendService.create( friend );
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
