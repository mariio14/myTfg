package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Notification;

public class NotificationConversor {
	
	public static final NotificationDto toNotificationDto(Notification notification) {

		return new NotificationDto(notification.getId(), notification.isRead(), notification.getUser().getId(),
				notification.getComentario().getUser().getUserName(), notification.getComentario().getUser().getAvatar(),
				notification.getComentario().getPost().getId(),
				notification.getComentario().getPost().getTitle(), notification.getComentario().getId(),
				notification.getComentario().getTextoComentario(), true);
    }
	
	public static final NotificationDto toNotificationDtoCommentNull(Notification notification) {

		return new NotificationDto(notification.getId(), notification.isRead(), notification.getUser().getId(),
				notification.getPost().getUser().getUserName(), notification.getPost().getUser().getAvatar(),
				notification.getPost().getId(),
				notification.getPost().getTitle(), 0L, "", false);
    }
}