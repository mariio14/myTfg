package es.udc.fi.tfg.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Comentario;
import es.udc.fi.tfg.model.entities.ComentarioDao;
import es.udc.fi.tfg.model.entities.CommentAndAnswers;
import es.udc.fi.tfg.model.entities.Notification;
import es.udc.fi.tfg.model.entities.NotificationDao;
import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.PostDao;
import es.udc.fi.tfg.model.entities.UserDao;
import es.udc.fi.tfg.model.entities.Users;
import es.udc.fi.tfg.model.services.exceptions.InvalidCommentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ComentarioDao comentarioDao;
    
    @Autowired
    private NotificationDao notificationDao;
    
    @Autowired
    private UserDao userDao;

    /** The post dao. */
    @Autowired
    private PostDao postDao;

    /** The permission checker. */
    @Autowired
    private PermissionChecker permissionChecker;

    @Override
    public Comentario postComentario(String comentario, Long postId, Long userId)
            throws InstanceNotFoundException, InvalidCommentException {

        Users registeredUser = permissionChecker.checkUser(userId);

        if (comentario.isEmpty()) {
            throw new InvalidCommentException("Un comentario no puede estar vacio.");
        }

        Optional<Post> post = postDao.findById(postId);

        if (!post.isPresent()) {
            throw new InstanceNotFoundException("Post no encontrado", post);
        }

        Post newPost = post.get();

        Comentario newComment = new Comentario(comentario, newPost, registeredUser);
        Comentario savedComment = comentarioDao.save(newComment);
                
        //Creamos la notificacion, excepto si el usuario se comenta a si mismo
        if(newPost.getUser() != registeredUser) {
        	Notification notification = new Notification(false, false, newPost.getUser(), savedComment);
        	notificationDao.save(notification);
        }

        return newComment;

    }

    @Override
    public Comentario postRespuestaAComentario(String comentario, Long userId, Long comentarioPadre, Long respuestaId)
            throws InstanceNotFoundException, InvalidCommentException {
        Users registeredUser = permissionChecker.checkUser(userId);

        if (comentario.isEmpty()) {
            throw new InvalidCommentException("Un comentario no puede estar vacio.");
        }
        
        Optional<Comentario> comentarioPadre1 = comentarioDao.findById(comentarioPadre);

        if (!comentarioPadre1.isPresent()) {
            throw new InstanceNotFoundException("Comentario no encontrado", comentarioPadre);
        }
        Comentario comentarioPadreFinal = comentarioPadre1.get();
        
        Post post = comentarioPadreFinal.getPost();

        // we use a different constructor, because this one is a response to a "Father"
        // commentary
        Comentario newAnswer = new Comentario(comentario, comentarioPadreFinal, post, registeredUser);
        Comentario savedAnswer = comentarioDao.save(newAnswer);
        
        Notification notification;
        if(respuestaId == 0L) {
            //Creamos la notificacion, excepto si el usuario se responde a si mismo
            if (comentarioPadreFinal.getUser() != registeredUser) {
                notification = new Notification(false, false, comentarioPadreFinal.getUser(), savedAnswer);
                notificationDao.save(notification);
            }
        } else {
            Optional<Comentario> answer = comentarioDao.findById(respuestaId);
            if (!answer.isPresent()) {
                throw new InstanceNotFoundException("Estas respondiendo al comentario de un usuario inexistente.", answer);
            }

            if (answer.get().getUser() != registeredUser) {
                notification = new Notification(false, false, answer.get().getUser(), savedAnswer);
                notificationDao.save(notification);
            }
        }

        return newAnswer;

    }
    

    @Override
    public void borrarComentario(Long comentarioId, Long userId) throws InstanceNotFoundException {
        
        permissionChecker.checkUser(userId);
		
		comentarioDao.deleteById(comentarioId);
    }

	@Override
	public Block<CommentAndAnswers> findCommentAnswersByPost(Long postId, int page, int size)
			throws InstanceNotFoundException {
		
		Optional<Post> post = postDao.findById(postId);

        if (!post.isPresent()) {
            throw new InstanceNotFoundException("Post no encontrado", post);
        }

        Slice<Comentario> comments = comentarioDao.findByPostIdAndComentarioPadreIdIsNull(postId, PageRequest.of(page, size));
        
        List<CommentAndAnswers> list = new ArrayList<>();
        for(Comentario comment : comments.getContent()) {
        	List <Comentario> answers = comentarioDao.findByComentarioPadreId(comment.getId());
        	
        	CommentAndAnswers commentAndAnswers = new CommentAndAnswers(comment, answers);
        	
        	list.add(commentAndAnswers);
        }
		
        return new Block<>(list, comments.hasNext());
	}

}