// file written by Dennis, used to call api to make connection with
// the backend
import { putAction } from '@/utils/communication'

export const updateStudentPreference = (studentId, param) => putAction('/api/changeStudentPreference/' + studentId, param)

// export const getSubjectInfo = (subjectId) => getAction("/api/getSubjectDetail/" + subjectId, null)
