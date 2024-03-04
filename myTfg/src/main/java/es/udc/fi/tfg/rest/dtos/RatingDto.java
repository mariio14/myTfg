package es.udc.fi.tfg.rest.dtos;


/**
 * The Class RatingDto.
 */
public class RatingDto {
	
	/** The id. */
	private Long id;
	
	/** The rating. */
	private int rating;
	
	/** The user id. */
	private Long userId;
	
	/** The post id. */
	private Long postId;
	
	
	/**
	 * Instantiates a new rating dto.
	 */
	public RatingDto() {}

	/**
	 * Instantiates a new rating dto.
	 *
	 * @param id the id
	 * @param rating the rating
	 * @param userId the user id
	 * @param postId the post id
	 */
	public RatingDto(Long id, int rating, Long userId, Long postId) {
		this.id = id;
		this.rating = rating;
		this.userId = userId;
		this.postId = postId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the post id.
	 *
	 * @return the post id
	 */
	public Long getPostId() {
		return postId;
	}

	/**
	 * Sets the post id.
	 *
	 * @param postId the new post id
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	
	
	
}