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
	
	public Long id;
	
	public boolean read;

	public boolean newPost;

	public boolean newPostSubject;
	
	public Users user;
	
	public Comentario comentario;

	public Post post;

	

	// Entera
	public Notification(Long id, boolean read, boolean newPost, Users user, Comentario comentario, Post post) {
		this.id = id;
		this.read = read;
		this.newPost = newPost;
		this.user = user;
		this.comentario = comentario;
		this.post = post;
	}

	// Responde a comentario
	public Notification(boolean read, boolean newPost, Users user, Comentario comentario) {
		this.read = read;
		this.newPost = newPost;
		this.user = user;
		this.comentario = comentario;
	}

	// Post de usuario al que sigues o de asignatura
	public Notification(boolean read, boolean newPost, boolean newPostSubject, Users user, Post post) {
		this.read = read;
		this.newPost = newPost;
		this.newPostSubject = newPostSubject;
		this.user = user;
		this.post = post;
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

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isNewPost() {
		return newPost;
	}

	public void setNewPost(boolean newPost) {
		this.newPost = newPost;
	}

	public boolean isNewPostSubject() {
		return newPostSubject;
	}

	public void setNewPostSubject(boolean newPostSubject) {
		this.newPostSubject = newPostSubject;
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
	
}