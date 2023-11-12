<!-- The component code was written by Yawen Luo, Dennis Wang was modified the 
     front-end and back-end interaction method code at a later stage. The following code is setting
     compoent. -->

<template>
  <div class="settings-page">
    <h3>Notification Settings</h3>
    <el-table :data="notificationSettings" style="width: 100%">
      <el-table-column
        prop="notificationType"
        label="Email Notification Type"
      ></el-table-column>
      <el-table-column prop="isEnabled" label="Enable" width="120">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isEnabled" @change="updateSetting()"></el-switch>
        </template>
      </el-table-column>
    </el-table>
    <br />
    <h3>Time Preferences</h3>
    <span>Set the time you want to receive email</span>
    <br />
    <el-time-picker
      is-range
      v-model="notificationTime"
      range-separator="to"
      start-placeholder="Start Time"
      end-placeholder="Finish Time"
      placeholder="Select Time Range"
      style="margin-top: 10px"
    >
    </el-time-picker>
  </div>
</template>

<script>
import { updateStudentPreference, getStudentDetail } from "@/api/request";

export default {
  data() {
    return {
      notificationSettings: [
        {
          notificationType: "When the request is successfully create",
          isEnabled: undefined,
        },
        {
          notificationType: "When the request has been processed",
          isEnabled: undefined,
        },
        { notificationType: "When the request is deleted", isEnabled: undefined },
      ],
      notificationTime: ["08:00", "18:00"],
    };
  },

  // written by Dennis, initialize the setting for each student
  created() {
    getStudentDetail(1266288).then((res) => {
      console.log(res.data)
      this.notificationSettings[0].isEnabled = res.data.data.createRequest;
      this.notificationSettings[1].isEnabled = res.data.data.processRequest;
      this.notificationSettings[2].isEnabled = res.data.data.deleteRequest;
    })
  },

  // method written by Dennis, used to update the user preference
  // on receiving the emails
  methods: {
    updateSetting() {
      let param = {
        createRequest: this.notificationSettings[0].isEnabled,
        processRequest: this.notificationSettings[1].isEnabled,
        deleteRequest: this.notificationSettings[2].isEnabled
      }
      // can change the id to the id in student profile
      updateStudentPreference(1266288, param).then((res) => {
        console.log(res.data);
      })
    }
  }
};
</script>

<style>
.settings-page {
  max-width: 600px;
  margin: 0 auto;
}
</style>
