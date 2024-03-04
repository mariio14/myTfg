package es.udc.fi.tfg.model.entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * The Interface RatingDao.
 */
public interface RatingDao extends JpaRepository<Rating, Long>{
	
	/**
	 * Find by post id.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<Rating> findByPostId(Long id);
	
	/**
	 * Find by user id and post id.
	 *
	 * @param userId the user id
	 * @param postId the post id
	 * @return the optional
	 */
	Optional<Rating> findByUserIdAndPostId(Long userId, Long postId);
}