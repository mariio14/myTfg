import * as selectors from "../selectors";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import * as actions from '../actions';
import { RatingStars } from "../../ratings"
import { AverageRating } from "../../ratings"
import users from "../../users";
import { Comentarios } from "../../comments";

const PostDetails = () => {

	const dispatch = useDispatch();
	const { id } = useParams();
	const post = useSelector(selectors.getPost);
	const universities = useSelector(selectors.getUniversities);
	const [isCopied, setIsCopied] = useState(false);
	const loggedIn = useSelector(users.selectors.isLoggedIn);
	const userIdReal = useSelector(users.selectors.getUserId);


	const handleCopyClick = () => {
		navigator.clipboard.writeText(post.cupon);
		setIsCopied(true);
	};

	useEffect(() => {

		const postId = Number(id);

		if (!Number.isNaN(id)) {
			dispatch(actions.findPostbyId(postId))
		}
		
		return () => dispatch(actions.postByIdClear);
	}, [id, dispatch]
	)
	
	const handleFavoriteClick = (event) => {
		event.preventDefault();
		
		if(!Number.isNaN(id)) {
			if(post.userFollows)
				dispatch(actions.unfollowPost(Number(id)));
			else
	            dispatch(actions.followPost(Number(id)));
        }
	};
	
	const isUserRating = (userId) => {
		return userIdReal === userId;
	}
	
	const FavoriteIcon = () => (
	  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
	      <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
	  </svg>
	);
	
	const FavoriteIconRed = () => (
	  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="red" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
	      <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
	  </svg>
	);

	if (!post)
		return null;

	return (
		<div className="container mt-4" style={{ paddingBottom: '2rem' }}>
			<div className="row justify-content-center">
				<div className="col-md-9">
					<div className={`post-card-complete 'bg-white' rounded-4`}>
						<div className="post">
							<div className="post-header">
								<div className="post-text">
									<div className="header-box">
										<div className="post-title" style={{textAlign:'center'}}>
											<div className="post-card-complete" style={{ position: 'relative' }}>
											  <button style={{ position: 'absolute', top: '10px', right: '10px', background: 'none', border: 'none' }} onClick={(e) => handleFavoriteClick(e)}>
											    {post.userFollows ? <FavoriteIconRed /> : <FavoriteIcon />}
											  </button>
											</div>
											<h2 data-testid="post-details-title">
												{post.titulo}
											</h2>
											<hr></hr>
											<div className="post-text-info">
												<h4 data-testid="post-details-category">{post.uniName}</h4>
												<h4 data-testid="post-details-price">${post.precio}</h4>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div className="post-complete">
								<div className="post-text">
									<h4 className="descripcion" data-testid="post-details-desc" style={{wordWrap:'break-word'}}>{post.descripcion}</h4>
									<h4 className="academicYear" data-testid="post-details-desc" style={{wordWrap:'break-word'}}>{post.academicYear}</h4>

									<div style={{display:'flex', justifyContent:'space-between'}} data-testid="post-details-rating-stars">
									{loggedIn && !isUserRating(post.userId) && <div style={{ marginTop: '2rem' }}>
										<h4 style={{ marginBottom: '0' }}>Valórame!</h4>
										<RatingStars rating={post.rating}/>
									</div>}
										<div className="five-pointed-star"></div>
										<h3 className="rating-box" style={{margin:'auto 0', position:'relative'}} data-testid="post-details-avgRating"> <AverageRating avgRating={post.avgRating} /> </h3>
									</div>

								</div>
								<br/>
								<Comentarios/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div >
	)
}
export default PostDetails;