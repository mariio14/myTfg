package es.udc.fi.tfg.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * The Class UpdatePostParamsDto.
 */
public class UploadPostParamsDto {

	/** The titulo. */
	private String titulo;
	
	/** The descripcion. */
	private String descripcion;

	
	/** The categoria. */
	private Long subjectId;
	
	String academicYear;


	/**
	 * Instantiates a new update post params dto.
	 */
	public UploadPostParamsDto() {
		super();
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	@NotNull
	@Size(min = 1, max = 120)
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	@NotNull
	@Size(min = 1, max = 1000)
	public String getDescripcion() {return descripcion;}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
}