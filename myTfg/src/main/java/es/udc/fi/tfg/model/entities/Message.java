package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {

    private Long id;

    private Users sender;

    private Users receiver;

    private LocalDateTime date;

    private String text;

    public Message() {
    }

    public Message(Users sender, Users receiver, LocalDateTime date, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId")
    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId")
    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
