package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.InvitationForm;
import com.spiralg.chatrestful.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping(path = "/api/")
public class InvitationController {

    Pattern pattern = Pattern.compile("^[0-9]$");


    @Autowired
    private InvitationService invitationService;

    @GetMapping(path = {"users/{id}/friends"})
    public ResponseEntity<List<Friend>> getAllInvitations(@PathVariable("id") int id){
            return new ResponseEntity<>( invitationService.getAllInvitations( new InvitationForm(id) ), HttpStatus.OK );
    }
}
