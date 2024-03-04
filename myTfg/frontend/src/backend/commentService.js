import {
  fetchConfig,
  appFetch,
} from "./appFetch";

export const postComentario = (postId, textoComentario, onSuccess, onErrors) => 
	appFetch(`/comments/uploadComment/${postId}`, fetchConfig('POST', {textoComentario}), onSuccess, onErrors);
	
export const uploadAnswer = (commentId, textoComentario, respuestaId, onSuccess, onErrors) => 
	appFetch(`/comments/uploadAnswer/${commentId}`, fetchConfig('POST', {textoComentario, respuestaId}), onSuccess, onErrors);

export const getBlockCommentsWithAnswersFromPost = ({postId, page}, onSuccess) => {
	
	let path = `/comments/commentsPost/${postId}?page=${page}`
	appFetch(path, fetchConfig('GET'), onSuccess);

}
    
export const deleteComment = (commentId, onSuccess, onErrors) =>
    appFetch(`/comments/deleteComment/${commentId}`, fetchConfig('DELETE'), onSuccess, onErrors);