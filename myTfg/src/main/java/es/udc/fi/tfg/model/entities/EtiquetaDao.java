package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtiquetaDao extends JpaRepository<Etiqueta, Long> {

    Optional<Etiqueta> findByKeyAndValue(String key, String value);
}
