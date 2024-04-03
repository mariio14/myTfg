package es.udc.fi.tfg.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {

	public enum Type {
		NEW_POST,
		NEW_POST_SUBJECT,
		MESSAGE,
		COMMENT,
		ANSWER
	}
	
	private Long id;
	
	private boolean leido;
	
	private Users user;
	
	private Comentario comentario;

	private Post post;

	private Message message;

	private Type type;


	//Entera
	public Notification(Long id, boolean leido, Users user, Comentario comentario, Post post, Message message, Type type) {
		this.id = id;
		this.leido = leido;
		this.user = user;
		this.comentario = comentario;
		this.post = post;
		this.message = message;
		this.type = type;
	}

	// Comentario en post
	public Notification(boolean leido, Users user, Comentario comentario, Post post, Type type) {
		this.leido = leido;
		this.user = user;
		this.comentario = comentario;
		this.post = post;
		this.type = type;
	}

	// Post nuevo
	public Notification(boolean leido, Users user, Post post, Type type) {
		this.leido = leido;
		this.user = user;
		this.post = post;
		this.type = type;
	}

	// Mensaje privado
	public Notification(boolean leido, Users user, Message message, Type type) {
		this.leido = leido;
		this.user = user;
		this.message = message;
		this.type = type;
	}

	public Notification() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "comentarioId")
	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "messageId")
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}