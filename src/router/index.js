/* The following code is the framework that comes with the scaffolding, modified and
edited by Yawen Luo to fit the student platform. The code is the router in the paltfrom */

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'
import StaffLayout from '@/layout/staffindex.vue'
import TutorLayout from '@/layout/tutorindex.vue'

// MODIFIED BY YAWEN LUO
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/stafflogin',
    component: () => import('@/views/login/staffindex'),
    hidden: true
  },

  {
    path: '/tutorlogin',
    component: () => import('@/views/login/tutorindex'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/staff',
    component: StaffLayout,
    redirect: '/staff/staffdashboard',
    children: [{
      path: 'staffdashboard',
      name: 'StaffDashboard',
      component: () => import('@/views/dashboard/staffindex'),
      meta: { title: 'Staff Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/staff/PER-Request',
    component: StaffLayout,
    children: [
      {
        path: 'index',
        name: 'Request Permission',
        component: () => import('@/views/requestAuthority/index'),
        meta: { title: 'Request Permission', icon: 'el-icon-s-operation' }
      }
    ]
  },

  {
    path: '/staff/PER-Tutor',
    component: StaffLayout,
    children: [
      {
        path: 'index',
        name: 'Tutor Authority',
        component: () => import('@/views/tutorAuthority/index'),
        meta: { title: 'Tutor Authority', icon: 'el-icon-s-help' }
      }
    ]
  },

  {
    path: '/tutor',
    component: TutorLayout,
    redirect: '/tutor/tutordashboard',
    children: [{
      path: 'tutordashboard',
      name: 'TutorDashboard',
      component: () => import('@/views/dashboard/tutorindex'),
      meta: { title: 'Tutor Dashboard', icon: 'dashboard' }
    }]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher
}

export default router
