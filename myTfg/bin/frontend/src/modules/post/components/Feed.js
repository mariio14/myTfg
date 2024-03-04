import {useDispatch, useSelector} from "react-redux";
import * as selectors from "../selectors";
import {useNavigate} from "react-router-dom";
import * as actions from "../actions";
import {useEffect} from 'react';

const Feed = () => {
	const feed = useSelector(selectors.getFeed);
	const dispatch = useDispatch();
	
	useEffect(() => {

        dispatch(actions.getFeed());

    },[dispatch]);
  
	/*
	return(
		<div>			
			<div className="container mt-4">
			    <div className="row justify-content-center">
			        <div className="col-md-6">
			            <div className="card bg-darkgray rounded-3">
						{feed && feed.map((post, idx) => 
							<div key={idx} className="Post_feed">
								<div className = "post-title">
									<h3>{post.titulo}</h3>
									<h2> khfdbkghb</h2>
									<img src={"data:image/jpeg;base64," + post.imagenes} alt="Imagen en base64" />
								</div>
							</div>
						)}
						</div>
					</div>
				</div>
			</div>
		</div>	
		);ç
		*/
}
export default Feed;