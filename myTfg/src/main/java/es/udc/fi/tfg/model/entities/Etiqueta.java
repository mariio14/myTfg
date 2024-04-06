package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Etiqueta {

    private Long id;

    private String clave;

    private String value;

    private Set<EtiquetaOfPost> etiquetas = new HashSet<>();

    public Etiqueta() {
    }

    public Etiqueta(String clave, String value) {
        this.clave = clave;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @OneToMany(mappedBy="etiqueta")
    public Set<EtiquetaOfPost> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<EtiquetaOfPost> etiquetas) {
        this.etiquetas = etiquetas;
    }
}
