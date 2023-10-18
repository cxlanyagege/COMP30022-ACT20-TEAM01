<!-- The component code was written by Yawen Luo. This code is used to display the 
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
        <span>{{ studentInfo.aap === "Yes" ? "Yes" : "No" }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Documentation: </label>
        <span>{{ fileData ? fileData.name : "NA" }}</span>
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
    },
    fileData: {
      default: null,
    },
  },
};
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
