package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Message;

import java.util.List;
import java.util.stream.Collectors;

public class MessageConversor {

    public static final List<MessageDto> toMessageDtos(List<Message> messages) {
        return messages.stream().map(MessageConversor::toMessageDto).collect(Collectors.toList());
    }

    public static final MessageDto toMessageDto(Message message) {

        return new MessageDto(message.getId(), message.getText(), message.getDate(), message.getSender().getAvatar());
    }
}
