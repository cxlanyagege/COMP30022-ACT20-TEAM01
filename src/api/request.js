// file written by Dennis, used to call api to make interaction with the
// backend
import { deleteAction, getAction, postAction, putAction } from '@/utils/communication'

export const addRequest = (param) => postAction('/api/saveRequest', param)

export const getRequests = (studentId, param) => getAction('/api/getRequests/' + studentId, param)

export const getRequest = (id) => getAction('/api/getRequestDetail/' + id, null)

export const deleteRequest = (id) => deleteAction('/api/deleteRequest/' + id, null)

export const changeRequest = (id, param) => putAction("/api/changeRequest/" + id, param)

export const getSubjectDetail = (subjectId) => getAction("/api/getSubjectDetail/" + subjectId, null)

export const updateStudentPreference = (studentId, param) => putAction("/api/changeStudentPreference/" + studentId, param)

export const getStudentDetail = (studentId) => getAction("/api/getStudentInfo/" + studentId, null);
