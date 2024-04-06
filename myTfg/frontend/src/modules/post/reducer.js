import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
	postId: null,
	feed: [],
	post: null,
	universities: null,
	subjects: null,
	keys: null,
	values: null
};

const feed = (state = initialState.feed, action) => {

	switch (action.type) {
		case actionTypes.GET_FEED_COMPLETED:
			return action.feed;
		case actionTypes.CLEAR_FEED_SEARCH:
			return initialState.feed;
		default:
			return state;

	}
}

const post = (state = initialState.post, action) => {

	switch (action.type) {

		case actionTypes.POST_BY_ID_COMPLETED:
			return action.post;

		case actionTypes.POST_BY_ID_CLEAR:
			return initialState.post;

		default:
			return state;
	}
}

const universities = (state = initialState.universities, action) => {

	switch (action.type) {

		case actionTypes.FIND_ALL_UNIVERSITIES_COMPLETED:
			return action.universities;

		default:
			return state;

	}

}

const subjects = (state = initialState.subjects, action) => {

	switch (action.type) {

		case actionTypes.FIND_SUBJECTS_COMPLETED:
			return action.subjects;

		default:
			return state;

	}

}

const keys = (state = initialState.keys, action) => {

	switch (action.type) {

		case actionTypes.FIND_ALL_KEYS_COMPLETED:
			return action.keys;

		default:
			return state;

	}

}

const values = (state = initialState.values, action) => {

	switch (action.type) {

		case actionTypes.FIND_VALUES_COMPLETED:
			return action.values;

		default:
			return state;

	}

}

const reducer = combineReducers({
	feed,
	post,
	universities,
	subjects,
	keys,
	values
});

export default reducer;