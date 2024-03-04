package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

@Entity
public class Comentario {

    private Long id;

    private String textoComentario;

    private Comentario comentarioPadre;

    private Post post;

    private Users user;

    public Comentario(){}

    public Comentario(String textoComentario, Comentario comentarioPadre, Post post, Users user) {
        this.textoComentario = textoComentario;
        this.comentarioPadre = comentarioPadre;
        this.post = post;
        this.user = user;
    }

    //necesitamos un constructor para la respuesta y otro para el comentario. En uno tendremos un comentarioPadre y en otro no.

    public Comentario(String textoComentario, Post post, Users user) {
        this.textoComentario = textoComentario;
        this.post = post;
        this.user = user;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "comentarioPadreId")
    public Comentario getComentarioPadre() {
        return comentarioPadre;
    }

    public void setComentarioPadre(Comentario comentarioPadre) {
        this.comentarioPadre = comentarioPadre;
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
    @JoinColumn(name = "userId")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    


    
}
