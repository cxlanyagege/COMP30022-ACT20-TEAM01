<template>
  <div>
    <!-- Add button to filter based on request type -->
    <el-button type="primary" plain style="width: 17%; margin-right: 2%; margin-left: 1.5%" @click="applyFilter('All')">All</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Assignment')">Assignment</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Test')">Test</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Exam')">Exam</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Personal')">Personal</el-button>
    <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Others')">Others</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filteredData: [] // 过滤后的数据
    };
  },
  created() {
    // initialize the filtering to "All"
    this.applyFilter('All')
  },
  methods: {
    applyFilter(filterCondition) {
      // filter according to "filterCondition"
      if (filterCondition === 'Assignment') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Assignment')
      } else if (filterCondition === 'Test') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Test')
      } else if (filterCondition === 'Exam') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Exam')
      } else if (filterCondition === 'Personal') {
        this.filteredData = this.tableData.filter(item => item.taskType === 'Personal')
      } else if (filterCondition === 'Others') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Others')
      } else {
        this.filteredData = this.tableData
      }
    }
  }
}
</script>
