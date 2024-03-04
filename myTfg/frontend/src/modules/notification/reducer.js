import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
	notReadNotifications: 0,
	notificationsByUser: []
};

const notificationsByUser = (state = initialState.notificationsByUser, action) => {
	switch(action.type){
		case actionTypes.NOTIFICATIONS_BY_USER_COMPLETED:
			return action.notificationsByUser;
		case actionTypes.NOTIFICATIONS_BY_USER_CLEAR:
			return initialState.notificationsByUser;	
		default:
			return state;
	}	
}

const notReadNotifications = (state = initialState.notReadNotifications, action) => {
	switch(action.type){
		case actionTypes.NOT_READ_NOTIFICATIONS_COMPLETED:
			return action.notReadNotifications;	
		default:
			return state;
	}
}

const reducer = combineReducers({
	notReadNotifications,
	notificationsByUser
});

export default reducer;

