package es.udc.fi.tfg.model.entities;

import jakarta.persistence.*;

/**
 * The Class Rating.
 */
@Entity
public class Rating{
	/** The id. */
	private Long id;
	/** The rating. */
	private int rating;
	/** The user. */
	private Users user;
	/** The post. */
	private Post post;

	/**
	 * Instantiates a new rating.
	 *
	 * @param rating the rating
	 * @param user the user
	 * @param post the post
	 */
	public Rating(int rating, Users user, Post post) {
		this.rating = rating;
		this.user= user;
		this.post=post;
		this.post.getRatings().add(this);
	}
	
	public Rating(Long id, int rating, Users user, Post post) {
		this.id = id;
		this.rating = rating;
		this.user= user;
		this.post=post;
		this.post.getRatings().add(this);
	}


	/**
	 * Instantiates a new rating.
	 */
	public Rating() {}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {return id;}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {this.id = id;}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public Users getUser(){return user;}

	/**
	 * Gets the post.
	 *
	 * @return the post
	 */
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "postId")
	public Post getPost(){return post;}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public int getRating() {return rating;}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(int rating) {this.rating = rating;}


	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Users user) {this.user = user;}


	/**
	 * Sets the post.
	 *
	 * @param post the new post
	 */
	public void setPost(Post post) {this.post = post;}



}