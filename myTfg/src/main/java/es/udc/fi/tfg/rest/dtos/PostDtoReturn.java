package es.udc.fi.tfg.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.List;


/**
 * The Class PostDto.
 */
public class PostDtoReturn {

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
    
    private RatingDto rating;

	private String subjectName;

	private String uniName;

	private byte [] files;

	private List<String> fileNames;

	private String urls;


	public PostDtoReturn() {
	}

	public PostDtoReturn(Long id, Long userId, String userName, byte[] avatar, String titulo, String descripcion,
						 LocalDateTime creationDate, String academicYear, BigDecimal avgRating, RatingDto rating,
						 String subjectName, String uniName, byte [] files, List<String> fileNames, String urls) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.avatar = avatar;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.creationDate = creationDate;
		this.academicYear = academicYear;
		this.avgRating = avgRating;
		this.rating = rating;
		this.subjectName = subjectName;
		this.uniName = uniName;
		this.files = files;
		this.fileNames = fileNames;
		this.urls = urls;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public RatingDto getRating() {
		return rating;
	}

	public void setRating(RatingDto rating) {
		this.rating = rating;
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

	public byte[] getFiles() {
		return files;
	}

	public void setFiles(byte[] files) {
		this.files = files;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}
}