package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long receiverId, String text) throws InstanceNotFoundException;

    List<Message> getMessages(Long selfId, Long userId) throws InstanceNotFoundException;
}
