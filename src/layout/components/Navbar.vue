<template>
  <div class="navbar">

    <breadcrumb class="breadcrumb-container" />

    <el-button plain @click="openNotificationDialog" class="notification-button" style="padding: 8px; margin-top: 6px; margin-bottom: 6px; margin-left: 1060px">
      <i class="el-icon-bell" style="font-size: 20px; color: #304156;"></i>
    </el-button>

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <el-dropdown-item @click.native="openSettingDialog">Setting</el-dropdown-item>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">Log Out</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      title="Notification Settings"
      :visible.sync="showSettingDialog"
      width="50%"
    >
      <setting></setting>  <!-- 在弹窗中显示 Setting 组件 -->
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import Setting from '@/components/Setting'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    Setting
  },
  data() {
    return {
      showSettingDialog: false
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    openSettingDialog() {
      this.showSettingDialog = true
    },
    openNotificationDialog() {
      // 处理打开通知对话框的逻辑
      // 可以在这里弹出通知设置对话框或执行其他操作
    }
  }
}
</script>

<style lang="scss" scoped>

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .breadcrumb-container {
    float: left;
    margin-left: 35px;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;
    margin-right: 30px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
