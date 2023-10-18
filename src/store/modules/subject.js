/**
 * JS Name: store/modules/subject.js
 * Description: Subject information storing
 * 
 * Author: He Shen
 * Date: 2023/9/23
 */

import { getSubjectInfo } from '@/api/subject'
import { getToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    subjectId: null,
    subjectName: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_SUBJECTID: (state, subjectId) => {
    state.subjectId = subjectId
  },
  SET_SUBJECTNAME: (state, subjectName) => {
    state.subjectName = subjectName
  }
}

const actions = {
  // get subject info
  getSubjectInfo({ commit, state, rootState }) {
    return new Promise((resolve, reject) => {
      if (!state.token) {
        commit('SET_TOKEN', rootState.user.token)
      }

      getSubjectInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { subjectId, subjectName } = data

        commit('SET_SUBJECTID', subjectId)
        commit('SET_SUBJECTNAME', subjectName)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      resetRouter()
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
