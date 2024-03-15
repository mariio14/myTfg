import {
  fetchConfig,
  appFetch,
} from "./appFetch";



export const postear = (descripcion, titulo, subjectId, academicYear, onSuccess, onErrors) =>
appFetch(`/posts/upload`, fetchConfig('POST', {descripcion, titulo, subjectId, academicYear}), onSuccess, onErrors);


export const obtenerFeed = ({keywords, minYear, maxYear, order, page},
                             onSuccess) => {

  let path = `/posts/feed?page=${page}`;

  path += keywords.length > 0 ? `&keywords=${encodeURIComponent(keywords)}` : "";
  path += minYear ? `&minPrice=${minYear}` : "";
  path += maxYear ? `&maxPrice=${maxYear}` : "";
  path += order ? `&order=${order}` : "";


  appFetch(path, fetchConfig('GET'), onSuccess);

}

export const obtenerMyFeed = ({page},
                            onSuccess) => {

  let path = `/posts/myFeed?page=${page}`;

  appFetch(path, fetchConfig('GET'), onSuccess);

}

export const findPostbyId = (id, onSuccess, onErrors) =>
    appFetch(`/posts/${id}`, fetchConfig('GET'), onSuccess, onErrors)
    
export const deletear = (id, onSuccess, onErrors) =>
appFetch(`/posts/delete/${id}`, fetchConfig('POST'), onSuccess, onErrors);

export const updatear = (id, titulo, descripcion, subjectId, academicYear, onSuccess, onErrors) =>
appFetch(`/posts/update/${id}`, fetchConfig('PUT', {titulo, descripcion, subjectId, academicYear}), onSuccess, onErrors);

export const findAllUniversities = (onSuccess) =>
    appFetch('/posts/universities', fetchConfig('GET'), onSuccess);

export const findSubjects = (id,onSuccess) =>
    appFetch(`/posts/subjects/${id}`, fetchConfig('GET'), onSuccess);
    
export const followPost = (id, onSuccess, onErrors) =>
	appFetch(`/posts/follow/${id}`, fetchConfig('POST'), onSuccess, onErrors);
	
export const unfollowPost = (id, onSuccess, onErrors) =>
	appFetch(`/posts/unfollow/${id}`, fetchConfig('DELETE'), onSuccess, onErrors);	
	