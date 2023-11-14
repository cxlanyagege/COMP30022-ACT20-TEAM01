<!-- The component code was written by Yawen Luo in student interface, edited by Xuan Zhang. This code is used to display the
     form element component of the student's personal information -->

<template>
  <div class="student-profile">
    <div>
      <div class="profile-item">
        <label>Name: </label>
        <span>{{ studentInfo.name }}</span>
      </div>
      <div class="profile-item">
        <label>Student ID: </label>
        <span>{{ studentInfo.studentId }}</span>
      </div>
      <div class="profile-item">
        <label>Student Email: </label>
        <span>{{ studentInfo.email }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Beneficiaries: </label>
        <span>{{ studentInfo.aapAttachment != null ? "Yes" : "NO" }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Documentation: </label>
        <template v-if="studentInfo.aapAttachment != null">
          <a :href="studentInfo.aapAttachment.attachmentUrl" target="_blank">
            <span style="color: rgb(0, 85, 255)">{{
              studentInfo.aapAttachment.attachmentUrl }}</span>
          </a>
        </template>
        <template v-else> NA </template>
      </div>
      <div class="profile-item">
        <label>Student History: </label>
        <span>
          <!-- Nested Text Button -->
          <el-button type="text" @click="viewStudentHistory()">View History</el-button>
          <el-dialog title="Student Request History" :visible.sync="dialogVisible" append-to-body>
            <el-table
              :data="studentInfo.requestHistory"
              height="350"
              stripe
              border
            >
              <!-- More columns may be added-->
              <el-table-column property="requestName" label="Request Name" width="200" />
              <el-table-column property="description" label="Description" width="200" />
              <el-table-column property="requestType" label="Request Type" width="200" />
              <el-table-column property="taskType" label="Task Type" width="200" />
              <el-table-column property="workType" label="More Specific" width="200" />
            </el-table>
          </el-dialog>
        </span>
      </div>
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
  },
  data() {
    return {
      dialogVisible: false
    }
  },
  methods: {
    viewStudentHistory() {
      this.dialogVisible = true
    }
  }
}
</script>

<style scoped>
.student-profile {
  padding: 20px;
}

.profile-item {
  margin-bottom: 30px;
}

.label {
  font-weight: bold;
  margin-right: 5px;
}
</style>
