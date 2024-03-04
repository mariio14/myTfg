import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    postId: null,
    feed: []
};

const feed = (state = initialState.feed, action) => {
	
	switch(action.type){
		case actionTypes.GET_FEED_COMPLETED:
			return action.feed;
			
		default:
			return state;	
		
	}	
}

const reducer = combineReducers({
    feed
});

export default reducer;