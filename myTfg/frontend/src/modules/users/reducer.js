import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    user: null,
    userProfile: null
};

const user = (state = initialState.user, action) => {

    switch (action.type) {

        case actionTypes.SIGN_UP_COMPLETED:
            return action.authenticatedUser.user;

        case actionTypes.LOGIN_COMPLETED:
            return action.authenticatedUser.user;

        case actionTypes.LOGOUT:
            return initialState.user;

        case actionTypes.UPDATE_PROFILE_COMPLETED:
            return action.user;

        default:
            return state;

    }

}

const userProfile = (state = initialState.userProfile, action) => {

    switch (action.type){
        case actionTypes.USER_PROFILE_COMPLETED:
            return action.userProfile;

        default:
            return state
    }
}

const reducer = combineReducers({
    user,
    userProfile
});

export default reducer;


