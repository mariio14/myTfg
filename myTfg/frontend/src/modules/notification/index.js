import * as actions from './actions';
import * as actionTypes from './actionTypes';
import reducer from './reducer';
import * as selectors from './selectors';

export { default as Notifications } from "./components/Notifications.js";

const notification = { actions, actionTypes, reducer, selectors };
export default notification;