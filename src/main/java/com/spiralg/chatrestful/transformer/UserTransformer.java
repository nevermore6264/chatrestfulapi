package com.spiralg.chatrestful.transformer;

import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.UserForm;
import com.spiralg.chatrestful.model.api.view.ApiListResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformer {

    public UserView toResponse(User user) {
        UserView view = new UserView();
        view.setId( user.getId() );
        view.setUserName( user.getUserName() );
        return view;
    }

    public List<UserView> toResponse(List<User> users) {
        return users.stream()
                .map( m -> toResponse( m ) )
                .collect(Collectors.toList());
    }

    public ApiListResponse<UserView> toListResponse(List<User> users){
        ApiListResponse<UserView> result = new ApiListResponse<>();
        result.setTotal( users.size() );
        result.setItems( toResponse( users ) );
        return result;
    }

    public User fromRequest(UserForm form) {
        User user = new User();
        user.setUserName( form.getUserName() );
        return user;
    }
}
