import request from '@/utils/request'

export function getInfo(token) {
  return request({
    url: '/api/getUserInfo',
    method: 'get',
    params: { token }
  })
}

export function getStaffInfo(token) {
  return request({
    url: '/api/getStaffUserInfo',
    method: 'get',
    params: { token }
  })
}
