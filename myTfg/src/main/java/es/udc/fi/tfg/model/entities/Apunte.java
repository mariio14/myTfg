package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

@Entity
public class Apunte {

    private Long id;

    private String path;

    private Post post;

    public Apunte() {
    }

    public Apunte(String path, Post post) {
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
