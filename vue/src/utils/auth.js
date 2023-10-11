import Cookies from 'js-cookie'

const TokenKey = 'stu_request_hub'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token, {
    sameSite: 'None',
    secure: true
  })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}