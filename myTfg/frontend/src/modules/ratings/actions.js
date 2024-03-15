import backend from '../../backend';
import post from "../post";

export const valorar = (postId, rating) => dispatch => {
    backend.ratingService.valorar(postId, rating, rating => dispatch(post.actions.findPostbyId(postId)))
}

export const deleteRating = (id, postId) => dispatch => {
    backend.ratingService.eliminar_rating(id, rating => dispatch(post.actions.findPostbyId(postId)))
}
