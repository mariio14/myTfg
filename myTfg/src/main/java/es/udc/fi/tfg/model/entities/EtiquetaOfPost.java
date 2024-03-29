package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

@Entity
public class EtiquetaOfPost {

    private Long id;

    private Post post;

    private Etiqueta etiqueta;

    public EtiquetaOfPost() {
    }

    public EtiquetaOfPost(Post post, Etiqueta etiqueta) {
        this.post = post;
        this.etiqueta = etiqueta;
        this.post.getEtiquetas().add(this);
        this.etiqueta.getEtiquetas().add(this);
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
    @JoinColumn(name = "postId")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "etiquetaId")
    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }
}
