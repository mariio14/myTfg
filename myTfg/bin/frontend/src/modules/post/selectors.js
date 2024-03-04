const getModuleState = state => state.post;

export const getUltimoPostId = state =>
    getModuleState(state).postId;    

export const getFeed = state =>
    getModuleState(state).feed;    