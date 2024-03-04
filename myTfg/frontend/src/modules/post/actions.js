import * as actionTypes from './actionTypes';
import backend from '../../backend';
import * as selectors from "./selectors";


export const postear = (titulo, descripcion , subjectId, academicYear, onSuccess, onErrors) => dispatch =>
	backend.postService.postear(descripcion, titulo, subjectId, academicYear,
		onSuccess
		,onErrors);

const getFeedCompleted =  feed => ({
	type: actionTypes.GET_FEED_COMPLETED,
	feed
});

export const getFeed = criteria => dispatch => {

	dispatch(clearFeedSearch());
	backend.postService.obtenerFeed(criteria,
		result => dispatch(getFeedCompleted({criteria, result})));

}

export const getMyFeed = criteria => dispatch => {
	dispatch(clearFeedSearch());
	backend.postService.obtenerMyFeed(criteria,
		result => dispatch(getFeedCompleted({criteria, result})));
}

const clearFeedSearch = () => ({
	type: actionTypes.CLEAR_FEED_SEARCH
});

export const previousFindPostsResultPage = criteria =>
	getFeed({...criteria, page: criteria.page-1});

export const nextFindPostsResultPage = criteria =>
	getFeed({...criteria, page: criteria.page+1});

export const previousFindMyPostsResultPage = criteria =>
	getMyFeed({ ...criteria, page: criteria.page - 1 });

export const nextFindMyPostsResultPage = criteria =>
	getMyFeed({ ...criteria, page: criteria.page + 1 });

export const findPostByIdCompleted = post => ({
	type : actionTypes.POST_BY_ID_COMPLETED,
	post
})


export const postByIdClear = () => ({
	type: actionTypes.POST_BY_ID_CLEAR
})

export const findPostbyId = id => dispatch => {
	backend.postService.findPostbyId(id, post => dispatch(findPostByIdCompleted(post)))
}

export const deletear = (id, onSuccess) => dispatch => {
	backend.postService.deletear(id, onSuccess)
}

export const updatear = (id, titulo, descripcion, subjectId, academicYear, onSuccess, onErrors) => dispatch =>
	backend.postService.updatear(id, titulo, descripcion, subjectId, academicYear, onSuccess, onErrors);

const findAllUniversitiesCompleted = universities => ({
	type: actionTypes.FIND_ALL_UNIVERSITIES_COMPLETED,
	universities
});

export const findAllUniversities = () => (dispatch, getState) => {

//	const universities = selectors.getUniversities(getState());

//	if (!universities) {

		backend.postService.findAllUniversities(
			unis => dispatch(findAllUniversitiesCompleted(unis))
		);

//	}

}

export const followPost = postId => dispatch => {
	backend.postService.followPost(postId, _ => dispatch(findPostbyId(postId)))
}

export const unfollowPost = postId => dispatch => {
	backend.postService.unfollowPost(postId, _ => dispatch(findPostbyId(postId)))
}
