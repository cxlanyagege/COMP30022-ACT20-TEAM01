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
          <el-switch v-model="scope.row.isEnabled" @change="updateSetting()"></el-switch>
        </template>
      </el-table-column>
    </el-table>
    <br>
    <h3>Time Preferences</h3>
    <span>Set the time you want to receive email</span>
    <br />
    <el-time-picker
      v-model="notificationTime"
      is-range
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
import { updateStaffPreference, getStaffDetail } from "@/api/api";
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
        { notificationType: 'When the request is deleted',
          isEnabled: true
        },
        {
          notificationType: "When there is a new request",
          isEnabled: undefined,
        },
        {
          notificationType: "When other tutor has flagged a request",
          isEnabled: undefined,
        },
      ],
      notificationTime: ["08:00", "18:00"],
    };
  },

  // written by Dennis, initialize the setting for each staff
  created() {
    getStaffDetail(123456).then((res) => {
      console.log(res.data)
      this.notificationSettings[0].isEnabled = res.data.data.newRequest;
      this.notificationSettings[1].isEnabled = res.data.data.flaggedRequest;
    })
  },

  // method written by Dennis, used to update the user preference
  // on receiving the emails
  methods: {
    updateSetting() {
      let param = {
        newRequest: this.notificationSettings[0].isEnabled,
        flaggedRequest: this.notificationSettings[1].isEnabled,
      }
      // can change the id to the id in staff profile
      updateStaffPreference(123456, param).then((res) => {
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
