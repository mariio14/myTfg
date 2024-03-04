package es.udc.fi.tfg.model.entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioDao extends JpaRepository<Comentario, Long>{

    Slice<Comentario> findByPostIdAndComentarioPadreIdIsNull(Long postId, Pageable pageable);

    List<Comentario> findByComentarioPadreId(Long comentarioPadreId);
    
    Optional<Comentario> findByComentarioPadreIdAndPostIdAndUserId(Long comentarioPadre, Long postId, Long userId);
    
    
}
