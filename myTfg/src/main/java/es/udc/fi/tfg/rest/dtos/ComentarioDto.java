package es.udc.fi.tfg.rest.dtos;

public class ComentarioDto {

    private Long id;

    private String textoComentario;

    private String userName;

    private Long userId;

    private Long postId;

    private Long comentarioPadreId;
    
    private byte[] avatar;

    public ComentarioDto(){}

    public ComentarioDto(Long id, String textoComentario, String userName, Long userId, Long postId,
            Long comentarioPadreId, byte[] avatar) {
        this.id = id;
        this.textoComentario = textoComentario;
        this.userName = userName;
        this.userId = userId;
        this.postId = postId;
        this.comentarioPadreId = comentarioPadreId;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getComentarioPadreId() {
        return comentarioPadreId;
    }

    public void setComentarioPadreId(Long comentarioPadreId) {
        this.comentarioPadreId = comentarioPadreId;
    }

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

}
