package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Etiqueta {

    private Long id;

    private String key;

    private String value;

    private Set<EtiquetaOfPost> etiquetas = new HashSet<>();

    public Etiqueta() {
    }

    public Etiqueta(String key, String value) {
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
