 <!-- The following code is the framework that comes with the scaffolding, modified and
      edited by Yawen Luo to fit the student platform. The code describes Breadcrumb
      component-->

<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item, index) in levelList" :key="item.path">
        <span
          v-if="item.redirect === 'noRedirect' || index == levelList.length - 1"
          class="no-redirect"
        >{{ item.meta.title }}</span>
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
import pathToRegexp from 'path-to-regexp'

export default {
  data() {
    return {
      levelList: null
    }
  },

  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },

  created() {
    this.getBreadcrumb()
  },

  // MODIFIED BY YAWEN LUO
  methods: {
    // MODIFIED BY YAWEN LUO
    getBreadcrumb() {
      let matched = this.$route.matched.filter(
        (item) => item.meta && item.meta.title
      )
      
      this.levelList = matched.filter(
        (item) => item.meta && item.meta.title && item.meta.breadcrumb !== false
      );
    },
    isDashboard(route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return (
        name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase()
      )
    },
    pathCompile(path) {
      const { params } = this.$route
      var toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink(item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    }
  }
}
</script>

<style lang="scss" scoped>
// MODIFIED BY YAWEN LUO
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
