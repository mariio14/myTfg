package es.udc.fi.tfg.rest.controllers;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Message;
import es.udc.fi.tfg.model.services.MessageService;
import es.udc.fi.tfg.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send/{id}")
    public MessageDto sendMessage(@PathVariable Long id, @RequestAttribute Long userId,
                                  @Validated @RequestBody UploadComentarioParamsDto params) throws InstanceNotFoundException {

        Message message = messageService.sendMessage(userId, id, params.getTextoComentario());
        //messagingTemplate.convertAndSend("/{id}", message);   // ("/user/receive/{id}", message);
        return MessageConversor.toMessageDto(message);
    }

    @GetMapping("/{id}")
    public List<MessageDto> getMessages(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException {

        List<Message> messages = messageService.getMessages(userId,id);
        return MessageConversor.toMessageDtos(messages);
    }
}
