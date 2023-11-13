<template>
  <div>
    <!-- Add button to filter based on request type -->
    <el-button type="primary" plain style="width: 17%; margin-right: 2%; margin-left: 1.5%" @click="applyFilter('All')">All</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Assignment')">Assignment</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Quiz')">Quiz</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Exam')">Exam</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Personal')">Personal</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Others')">Others</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filterCondition: 'All'
    }
  },
  methods: {
    applyFilter(filterCondition) {
      // filter according to "filterCondition"
      this.filterCondition = filterCondition
      this.$emit('filter', filterCondition)
    }
  }
}
</script>
