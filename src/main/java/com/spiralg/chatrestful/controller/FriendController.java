package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.Token;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.FriendForm;
import com.spiralg.chatrestful.service.FriendService;
import com.spiralg.chatrestful.service.TokenService;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/")
public class FriendController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    Pattern pattern = Pattern.compile("^[a-z0-9_]+");

    @PostMapping(path = {"users/{id}/friends"})
    public ResponseEntity addFriend(@PathVariable("id") int id,@RequestBody FriendForm friendForm, HttpServletRequest request) throws Exception {
        Token token = tokenService.getByUserId(id);
        String t = request.getHeader( "token");
        System.out.println(token+"--"+t+"--"+friendForm.getUserFriend());
        if(token.getKey().equals(t)){
            Friend friend = new Friend();
            friend.setUserId(id);
            friend.setUserFriend(userService.findIdByUsername(friendForm.getUserFriend()));
            friend.setStatus((byte)0);
            System.out.println(userService.findIdByUsername( friendForm.getUserFriend() ));
            friendService.create( friend );
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = {"friends"})
    public ResponseEntity<List<User>> getAllFriend(@RequestParam("username") String userName) {

            if (pattern.matcher(userName).matches()) {
                return new ResponseEntity<>(friendService.getAllFriend(userName), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }
}
