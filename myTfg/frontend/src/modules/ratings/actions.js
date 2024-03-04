import backend from '../../backend';

export const valorar = (postId, rating) => dispatch => {
    backend.ratingService.valorar(postId, rating)
}

export const deleteRating = (id, postId) => dispatch => {
    backend.ratingService.eliminar_rating(id)
}
