import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import subject from './modules/subject'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    subject
  },
  getters
})

export default store
