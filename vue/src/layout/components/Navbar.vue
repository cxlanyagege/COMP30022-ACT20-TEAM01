 <!-- 自己写的 -->

<template>
  <div class="navbar">
    <breadcrumb class="breadcrumb-container" />
    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar + '?imageView2/1/w/80/h/80'" class="user-avatar" />
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <table>
            <tr>
              <td>
                <button class="custom-button" @click="showProfileDialog = true">
                  Student Profile
                </button>
              </td>
            </tr>
            <tr>
              <td>
                <button class="custom-button" @click="showSettingDialog = true">
                  Setting
                </button>
              </td>
            </tr>
          </table>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog
      v-model="showProfileDialog"
      title="Student Profile"
      :visible.sync="showProfileDialog"
      width="50%"
      center
    >
      <student-profile :student-info="studentInfo" />
    </el-dialog>
    <el-dialog
      v-model="showSettingDialog"
      title="Setting"
      :visible.sync="showSettingDialog"
      width="60%"
      center
    >
      <Setting/>
    </el-dialog>
  </div>
</template>



<script>
import { mapGetters } from "vuex";
import Breadcrumb from "@/components/Breadcrumb";
import StudentProfile from "@/components/form/StudentProfile.vue";
import Setting from "@/components/form/setting.vue";

export default {
  components: {
    Breadcrumb,
    StudentProfile,
    Setting,
  },

  data() {
    return {
      showProfileDialog: false,
      showSettingDialog: false,
      studentInfo: {
        name: "John Doe",
        id: "1266543",
        email: "Johndoe@student.unimelb.edu.au",
        aap: "Yes",
      },
    };
  },

  computed: {
    ...mapGetters(["sidebar", "avatar"]),
  },

  methods: {
    toggleSideBar() {
      this.$store.dispatch("app/toggleSideBar");
    },
    openProfileDialog() {
      this.showProfileDialog = true;
    },
  },
};
</script>


<style lang="scss" scoped>
.custom-button {
  background-color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 14px;
  width: 145px;
  text-align: left;
  color: #5a5f64;
  &:hover {
    background-color: rgb(225, 239, 248);
  }
}

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  margin-top: 25px;

  .breadcrumb-container {
    float: left;
    margin-left: 35px;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

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
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
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