import request from '@/utils/request'

export function getInfo(token) {
  return request({
    url: '/api/getUserInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/logout',
    method: 'post'
  })
}
