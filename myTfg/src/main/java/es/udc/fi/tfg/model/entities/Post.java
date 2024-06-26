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

    private int academicYear;

    private LocalDateTime creationDate;

    private Subject subject;

    private University university;

    private BigDecimal avgRating;

    private Set<Rating> ratings = new HashSet<>();

    private Set<Comentario> comentarios = new HashSet<>();

    private Set<Apunte> apuntes = new HashSet<>();

    private Set<EtiquetaOfPost> etiquetas = new HashSet<>();

    /**
     * Instantiates a new post.
     */
    public Post(){}

    public Post(Users user, String title, String description, int academicYear, LocalDateTime creationDate, Subject subject, BigDecimal avgRating) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.academicYear = academicYear;
        this.creationDate = creationDate;
        this.subject = subject;
        this.university = subject.getUniversity();
        this.avgRating = avgRating;
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

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "universityId")
    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
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

    @OneToMany(mappedBy="post")
    public Set<EtiquetaOfPost> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<EtiquetaOfPost> etiquetas) {
        this.etiquetas = etiquetas;
    }
}