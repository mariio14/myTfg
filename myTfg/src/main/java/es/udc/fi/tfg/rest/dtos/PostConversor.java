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
		int year = post.getAcademicYear()+1;
		return new PostDto(post.getId(),post.getUser().getId(), post.getTitle(), post.getDescription(), post.getAcademicYear() + "/" + year,
				post.getCreationDate(), post.getApuntes());
	}

	public static final PostDtoReturn toPostDtoReturn(Post post, RatingDto rating) {
		int year = post.getAcademicYear()+1;
		return new PostDtoReturn(post.getId(),post.getUser().getId(), post.getUser().getUserName(), post.getUser().getAvatar(), post.getTitle(), post.getDescription(),
				post.getCreationDate(), post.getAcademicYear() + "/" + year, post.getAvgRating(), rating, post.getSubject().getSubjectName(),
				post.getSubject().getUniversity().getUniName());
	}

	public static final PostFeedDto toPostFeedDto(Post post) {
		int year = post.getAcademicYear()+1;
		return new PostFeedDto(post.getId(),post.getUser().getId(), post.getUser().getUserName(), post.getUser().getAvatar(), post.getTitle(), post.getDescription(),
				post.getAcademicYear() + "/" + year, post.getCreationDate(), post.getAvgRating(), post.getSubject().getSubjectName(),
				post.getSubject().getUniversity().getUniName());
	}
}