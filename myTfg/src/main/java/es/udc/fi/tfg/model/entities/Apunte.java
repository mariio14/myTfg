package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Apunte {

    private Long id;

    private String name;
    private String contentType;
    private long size;
    private String storagePath;

    private Post post;

    public Apunte() {
    }

    public Apunte(String name, String contentType, long size, String storagePath, Post post) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.storagePath = storagePath;
        this.post = post;
        this.post.getApuntes().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
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
