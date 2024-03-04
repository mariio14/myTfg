import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as Post} from "./components/Post.js";
export {default as PostCompleto} from "./components/PostCompleto.js";
export {default as Feed} from "./components/Feed";
export {default as Avatar} from "./components/Avatar";

// eslint-disable-next-line
export default {actions, actionTypes, reducer, selectors};