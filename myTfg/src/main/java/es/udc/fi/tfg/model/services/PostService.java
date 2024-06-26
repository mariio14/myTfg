package es.udc.fi.tfg.model.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Apunte;
import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Subject;
import es.udc.fi.tfg.model.entities.University;
import org.springframework.security.core.parameters.P;
import es.udc.fi.tfg.model.services.exceptions.AlreadyFollowingException;
import es.udc.fi.tfg.model.services.exceptions.NotFollowingException;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Interface PostService.
 */
public interface PostService {

    /**
     * Upload post.
     *
     * @param userId the user id
     * @param titulo the titulo
     * @param descripcion the descripcion
     * @return the post
     * @throws InstanceNotFoundException the instance not found exception
     */
	 Post uploadPost(Long userId, String titulo, String descripcion, String academicYear, Long subjectId, List<MultipartFile> files, List<String> etiquetas) throws InstanceNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeyException;


    Block<Post> findPosts(String keywords, Long universityId, Long subjectId, String minYear, String maxYear, String order, int page, int size);

    /**
     * Find posts by user id.
     *
     * @param userId the user id
     * @param page the page
     * @param size the size
     * @return the block
     */
    Block<Post> findPostsByUserId(Long userId, int page, int size);

    /**
     * Find post by id.
     *
     * @param id the id
     * @return the post
     * @throws InstanceNotFoundException the instance not found exception
     */
    Post findPostById(Long id) throws InstanceNotFoundException;

	/**
	 * Delete post.
	 *
	 * @param userId the user id
	 * @param postId the post id
	 * @throws InstanceNotFoundException the instance not found exception
	 */
	void deletePost(Long userId, Long postId) throws InstanceNotFoundException;

	Post updatePost(Long userId,Long postId, String titulo, String descripcion, String academicYear, Long subjectId)
			throws InstanceNotFoundException;

	List<University> findAllUniversities();

	public List<Subject> findAllSubjectsByUni(Long id);

	public List<Apunte> findApuntesByPost(Long id);

	void followSubject(Long userId, Long subjectId) throws AlreadyFollowingException, InstanceNotFoundException;

	void unfollowSubject(Long userId, Long subjectId) throws InstanceNotFoundException, NotFollowingException;

	Block<Post> findPostsByEtiquetaId(Long etiquetaId, int page, int size) throws InstanceNotFoundException;
}
