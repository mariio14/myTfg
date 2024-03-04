package es.udc.fi.tfg.rest.dtos;

public class NotificationDto {
	
	private Long id;
	
	private boolean read;
	
	private Long userId;
	
	private String userName;
	
	private byte[] avatar;
	
	private Long postId;
		
	private String postTitulo;
	
	private Long comentarioId;
	
	private String textoComentario;
	
	private boolean respondeAComentario;

	public NotificationDto() {
	}

	public NotificationDto(Long id, boolean read, Long userId, String userName, byte[] avatar, Long postId, String postTitulo,
						   Long comentarioId, String textoComentario, boolean respondeAComentario) {
		this.id = id;
		this.read = read;
		this.userId = userId;
		this.userName = userName;
		this.postId = postId;
		this.postTitulo = postTitulo;
		this.comentarioId = comentarioId;
		this.textoComentario = textoComentario;
		this.respondeAComentario = respondeAComentario;
		this.avatar = avatar;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostTitulo() {
		return postTitulo;
	}

	public void setPostTitulo(String postTitulo) {
		this.postTitulo = postTitulo;
	}

	public Long getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(Long comentarioId) {
		this.comentarioId = comentarioId;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public boolean isRespondeAComentario() {
		return respondeAComentario;
	}

	public void setRespondeAComentario(boolean respondeAComentario) {
		this.respondeAComentario = respondeAComentario;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}