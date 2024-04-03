import {combineReducers} from 'redux';

import users from '../modules/users';
import ratings from '../modules/ratings';
import comments from '../modules/comments';
import notification from '../modules/notification';
import post from '../modules/post';
import messages from "../modules/messages";

const rootReducer = combineReducers({
    users: users.reducer,
    ratings: ratings.reducer,
    comments: comments.reducer,
    notification: notification.reducer,
    post: post.reducer,
    messages: messages.reducer
});

export default rootReducer;