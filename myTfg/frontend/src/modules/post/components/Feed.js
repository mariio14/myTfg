import { useDispatch, useSelector } from "react-redux";
import * as selectors from "../selectors";
import { useNavigate } from "react-router-dom";
import * as actions from "../actions";
import React from 'react';
import { Pager } from "../../common";
import {Avatar} from "@mui/material";

const Feed = () => {

	const dispatch = useDispatch();
	const feed = useSelector(selectors.getFeed);
	const navigate = useNavigate();
	const universities = useSelector(selectors.getUniversities);
	var text = "Publicaciones";

	const navigatetoPostDetail = (id) => {
		const path = `/feed/posts/${id}`;
		navigate(path);
	}

	const handleUserClick = (id) => {
		const path = `/users/${id}`;
		navigate(path);
	}

	if (feed.result == null) {
		return
	} else if (feed.result.items.length === 0) {
		text = "Sin resultados";
	}

	return (
		<div className="container mt-4">
			<h2 style={{ fontSize: '40px', textAlign: 'center', paddingTop: '0.2rem', paddingBottom: '1rem' }}>
				{text}
			</h2>

			<div className="row">
				{feed.result.items &&
					feed.result.items.map((post, idx) => (
						<div key={idx} className="col-12 col-lg-6 mb-5" data-testid={`post-${idx}`}>
							<div className={`card post-card ${post.cupon ? 'bg-lightblue' : 'bg-white'} rounded-4`}
								onClick={() => navigatetoPostDetail(post.id)}>
								<div className="Post_feed">
									<div className="post-content" style={{ width: '100%', display: 'block' }}>
										<div className='avatar_div' data-testid={'avatar'}>
											<Avatar alt="Remy Sharp" src={"data:image/jpeg;base64," + post.avatar} sx={{ width: 45, height: 45 }}/>
											<h3 data-testid={`post-category-${idx}`}>{post.userName}</h3>
										</div>
										<div className="post-text" >
											<div className="post-text-info">
												<h3 data-testid={`post-category-${idx}`}>{post.uniName}</h3><h3 data-testid={`post-price-${idx}`}>{post.academicYear}</h3>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					))}
			</div>
			{text !== "Sin resultados" && (<Pager
				back={{
					enabled: feed.criteria.page >= 1,
					onClick: () => dispatch(actions.previousFindPostsResultPage(feed.criteria))
				}}
				next={{
					enabled: feed.result.existMoreItems,
					onClick: () => dispatch(actions.nextFindPostsResultPage(feed.criteria))
				}}
				/>
			)}
		</div>
	);
}
export default Feed;