package es.udc.fi.tfg.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdatePostParamsDtoo {

    private String titulo;
    private String descripcion;
    private String url;
    private Long categoryId;
    private BigDecimal precio;
    private List<String> imagenes;


    public UpdatePostParamsDtoo() {
        super();
    }

    @NotNull
    @Size(min = 1, max = 120)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotNull
    @Size(min = 1, max = 1000)
    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getUrl() {return url;}

    public void setUrl(String URL) {this.url = URL;}

    public Long getCategoryId() {return categoryId;}

    public void setCategoryId(Long categoryId) {this.categoryId = categoryId;}

    public BigDecimal getPrecio() {return precio;}

    public void setPrecio(BigDecimal precio) {this.precio = precio;}

    public List<String> getImagenes() {return imagenes;}

    public void setImagenes(List<String> imagenes) {this.imagenes = imagenes;}

}