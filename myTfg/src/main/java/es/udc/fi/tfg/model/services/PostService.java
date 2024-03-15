package es.udc.fi.tfg.model.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import es.udc.fi.tfg.model.common.exceptions.InstanceNotFoundException;
import es.udc.fi.tfg.model.entities.Post;
import es.udc.fi.tfg.model.entities.Subject;
import es.udc.fi.tfg.model.entities.University;

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
	 Post uploadPost(Long userId, String titulo, String descripcion, String academicYear, Long subjectId) throws InstanceNotFoundException;


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

	public List<University> findAllUniversities();

	public List<Subject> findAllSubjectsByUni(Long id);
}
