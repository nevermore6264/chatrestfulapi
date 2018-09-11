package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.Token;
import com.spiralg.chatrestful.model.api.form.AcceptForm;
import com.spiralg.chatrestful.model.api.form.FriendForm;
import com.spiralg.chatrestful.model.api.form.InvitationForm;
import com.spiralg.chatrestful.service.InvitationService;
import com.spiralg.chatrestful.service.TokenService;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/api/")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping(path = {"users/{id}/friends"})
    public ResponseEntity<List<Friend>> getAllInvitations(@PathVariable("id") int id){
            return new ResponseEntity<>( invitationService.getAllInvitations( new InvitationForm(id) ), HttpStatus.OK );
    }

    @PutMapping(path = {"users/{id}/friends"})
    public ResponseEntity accept (@RequestBody FriendForm friendForm, @PathVariable ("id") int id, HttpServletRequest httpServletRequest){
        Token token = tokenService.getByUserId(id);
        String t = httpServletRequest.getHeader( "token");
        System.out.println(token+"--"+t+"--"+friendForm.getUserFriend());
        if(token.getKey().equals(t)){
            AcceptForm acceptForm = new AcceptForm();
            acceptForm.setUserId(userService.findIdByUsername(friendForm.getUserFriend()));
            acceptForm.setUserFriend(id);
            invitationService.acceptInvitations( acceptForm );
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
