package es.udc.fi.tfg.model.services;

import java.util.List;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Notification;

public interface NotificationService {
		
	void deleteNotification(Long id, Long userId) throws InstanceNotFoundException;
	
	List<Notification> getNotificationsByUser(Long userId) throws InstanceNotFoundException;
	
	int getNotReadNotifications(Long userId) throws InstanceNotFoundException;
}