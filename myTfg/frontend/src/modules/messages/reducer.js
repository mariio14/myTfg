import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    messages: []
};

const messages = (state = initialState.messages, action) => {
    switch(action.type){
        case actionTypes.GET_MESSAGES_COMPLETED:
            return action.messages;
        case actionTypes.CLEAR_MESSAGES:
            return initialState.messages;
        default:
            return state;
    }
}

const reducer = combineReducers({
    messages
});

export default reducer;