const getModuleState = state => state.comments;

export const getCommentsAndAnswersFromPost = state => 
	getModuleState(state).commentsAndAnswers;
