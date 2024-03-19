package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Apunte;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


/**
 * The Class PostDto.
 */
public class PostDto {

    /**
     * The Interface AllValidations.
     */
    public interface AllValidations {}

    /** The id. */
    private Long id;

    /** The user id. */
    private Long userId;

    /** The titulo. */
    private String titulo;

    /** The descripcion. */
    private String descripcion;

    private String academicYear;

    private LocalDateTime creationDate;

    private List<byte []> files;

    private int size;


    /**
     * Instantiates a new post dto.
     */
    public PostDto(){}



    public PostDto(Long id,Long userId, String titulo, String descripcion, String academicYear, LocalDateTime creationDate,
                   List<byte []> files, int size){
        this.id=id;
        this.userId=userId;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.academicYear=academicYear;
        this.creationDate= creationDate;
        this.files= files;
        this.size = size;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {return id;}

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public Long getUserId(){return userId;}


    /**
     * Sets the user.
     *
     * @param userId the new user
     */
    public void setUserId(Long userId){this.userId=userId;}

    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    @NotNull(groups={AllValidations.class})
    @Size(min=1, max=120, groups={AllValidations.class})
    public String getTitulo() {return titulo;}

    /**
     * Sets the titulo.
     *
     * @param titulo the new titulo
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    @NotNull(groups={AllValidations.class})
    @Size(min=1, max=1000, groups={AllValidations.class})
    public String getDescripcion() {return descripcion;}

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<byte []> getFiles() {
        return files;
    }

    public void setFiles(List<byte []> files) {
        this.files = files;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}