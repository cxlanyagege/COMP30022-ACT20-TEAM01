/**
 * JS Name: api/subject.js
 * Description: Subject information request api
 * 
 * Author: He Shen
 * Date: 2023/9/23
 */

import request from '@/utils/request'

export function getSubjectInfo(token) {
  return request({
    url: '/api/getSubjectInfo',
    method: 'get',
    params: { token }
  })
}
