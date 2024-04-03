import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const sendMessage = (userId, text, onSuccess, onErrors) => dispatch =>
    backend.messageService.sendMessage(userId, text, onSuccess, onErrors);

export const getMessagesCompleted = messages => ({
    type : actionTypes.GET_MESSAGES_COMPLETED,
    messages
})

export const getMessages = (userId) => dispatch =>
    backend.messageService.getMessages(userId, messages => dispatch(getMessagesCompleted(messages)));


export const clearMessages = commentsAndAnswers => ({
    type: actionTypes.CLEAR_MESSAGES
});