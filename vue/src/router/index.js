/* // The following code is the framework that comes with the scaffolding, modified and 
edited by Yawen Luo to fit the student platform. The code is the router in the paltfrom */

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

// MODIFIED BY YAWEN LUO
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
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
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/history',
    children: [
      {
        path: 'history',
        name: 'History',
        component: () => import('@/views/history/index'),
        meta: { title: 'History', icon: 'tree' }
      }]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/staff_dashboard',
    children: [{
      path: 'staff_dashboard',
      name: 'Staff_dashboard',
      component: () => import('@/views/staff_dashboard/index'),
      meta: { title: 'Staff dashboard', icon: 'dashboard' }
    }]
  },
  {
    path: '/',
    component: Layout,
    redirect: '/authority',
    children: [
      {
        path: 'authority',
        name: 'Authority',
        component: () => import('@/views/authority/index'),
        meta: { title: 'Authority', icon: 'el-icon-s-help' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
const router = createRouter()
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
