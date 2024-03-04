import {
  fetchConfig,
  appFetch,
} from "./appFetch";


export const findNotificationsByUser = (onSuccess, onErrors) =>
    appFetch(`/notifications/findAll`, fetchConfig('GET'), onSuccess, onErrors);
    
export const findNotReadNotifications = (onSuccess, onErrors) =>
    appFetch(`/notifications/notRead`, fetchConfig('GET'), onSuccess, onErrors);    

export const deleteNotification = (notificationId, onSuccess, onErrors) =>
    appFetch(`/notifications/delete/${notificationId}`, fetchConfig('DELETE'), onSuccess, onErrors);