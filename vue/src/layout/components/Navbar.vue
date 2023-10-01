<!-- The following code is written by Yawen Luo, the code is discribe the Navbar element-->

<template>
  <div class="navbar">
    <breadcrumb class="breadcrumb-container" />
    <div class="right-menu">
      <div class="avatar-container" @click="showStudentProfileDialog">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" :size="45" class="vertical-center" />
      </div>
    </div>
    <!-- Add student information modal box -->
    <el-dialog
      v-model="showProfileDialog"
      title="Student Profile"
      :visible.sync="showProfileDialog"
      width="50%"
      center
    >
      <!-- Referencing the StudentProfile component and passing student information -->
      <student-profile :student-info="studentInfo" />
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import StudentProfile from '@/components/form/StudentProfile.vue'

export default {
  components: {
    Breadcrumb,
    StudentProfile
  },
  data() {
    return {
      showProfileDialog: false,
      studentInfo: {
        name: this.$store.state.user.name, 
        id: this.$store.state.user.id,
        email: this.$store.state.user.email,
        aap: 'Yes' 
      }
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
    showStudentProfileDialog() {
      console.log('Button clicked') 
      this.showProfileDialog = true
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
  margin-top: 25px;

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
      margin-top: 3px;
      cursor: pointer;
      display: flex;
      align-items: center;

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
