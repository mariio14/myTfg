package es.udc.fi.tfg.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtiquetaOfPostDao extends JpaRepository<EtiquetaOfPost, Long> {

    Optional<EtiquetaOfPost> findByPostAndEtiqueta(Post post, Etiqueta etiqueta);

    Slice<EtiquetaOfPost> findByEtiquetaId(Long etiquetaId, Pageable pageable);
}
