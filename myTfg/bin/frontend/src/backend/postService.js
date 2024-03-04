import {
  fetchConfig,
  appFetch,
} from "./appFetch";



export const postear = (imagenes, descripcion, titulo, categoria, url, precio, onSuccess, onErrors) =>
appFetch(`/posts/upload`, fetchConfig('POST', {imagenes, descripcion, titulo, categoria, url, precio}), onSuccess, onErrors);

export const obtenerFeed = (onSuccess, onErrors) => 
appFetch ('/posts/feed', fetchConfig('GET'), onSuccess, onErrors);