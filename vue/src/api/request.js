import { deleteAction, getAction, postAction, putAction } from '@/utils/communication';

export const addRequest = (param) => postAction("/api/saveRequest", param)

export const getRequests = (studentId, param) => getAction("/api/getRequests/" + studentId, param)

export const getRequest = (id) => getAction("/api/getRequestDetail/" + id, null)

export const deleteRequest = (id) => deleteAction("/api/deleteRequest/" + id, null)

export const changeRequest = (id, param) => putAction("/api/changeRequest/" + id, param)