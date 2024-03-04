package es.udc.fi.tfg.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.udc.fi.tfg.model.entities.Notification;
import es.udc.fi.tfg.model.entities.NotificationDao;
import es.udc.fi.tfg.model.entities.UserDao;
import es.udc.fi.tfg.model.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
    private UserDao userDao;

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private PermissionChecker permissionChecker;

	@Override
	public void deleteNotification(Long id, Long userId) throws InstanceNotFoundException {
		
		permissionChecker.checkUser(userId);
		
		notificationDao.deleteById(id);
	}

	@Override
	public List<Notification> getNotificationsByUser(Long userId) throws InstanceNotFoundException {
		
		Optional<Users> user = userDao.findById(userId);

        if (!user.isPresent()) {
            throw new InstanceNotFoundException("User no encontrado", user);
        }
        
        List<Notification> notifications = notificationDao.findByUserIdOrderByIdDesc(userId);
        
        List<Notification> notificationsCopy = new ArrayList<>();
        
        for(Notification notification : notifications) {
        	notificationsCopy.add(new Notification(notification.getId(), notification.isRead(), notification.isNewPost(),
        			notification.getUser(), notification.getComentario(), notification.getPost()));
        	if(notification.isRead()==false)
        		notification.setRead(true);
        }
        
        return notificationsCopy;
	}

	@Override
	public int getNotReadNotifications(Long userId) throws InstanceNotFoundException {
		Optional<Users> user = userDao.findById(userId);

        if (!user.isPresent()) {
            throw new InstanceNotFoundException("User no encontrado", user);
        }
        
        return notificationDao.countByUserIdAndReadFalse(userId);
	}
	
}