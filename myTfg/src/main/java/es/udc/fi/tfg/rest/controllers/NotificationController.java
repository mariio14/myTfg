package es.udc.fi.tfg.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import es.udc.fi.tfg.model.entities.Notification;
import es.udc.fi.tfg.model.services.NotificationService;
import es.udc.fi.tfg.rest.dtos.NotificationConversor;
import es.udc.fi.tfg.rest.dtos.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@DeleteMapping("/delete/{id}")
	public void deleteNotification(@PathVariable Long id, @RequestAttribute Long userId) throws InstanceNotFoundException {
		notificationService.deleteNotification(id, userId);
	}
	
	@GetMapping("/findAll")
	public List<NotificationDto> getAllNotifications(@RequestAttribute Long userId) throws InstanceNotFoundException {

		List<Notification> notifications = notificationService.getNotificationsByUser(userId);
		
		
		List<NotificationDto> notificationDtoList = new ArrayList<>();
		for(Notification notification : notifications) {

			NotificationDto notificationDto;
			if(notification.getComentario() == null)
				notificationDto = NotificationConversor.toNotificationDtoCommentNull(notification);
			else
				notificationDto = NotificationConversor.toNotificationDto(notification);
			notificationDtoList.add(notificationDto);
		}

		return notificationDtoList;
	}
	
	@GetMapping("/notRead")
	public int getNotReadNotifications(@RequestAttribute Long userId) throws InstanceNotFoundException {

		return notificationService.getNotReadNotifications(userId);
	}
}