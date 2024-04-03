package es.udc.fi.tfg.rest.dtos;

import java.time.LocalDateTime;

public class MessageDto {

    private Long id;

    private String text;

    private LocalDateTime date;

    private byte[] avatar;

    public MessageDto() {
    }

    public MessageDto(Long id, String text, LocalDateTime date, byte[] avatar) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
