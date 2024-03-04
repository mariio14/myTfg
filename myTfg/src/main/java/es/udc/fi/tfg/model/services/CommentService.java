package es.udc.fi.tfg.model.services;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Comentario;
import es.udc.fi.tfg.model.entities.CommentAndAnswers;
import es.udc.fi.tfg.model.services.exceptions.InvalidCommentException;

public interface CommentService {

    Comentario postComentario(String comentario, Long postId, Long userId) throws InstanceNotFoundException, InvalidCommentException;

    Comentario postRespuestaAComentario(String comentario, Long userId, Long comentarioPadre, Long respuestaId) throws InstanceNotFoundException, 
    InvalidCommentException;
    
    void borrarComentario(Long comentarioId, Long userId) throws InstanceNotFoundException;
    
    Block<CommentAndAnswers> findCommentAnswersByPost(Long postId, int page, int size) throws InstanceNotFoundException; 
}
 