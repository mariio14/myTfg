import * as actionTypes from './actionTypes';
import backend from '../../backend';


export const getNotificationsByUserCompleted = notificationsByUser => ({
	type: actionTypes.NOTIFICATIONS_BY_USER_COMPLETED,
	notificationsByUser
});

export const getNotReadNotificationsCompleted = notReadNotifications => ({
	type: actionTypes.NOT_READ_NOTIFICATIONS_COMPLETED,
	notReadNotifications
});

export const getNotificationsByUserClear = () => ({
	type: actionTypes.NOTIFICATIONS_BY_USER_CLEAR
})


export const deleteNotification = (notificationId, onSuccess) => dispatch => {
	backend.notificationService.deleteNotification(notificationId, onSuccess)
};

export const findNotificationsByUser = () => dispatch => {
	backend.notificationService.findNotificationsByUser(notifications =>
	 dispatch(getNotificationsByUserCompleted(notifications)))
};


export const getNotReadNotifications = () => dispatch => {
	backend.notificationService.findNotReadNotifications(number =>
	 dispatch(getNotReadNotificationsCompleted(number)))
};
