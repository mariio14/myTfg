const getModuleState = state => state.post;

export const getPost = state => getModuleState(state).post;

export const getUltimoPostId = state =>
    getModuleState(state).postId;    

export const getFeed = state => {
    const moduleState = getModuleState(state);
    return moduleState ? moduleState.feed : null;
};

export const getUniversities = state => {
    const moduleState = getModuleState(state);
    return moduleState ? moduleState.universities : null;
};

export const getSubjects = state => {
    const moduleState = getModuleState(state);
    return moduleState ? moduleState.subjects : null;
};

export const getKeys = state => {
    const moduleState = getModuleState(state);
    return moduleState ? moduleState.keys : null;
};

export const getValues = state => {
    const moduleState = getModuleState(state);
    return moduleState ? moduleState.values : null;
};
