package com.spiralg.chatrestful.transformer;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.api.form.MessageForm;
import com.spiralg.chatrestful.model.api.view.ApiListResponse;
import com.spiralg.chatrestful.model.api.view.MessageView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageTransformer {

    public MessageView toResponse(Message message) {
        MessageView view = new MessageView();
        view.setId(message.getId());
        view.setMessage(message.getMessage());
        // TODO
        view.setTime( message.getTime() );
        view.setReceiver( message.getReceiver() );
        view.setSender( message.getSender() );
        return view;
    }

    public List<MessageView> toResponse(List<Message> messages) {
        return messages.stream()
                .map(m -> toResponse(m))
                .collect(Collectors.toList());
    }

    public ApiListResponse<MessageView> toListResponse(List<Message> messages) {
        ApiListResponse<MessageView> result = new ApiListResponse<>();
        result.setTotal(messages.size());
        result.setItems(toResponse(messages));
        return result;
    }

    public Message fromRequest(MessageForm form) {
        Message message = new Message();
        message.setMessage(form.getMessage());
        // TODO
        message.setReceiver( form.getReceiver() );
        message.setSender( form.getSender() );
        return message;
    }
}
