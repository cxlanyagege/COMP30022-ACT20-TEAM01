// file written by Dennis, used to call api to make interaction with the
// backend
import { getAction, putAction } from '@/api/communication'

export const getSubjectRequests = (subjectId) => getAction('/api/getSubjectRequests/' + subjectId, null)

export const getSubjectDetail = (subjectId) => getAction('/api/getSubjectDetail/' + subjectId, null)

export const updateSubjectApprovedRequests = (subjectId, param) => putAction('/api/updateApprovedRequests/' + subjectId, param)

export const updateStaffPreference = (staffId, param) => putAction('/api/changeStaffPreference/' + staffId, param)

export const getStaffDetail = (staffId) => getAction('/api/getStaffDetail/' + staffId, null)

export const getStudentDetail = (studentId, param) => getAction('/api/getStudentInfo/' + studentId, param)

export const updateRequest = (requestId, param) => putAction('/api/updateRequest/' + requestId, param)

export const getStaffRoleInfo = (subjectId) => getAction('/api/getStaffRoleInfo/' + subjectId, null)

export const updateStaffAuthority = (staffId, param) => putAction('/api/updateStaffAuthority/' + staffId, param)
