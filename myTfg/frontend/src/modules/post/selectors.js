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

export const getRefreshFeed = state =>
    getModuleState(state).feedRefresh;

export const getCategoryName = (categories, id) => {

    if (!categories) {
        return '';
    }

    const category = categories.find(category => category.id === id);

    if (!category) {
        return '';
    }

    return category.name;

}