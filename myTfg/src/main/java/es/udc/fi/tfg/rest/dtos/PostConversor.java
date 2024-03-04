package es.udc.fi.tfg.rest.dtos;

import es.udc.fi.tfg.model.entities.Post;


import java.math.BigDecimal;

/**
 * The Class PostConversor.
 */
public class PostConversor{

	/**
	 * To post dto.
	 *
	 * @param post the post
	 * @return the post dto
	 */
	public static final PostDto toPostDto(Post post) {
		return new PostDto(post.getId(),post.getUser().getId(), post.getTitle(), post.getDescription(), post.getAcademicYear(),
				post.getCreationDate(), post.getApuntes());
	}

	public static final PostDtoReturn toPostDtoReturn(Post post, BigDecimal avgRating, RatingDto rating) {
		return new PostDtoReturn(post.getId(),post.getUser().getId(), post.getUser().getUserName(), post.getUser().getAvatar(), post.getTitle(), post.getDescription(),
				post.getCreationDate(), post.getAcademicYear(), avgRating, rating, post.getSubject().getSubjectName(), post.getSubject().getUniversity().getUniName());
	}
}