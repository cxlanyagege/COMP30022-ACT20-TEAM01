import request from '@/utils/request'

export function getInfo(token) {
  return request({
    url: '/api/getUserInfo',
    method: 'get',
    params: { token }
  })
}
