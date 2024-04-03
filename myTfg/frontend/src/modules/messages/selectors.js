const getModuleState = state => state.messages;

export const getMessages = state =>
    getModuleState(state).messages;