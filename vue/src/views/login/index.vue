<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">StuRequestHub is launching...</h3>
      </div>
      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="refreshPage">Not loading? Click here to refresh the page</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  created() {
    // Get jwt from url
    const jwt = this.$route.query.jwt
    if (jwt) {
      this.loading = true
      // Use store action to login
      this.$store.dispatch('user/login', jwt).then(() => {
        // Success, go to dashboard
        this.$router.push({ path: this.redirect || '/' })
        this.loading = false
      }).catch(() => {
        // Failed
        this.loading = false
        console.log('JWT is invalid')
      })
    }
  },
  methods: {
    refreshPage() {
      location.reload()
    }
  }
}
</script>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;
  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
}
</style>
