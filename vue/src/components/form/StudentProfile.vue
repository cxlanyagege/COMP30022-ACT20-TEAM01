<template>
  <div class="student-profile">
    <h2>Student Profile</h2>
    <div class="profile-details">
      <div class="profile-item">
        <label>Name:</label>
        <span>{{ studentInfo.name }}</span>
      </div>
      <div class="profile-item">
        <label>Age:</label>
        <span>{{ studentInfo.age }}</span>
      </div>
      <div class="profile-item">
        <label>Grade:</label>
        <span>{{ studentInfo.grade }}</span>
      </div>
      <!-- 添加更多学生信息字段 -->
    </div>
  </div>
</template>

<script>
export default {
  props: {
    studentInfo: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
.student-profile {
  padding: 20px;
}

.profile-details {
  margin-top: 10px;
}

.profile-item {
  margin-bottom: 10px;
}

.label {
  font-weight: bold;
  margin-right: 5px;
}
</style>
