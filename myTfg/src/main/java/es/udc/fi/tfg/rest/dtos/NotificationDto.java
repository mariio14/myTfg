package es.udc.fi.tfg.rest.dtos;

public class NotificationDto {
	
	private Long id;
	
	private boolean read;
	
	private byte[] avatar;

	private String text;
	
	private String redirect;

	private Long redirectId;

	private String redirectUserName;

	public NotificationDto() {
	}

	public NotificationDto(Long id, boolean read, byte[] avatar, String text, String redirect, Long redirectId, String redirectUserName) {
		this.id = id;
		this.read = read;
		this.avatar = avatar;
		this.text = text;
		this.redirect = redirect;
		this.redirectId = redirectId;
		this.redirectUserName = redirectUserName;
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

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public Long getRedirectId() {
		return redirectId;
	}

	public void setRedirectId(Long redirectId) {
		this.redirectId = redirectId;
	}

	public String getRedirectUserName() {
		return redirectUserName;
	}

	public void setRedirectUserName(String redirectUserName) {
		this.redirectUserName = redirectUserName;
	}
}