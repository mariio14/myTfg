import {
    fetchConfig,
    appFetch,
} from "./appFetch";

export const sendMessage = (userId, text, onSuccess, onErrors) =>
    appFetch(`/messages/send/${userId}`, fetchConfig('POST', text), onSuccess, onErrors);

export const getMessages = (userId, onSuccess) =>
    appFetch(`/messages/${userId}`, fetchConfig('GET'), onSuccess);