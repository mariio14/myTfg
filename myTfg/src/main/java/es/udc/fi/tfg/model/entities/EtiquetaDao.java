package es.udc.fi.tfg.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EtiquetaDao extends JpaRepository<Etiqueta, Long> {

    Optional<Etiqueta> findByClaveAndValue(String key, String value);

    @Query("SELECT DISTINCT e.clave FROM Etiqueta e")
    List<String> findAllDistinctKeys();

    @Query("SELECT e.value FROM Etiqueta e WHERE e.clave = :key")
    List<String> findValuesByKey(@Param("key") String key);
}
