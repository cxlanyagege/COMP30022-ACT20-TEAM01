<!-- writen by Lanruo Su (based on the dashboard table made by Xuan Zhang) -->
<template>
  <div>
    <div>
      <!-- Add button to filter based on request type -->
      <el-button type="primary" plain style="width: 17%; margin-right: 2%; margin-left: 1.5%" @click="applyFilter('All')">All</el-button>
      <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Assignment')">Assignment</el-button>
      <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Test')">Test</el-button>
      <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Exam')">Exam</el-button>
      <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Individual')">Individual</el-button>
      <el-button plain style="width: 13%; margin-right: 2%" @click="applyFilter('Others')">Others</el-button>
    </div>
    <el-table
      :data="filteredData"
      style="width: 100%; top: 28px"
      border
      stripe
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="Student ID">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="Subject ID">
              <span>{{ props.row.subID }}</span>
            </el-form-item>
            <el-form-item label="Application Date">
              <span>{{ props.row.appDate }}</span>
            </el-form-item>
            <el-form-item label="Request Type">
              <span>{{ props.row.reqType }}</span>
            </el-form-item>
            <el-form-item label="Task Type">
              <span>{{ props.row.taskType }}</span>
            </el-form-item>
            <el-form-item label="Request Name">
              <span>{{ props.row.reqName }}</span>
            </el-form-item>
            <el-form-item label="Request Details">
              <span>{{ props.row.reqDetail }}</span>
            </el-form-item>
            <el-form-item label="Attached Files">
              <span>{{ props.row.files }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        min-width="40"
        label="ID No."
        prop="id"
      >
      </el-table-column>
      <el-table-column
        label="Subject ID"
        min-width="55"
        prop="subID"
      >
      </el-table-column>
      <el-table-column
        label="Request Type"
        min-width="60"
        prop="reqType"
      >
      </el-table-column>
      <el-table-column
        label="Request Name"
        min-width="70"
        prop="reqName"
      >
      </el-table-column>
      <el-table-column
        label="Application Date"
        min-width="65"
        prop="appDate"
      >
      </el-table-column>
      <el-table-column label="Status" prop="status" min-width="60">
        <template slot-scope="{ row }">
          <el-tag v-if="row.status === 'UNASSESSED'" type="warning">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'APPROVE'" type="success">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'REJECT'" type="danger">{{ row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Decision" min-width="80">
        <template slot-scope="{ row }">
          <!-- 使用 v-if 条件渲染 -->
          <template v-if="row.status === 'UNASSESSED'">
            <el-button type="success" size="small" @click="handleApproveClick(row)">Approve</el-button>
            <el-button type="danger" size="small" @click="handleRejectClick(row)">Reject</el-button>
          </template>
          <template v-else>
            <span v-if="row.status === 'APPROVE'">Approved</span>
            <span v-if="row.status === 'REJECT'">Rejected</span>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="Flag" prop="flag" min-width="30">
        <template slot-scope="{ row }">
          <el-button
            v-if="!row.flagClicked"
            type="danger"
            :class="{ 'flag-button-clicked': row.flagClicked }"
            icon="el-icon-s-flag"
            circle
            @click="handleFlagClick(row)"
          >
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style>
  .demo-table-expand .el-form-item span {
    font-size: 14px; /* 设置字体大小 */
    color: #333; /* 设置字体颜色 */
    /* 添加其他样式属性 */
  }

  .demo-table-expand .el-form-item label {
    width: 150px; /* 设置标签宽度 */
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 1;
    width: 50%;
  }
  .flag-button-clicked {
  background-color: white;
  color: red;
  }
</style>

<script>
export default {
  data() {
    return {
      tableData: [{
        id: '1266704',
        subID: 'COMP30022',
        appDate: '01/01/2024',
        reqType: 'Assignment',
        taskType: 'Individual',
        reqName: 'A1 extension',
        reqDetail: 'Hi, I would like to apply for A1 extension',
        files: 'N/A',
        status: 'APPROVE',
        flagClicked: false
      }, {
        id: '1266705',
        subID: 'COMP30026',
        appDate: '01/01/2024',
        reqType: 'Test',
        taskType: 'N/A',
        reqName: 'Remarking MST',
        reqDetail: 'HI, I would like to apply for Remarking my MST',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false
      }, {
        id: '1266706',
        subID: 'COMP30023',
        appDate: '01/01/2024',
        reqType: 'Exam',
        taskType: 'N/A',
        reqName: 'Exam remark',
        reqDetail: 'HI, I would like tp apply for remarking my exam',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false
      }, {
        id: '1266704',
        subID: 'COMP20008',
        appDate: '01/01/2024',
        reqType: 'Others',
        taskType: 'N/A',
        reqName: 'AAP certification',
        reqDetail: 'Hi, I would like to ask about my AAP certification stuff',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false
      }],
      // add a list to store the filtered data
      filteredData: []
    }
  },
  created() {
    // 初始化时将所有 "Subject ID" 为 "COMP30023" 的行存储到 filteredData 中
    this.filteredData = this.tableData.filter(item => item.subID === 'COMP30023')
  },
  methods: {
    handleFlagClick(row) {
      if (!row.flagClicked) {
        // mark the clicked flag as clicked
        this.flagClicked = !this.flagClicked
        // move the current row to the front
        this.tableData = [row, ...this.tableData.filter(item => item !== row)]
      }``
    },
    handleApproveClick(row) {
      // pop out the confirmation window
      this.$confirm('Do you confirm your selection?(Approve)', 'Warning', {
        confirmButtonText: 'Send',
        cancelButtonText: 'Cancel',
        type: 'success'
      }).then(() => {
        // click confirm for approve
        row.status = 'APPROVE'
      }).catch(() => {
      })
    },
    handleRejectClick(row) {
      // comfirmation window pop out
      this.$confirm('Do you confim your selection?(Reject)', 'Warning', {
        confirmButtonText: 'Send',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        // click confirm for reject
        row.status = 'REJECT'
      }).catch(() => {
        // user cancel the button
      })
    },
    applyFilter(filterCondition) {
      // filtering according to filterCondition
      if (filterCondition === 'All') {
        // reset filteredData to the rows with "Subject ID" of "COMP30023"
        this.filteredData = this.tableData.filter(item => item.subID === 'COMP30023')
      } else {
        // reset filteredData to the rows with "Subject ID" of "COMP30023"
        this.filteredData = this.tableData.filter(item => item.subID === 'COMP30023')
        // make further filtering based on the subject
        if (filterCondition === 'Assignment') {
          this.filteredData = this.filteredData.filter(item => item.reqType === 'Assignment')
        } else if (filterCondition === 'Test') {
          this.filteredData = this.filteredData.filter(item => item.reqType === 'Test')
        } else if (filterCondition === 'Exam') {
          this.filteredData = this.filteredData.filter(item => item.reqType === 'Exam')
        } else if (filterCondition === 'Individual') {
          this.filteredData = this.filteredData.filter(item => item.reqType === 'Individual')
        } else if (filterCondition === 'Others') {
          this.filteredData = this.filteredData.filter(item => item.reqType === 'Others')
        }
      }
    }
  }
}
</script>
