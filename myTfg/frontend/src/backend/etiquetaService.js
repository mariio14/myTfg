import {
    fetchConfig,
    appFetch,
} from "./appFetch";

export const findAllKeys = (onSuccess) =>
    appFetch('/etiquetas/allKey', fetchConfig('GET'), onSuccess);

export const findValuesFromKey = (key,onSuccess) =>
    appFetch(`/etiquetas/values?key=${key}`, fetchConfig('GET'), onSuccess);