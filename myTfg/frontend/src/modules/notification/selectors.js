const getModuleState = state => state.notification;

export const getNotReadNotifications = state => 
	getModuleState(state).notReadNotifications;

export const getNotificationsByUser = state => 
	getModuleState(state).notificationsByUser;