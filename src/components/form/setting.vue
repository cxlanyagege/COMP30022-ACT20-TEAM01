<!-- The component code was written by Yawen Luo. The following code is used
     to build the component request table elements. -->

<template>
  <div class="settings-page">
    <h3>Notification Settings</h3>
    <el-table :data="notificationSettings" style="width: 100%">
      <el-table-column
        prop="notificationType"
        label="Email Notification Type"
      />
      <el-table-column prop="isEnabled" label="Enable" width="120">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isEnabled" @change="updateSetting" />
        </template>
      </el-table-column>
    </el-table>
    <br>
    <h3>Time Preferences</h3>
    <span>Set the time you want to receive email</span>
    <br>
    <el-time-picker
      v-model="notificationTime"
      is-range
      range-separator="to"
      start-placeholder="Start Time"
      end-placeholder="Finish Time"
      placeholder="Select Time Range"
      style="margin-top: 10px"
    />
  </div>
</template>

<script>
import { updateStudentPreference } from '@/api/entity'

export default {
  data() {
    return {
      notificationSettings: [
        {
          notificationType: 'When the request is successfully create',
          isEnabled: true
        },
        {
          notificationType: 'When the request has been processed',
          isEnabled: true
        },
        { notificationType: 'When the request is deleted', isEnabled: true }
      ],
      notificationTime: ['08:00', '18:00']
    }
  },

  // method written by Dennis, used to update the user preference
  // on receiving the emails
  methods: {
    updateSetting() {
      console.log(this.notificationSettings[0].isEnabled)
      const param = {
        createRequest: this.notificationSettings[0].isEnabled,
        processRequest: this.notificationSettings[1].isEnabled,
        deleteRequest: this.notificationSettings[2].isEnabled
      }
      // can change the id to the id in student profile
      updateStudentPreference(1266288, param).then((res) => {
        console.log(res.data)
      })
    }
  }
}
</script>

<style>
.settings-page {
  max-width: 600px;
  margin: 0 auto;
}
</style>
