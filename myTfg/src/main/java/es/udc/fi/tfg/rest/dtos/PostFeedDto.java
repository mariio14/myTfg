package es.udc.fi.tfg.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PostFeedDto {

    /**
     * The Interface AllValidations.
     */
    public interface AllValidations {}

    /** The id. */
    private Long id;

    /** The user id. */
    private Long userId;

    private String userName;

    private byte[] avatar;

    /** The titulo. */
    private String titulo;

    /** The descripcion. */
    private String descripcion;

    private String academicYear;

    private LocalDateTime creationDate;

    private BigDecimal avgRating;

    private String subjectName;

    private String uniName;

    public PostFeedDto() {
    }

    public PostFeedDto(Long id, Long userId, String userName, byte[] avatar,
                       String titulo, String descripcion, String academicYear,
                       LocalDateTime creationDate, BigDecimal avgRating, String subjectName, String uniName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.academicYear = academicYear;
        this.creationDate = creationDate;
        this.avgRating = avgRating;
        this.subjectName = subjectName;
        this.uniName = uniName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }
}
