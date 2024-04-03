package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NotificationDao notificationDao;


    @Override
    public Message sendMessage(Long senderId, Long receiverId, String text) throws InstanceNotFoundException {

        Users user = permissionChecker.checkUser(senderId);

        Optional<Users> usersOptional = userDao.findById(receiverId);
        if (usersOptional.isEmpty()) {
            throw new InstanceNotFoundException("Usuario no encontrado", receiverId);
        }

        Message message = new Message(user, usersOptional.get(), LocalDateTime.now(), text);
        messageDao.save(message);

        Notification notification = new Notification(false, usersOptional.get(), message, Notification.Type.MESSAGE);
        notificationDao.save(notification);
        return message;
    }

    @Override
    public List<Message> getMessages(Long selfId, Long userId) throws InstanceNotFoundException {
        Users user = permissionChecker.checkUser(selfId);

        Optional<Users> usersOptional = userDao.findById(userId);
        if (usersOptional.isEmpty()) {
            throw new InstanceNotFoundException("Usuario no encontrado", userId);
        }

        //return messageDao.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByDate(selfId,userId,userId,selfId);
        return messageDao.findMessagesBetweenPairs(selfId, userId);
    }
}
