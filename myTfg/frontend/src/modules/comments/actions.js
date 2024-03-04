import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const getCommentsAndAnswersCompleted = commentsAndAnswers => ({
	type: actionTypes.GET_ALL_COMMENTS_AND_ANSWERS_COMPLETED,
	commentsAndAnswers
});

export const clearCommentsAndAnswers = commentsAndAnswers => ({
	type: actionTypes.CLEAR_ALL_COMMENTS_AND_ANSWERS,	
});

export const uploadComment = (postId, textoComentario, onSuccess, onErrors) => dispatch => {
	backend.commentService.postComentario(postId, textoComentario, onSuccess, onErrors);
};

export const uploadAnswer = (commentId, textoComentario, respuestaId, onSuccess, onErrors) => dispatch => {
	backend.commentService.uploadAnswer(commentId, textoComentario, respuestaId, onSuccess, onErrors)
};

export const deleteComment = (commentId, postId, onSuccess) => dispatch => {
	backend.commentService.deleteComment(commentId, comment => dispatch(getPostCommentsAndAnswersBlock({postId,  page: 0 })))
};

export const getPostCommentsAndAnswersBlock = criteria => dispatch => {
	
	dispatch(clearCommentsAndAnswers());
	backend.commentService.getBlockCommentsWithAnswersFromPost(criteria,
		result => dispatch(getCommentsAndAnswersCompleted({criteria, result})))
	
};

export const previousPostCommentsResultPage = criteria =>
	getPostCommentsAndAnswersBlock({...criteria, page: criteria.page - 1});

export const nextPostCommentsResultPage = criteria =>
	getPostCommentsAndAnswersBlock({...criteria, page: criteria.page + 1});




