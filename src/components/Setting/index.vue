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
          <el-switch v-model="scope.row.isEnabled"></el-switch>
        </template>
      </el-table-column>
    </el-table>
    <br>
    <h3>Time Preferences</h3>
    <el-time-picker
      is-range
      v-model="notificationTime"
      range-separator="to"
      start-placeholder="Start Time"
      end-placeholder="Finish Time"
      placeholder="Select Time Range"
    >
    </el-time-picker>
  </div>
</template>

<script>
export default {
  data() {
    return {
      notificationSettings: [
        {
          notificationType: "When the request is successfully create",
          isEnabled: true,
        },
        {
          notificationType: "When the request has been processed",
          isEnabled: true,
        },
        { notificationType: "When the request is deleted", 
          isEnabled: true,
        },
        {
          notificationType: "When there is a new request",
          isEnabled: true,
        },
        {
          notificationType: "When other tutor has flagged an request",
          isEnabled: true,
        },
      ],
      notificationTime: ["08:00", "18:00"]
    };
  },
};
</script>

<style>
.settings-page {
  max-width: 600px;
  margin: 0 auto;
}
</style>