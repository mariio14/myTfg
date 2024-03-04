import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
	commentsAndAnswers: []
};

const commentsAndAnswers = (state = initialState.commentsAndAnswers, action) => {
	switch(action.type){
		case actionTypes.GET_ALL_COMMENTS_AND_ANSWERS_COMPLETED:
			return action.commentsAndAnswers;
		case actionTypes.CLEAR_ALL_COMMENTS_AND_ANSWERS:
			return initialState.commentsAndAnswers;	
		default:
			return state;
	}
	
}

const reducer = combineReducers({
	commentsAndAnswers
});

export default reducer;

