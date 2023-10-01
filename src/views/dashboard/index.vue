<!-- writeen by Xuan Zhang -->
<template>
  <div>
    <div>
      <!-- 添加筛选功能按钮 -->
      <button @click="applyFilterA('someFilter')">Assignment</button>
      <button @click="applyFilterT('someFilter')">Test</button>
      <button @click="applyFilterE('someFilter')">Exam</button>
      <button @click="applyFilterI('someFilter')">Personal</button>
      <button @click="applyFilterO('someFilter')">Others</button>
    </div>
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      stripe
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="Student ID">
              <span>{{ props.row.id }}</span>
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
        label="Request Type"
        prop="reqType"
      >
      </el-table-column>
      <el-table-column
        label="Request Name"
        prop="reqName"
      >
      </el-table-column>
      <el-table-column
        label="Application Date"
        prop="appDate"
      >
      </el-table-column>
      <el-table-column label="Status" prop="status">
        <template slot-scope="{ row }">
          <el-tag v-if="row.status === 'UNASSESSED'" type="warning">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'APPROVE'" type="success">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'REJECT'" type="danger">{{ row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Decision">
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
      <el-table-column label="Flag" prop="flag" min-width="40">
        <template slot-scope="{ row }">
          <el-button
            v-if="!row.flagClicked"
            @click="handleFlagClick(row)"
            type="danger"
            :class="{ 'flag-button-clicked': row.flagClicked }"
            icon="el-icon-s-flag"
          circle>
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
        appDate: '01/01/2024',
        reqType: 'Others',
        taskType: 'N/A',
        reqName: 'AAP certification',
        reqDetail: 'Hi, I would like to ask about my AAP certification stuff',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false
      }]
    }
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
    applyFilterA(filterCondition) {
    // 在这里根据 filterCondition 对 tableData 数据进行筛选
    // 更新 tableData 数据源，以显示符合条件的数据
      if (filterCondition === 'someFilter') {
        // 筛选出 reqType 为 'Assignment' 的数据
        this.tableData = this.tableData.filter(item => item.reqType === 'Assignment')
      }
    },
    applyFilterT(filterCondition) {
      if (filterCondition === 'someFilter') {
        // 例如，筛选出 reqType 为 'Test' 的数据
        this.tableData = this.tableData.filter(item => item.reqType === 'Test')
      }
    },
    applyFilterE(filterCondition) {
      if (filterCondition === 'someFilter') {
        // 例如，筛选出 reqType 为 'Exam' 的数据
        this.tableData = this.tableData.filter(item => item.reqType === 'Exam')
      }
    },
    applyFilterI(filterCondition) {
      if (filterCondition === 'someFilter') {
        // 例如，筛选出 reqType 为 'Individual' 的数据
        this.tableData = this.tableData.filter(item => item.reqType === 'Individual')
      }
    },
    applyFilterO(filterCondition) {
      if (filterCondition === 'someFilter') {
        // 例如，筛选出 reqType 为 'Others' 的数据
        this.tableData = this.tableData.filter(item => item.reqType === 'Others')
      }
    }
  }
}
</script>
