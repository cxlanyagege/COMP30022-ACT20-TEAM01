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
          <el-switch v-model="scope.row.isEnabled" @change="updateSetting()" />
        </template>
      </el-table-column>
    </el-table>
    <br>
    <h3>Time Preferences</h3>
    <el-time-picker
      v-model="notificationTime"
      is-range
      range-separator="to"
      start-placeholder="Start Time"
      end-placeholder="Finish Time"
      placeholder="Select Time Range"
    />
  </div>
</template>

<script>
import { getStaffDetail, updateStaffPreference } from '@/api/api'

export default {
  data() {
    return {
      notificationSettings: [
        {
          notificationType: 'When there is a new request',
          isEnabled: undefined
        }
      ],
      notificationTime: ['08:00', '18:00']
    }
  },
  // written by Dennis, initialize the setting for each staff
  created() {
    getStaffDetail(123455).then((res) => {
      console.log(res.data)
      this.notificationSettings[0].isEnabled = res.data.data.newRequest
    })
  },
  methods: {
    updateSetting() {
      const param = {
        newRequest: this.notificationSettings[0].isEnabled
      }
      // can change the id to the id in staff profile
      updateStaffPreference(123455, param).then((res) => {
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
