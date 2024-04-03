package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.*;

public class NotificationConversor {

	public static final NotificationDto toNotificationDto(Notification notification) {

		byte[] avatar;
		String redirect = "post";
		Long redirectId;
		String text;
		String redirectUserName = "";
		if(notification.getType() == Notification.Type.MESSAGE){
			Users sender = notification.getMessage().getSender();
			avatar = sender.getAvatar();
			redirect = "message";
			redirectId = sender.getId();
			redirectUserName = sender.getUserName();
			text = "El usuario " + sender.getUserName() + " te ha enviado un mensaje.";
		} else if (notification.getType() == Notification.Type.NEW_POST) {
			Post post = notification.getPost();
			Users users = post.getUser();
			avatar = users.getAvatar();
			redirectId = post.getId();
			text = "El usuario " + users.getUserName() + " ha subido una nueva publicacion, ve a verla!";
		} else if (notification.getType() == Notification.Type.NEW_POST_SUBJECT) {
			Post post = notification.getPost();
			avatar = post.getUser().getAvatar();
			redirectId = post.getId();
			text = "Hay una nueva publicacion de la asignatura " + post.getSubject().getSubjectName() + ", ve a verla!";
		} else if (notification.getType() == Notification.Type.COMMENT){
			Comentario comentario = notification.getComentario();
			Users user = comentario.getUser();
			avatar = user.getAvatar();
			redirectId = comentario.getPost().getId();
			text = "El usuario " + user.getUserName() + " te ha comentado: \"" + comentario.getTextoComentario() + "\"";
		} else {
			Comentario comentario = notification.getComentario();
			Users user = comentario.getUser();
			avatar = user.getAvatar();
			redirectId = comentario.getPost().getId();
			text = "El usuario " + user.getUserName() + " te ha respondido: \"" + comentario.getTextoComentario() + "\"";
		}

		return new NotificationDto(notification.getId(), notification.isLeido(), avatar, text, redirect, redirectId, redirectUserName);
	}
}