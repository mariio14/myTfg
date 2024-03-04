import {
    fetchConfig,
    appFetch,
} from "./appFetch";

export const valorar = (postId, rating, onSuccess, onErrors) =>
    appFetch(`/ratings/upload/${postId}`, fetchConfig('POST', {rating}), onSuccess, onErrors);

export const eliminar_rating = (postId, onSuccess, onErrors) =>
    appFetch(`/ratings/delete/${postId}`, fetchConfig('DELETE'), onSuccess, onErrors);