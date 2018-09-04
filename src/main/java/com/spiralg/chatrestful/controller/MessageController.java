package com.spiralg.chatrestful.controller;

import com.spiralg.chatrestful.common.ItemMessage;
import com.spiralg.chatrestful.common.exception.TcpChatException;
import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.MessageForm;
import com.spiralg.chatrestful.model.api.form.MessageSearchForm;
import com.spiralg.chatrestful.model.api.view.ApiListResponse;
import com.spiralg.chatrestful.model.api.view.MessageView;
import com.spiralg.chatrestful.service.MessageService;
import com.spiralg.chatrestful.service.UserService;
import com.spiralg.chatrestful.transformer.MessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageTransformer messageTransformer;

    @GetMapping
    public ResponseEntity<ApiListResponse<MessageView>> list(MessageSearchForm searchForm) {
        List<Message> list = messageService.search(searchForm);
        return ResponseEntity.ok(messageTransformer.toListResponse(list));
    }

    @PostMapping
    public ResponseEntity<MessageView> create(@RequestBody MessageForm form) throws TcpChatException {
        // validate
        if (StringUtils.isEmpty(form.getMessage())) {
            throw new TcpChatException("Message is required");
        }
        if (StringUtils.isEmpty(form.getReceiver())) {
            throw new TcpChatException("Receiver is required");
        }
        if (StringUtils.isEmpty(form.getSender())) {
            throw new TcpChatException("Sender is required");
        }

        messageService.create(messageTransformer.fromRequest(form));
        return null; // TODO
    }
}
