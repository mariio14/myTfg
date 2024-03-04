import {combineReducers} from 'redux';

import users from '../modules/users';
import post from '../modules/post';

const rootReducer = combineReducers({
    users: users.reducer,
    post: post.reducer
});

export default rootReducer;