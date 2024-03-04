import * as actionTypes from './actionTypes';
import backend from '../../backend';


export const postear = (imagenes, titulo, descripcion , categoria, url, precio, onSuccess, onErrors) => dispatch =>
	backend.postService.postear(imagenes, descripcion, titulo, categoria,  url, precio,
	onSuccess()
,onErrors());



const getFeedCompleted =  (feed) => ({
    type: actionTypes.GET_FEED_COMPLETED,
    feed
});


export const getFeed = () => dispatch => {

    backend.postService.obtenerFeed(
		feed => dispatch(getFeedCompleted(feed))
	)
}


