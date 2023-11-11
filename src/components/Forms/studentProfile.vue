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
        <span>{{ studentInfo.id }}</span>
      </div>
      <div class="profile-item">
        <label>Student Email: </label>
        <span>{{ studentInfo.email }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Beneficiaries: </label>
        <span>{{ studentInfo.aap === "Yes" ? "Yes" : "NO" }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Documentation: </label>
        <span>{{ fileData ? fileData.name : "NA" }}</span>
      </div>
      <div class="profile-item">
        <label>Student History: </label>
        <span>
          <!-- Nested Text Button -->
          <button @click="viewStudentHistory">View History</button>
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
      required: true,
      fileData: null
    }
  },
  methods: {
    viewStudentHistory() {
      // Handle the action when the "View History" button is clicked
      // You can navigate to the student history or show a modal, for example.
      // Example: this.$router.push(`/studentHistory/${this.studentInfo.id}`);
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
