package es.udc.fi.tfg.model.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The Interface PostDao.
 */
public interface PostDao extends JpaRepository<Post, Long>, CustomizedPostDao{
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Post> findAll();
	
	/**
	 * Find by user id.
	 *
	 * @param userId the user id
	 * @param pageable the pageable
	 * @return the slice
	 */
	Slice<Post> findByUserId(Long userId, Pageable pageable);

}
