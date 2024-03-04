package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class Post.
 */
@Entity
public class Post {


    /** The id. */
    private Long id;

    /** The user. */
    private Users user;

    /** The title. */
    private String title;

    /** The description. */
    private String description;

    private String academicYear;

    private LocalDateTime creationDate;

    private Subject subject;

    /** The ratings. */
    private Set<Rating> ratings = new HashSet<>();

    private Set<Comentario> comentarios = new HashSet<>();

    private Set<Apunte> apuntes = new HashSet<>();

    /**
     * Instantiates a new post.
     */
    public Post(){}

    public Post(Users user, String title, String description, String academicYear, LocalDateTime creationDate, Subject subject) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.academicYear = academicYear;
        this.creationDate = creationDate;
        this.subject = subject;
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {return id;}

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {this.id = id;}


    /**
     * Gets the user.
     *
     * @return the user
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public Users getUser(){return user;}


    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(Users user){this.user=user;}

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitle() {return title;}

    /**
     * Sets the titulo.
     *
     * @param title the new titulo
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {return description;}

    /**
     * Sets the descripcion.
     *
     * @param description the new descripcion
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Gets the ratings.
     *
     * @return the ratings
     */
    @OneToMany(mappedBy="post")
    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @OneToMany(mappedBy="post")
    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @OneToMany(mappedBy="post")
    public Set<Apunte> getApuntes() {
        return apuntes;
    }

    public void setApuntes(Set<Apunte> apuntes) {
        this.apuntes = apuntes;
    }
}