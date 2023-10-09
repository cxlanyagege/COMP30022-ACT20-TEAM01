<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%; top: 28px"
      border
      stripe
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="Request Type">
              <span>{{ props.row.reqType }}</span>
            </el-form-item>
            <el-form-item label="Task Type">
              <span>{{ props.row.taskType }}</span>
            </el-form-item>
            <el-form-item label="More specific">
              <span>{{ props.row.morespecific }}</span>
            </el-form-item>
            <el-form-item label="Request Name">
              <span>{{ props.row.reqName }}</span>
            </el-form-item>
            <el-form-item label="Request Details">
              <span>{{ props.row.reqDetail }}</span>
            </el-form-item>
            <el-form-item label="AAPs">
              <span>{{ props.row.AAPs }}</span>
            </el-form-item>
            <el-form-item label="Attached Files">
              <span>{{ props.row.files }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        min-width="40"
        label="Student ID"
        prop="id"
      >
        <template slot-scope="{ row }">
          <el-button type="text" @click="showStudentProfile(row)">{{ row.id }}</el-button>
        </template>
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
        min-width="60"
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
          <!-- use v-if  -->
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
    <el-dialog v-if="showProfileDialog" title="Student Profile" :visible.sync="showProfileDialog">
      <student-profile :studentInfo="selectedStudentInfo" :fileData="selectedFileData" />
    </el-dialog>
    <ConfirmationDialog
      :visible.sync="approveDialogVisible"
      :form.sync="approveForm"
      @confirm="handleApprove"
    />
  </div>
</template>

<style>
  .demo-table-expand .el-form-item span {
    font-size: 14px; /* set font size */
    color: #333; /* set font color */
  }

  .demo-table-expand .el-form-item label {
    width: 150px; /* set tag length */
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
import StudentProfile from "@/components/Forms/studentProfile.vue";
import ConfirmationDialog from "@/components/Forms/confirmationDialog.vue";

export default {
  components: {
    StudentProfile,
    ConfirmationDialog
  },
  data() {
    return {
      approveDialogVisible: false,
        approveForm: {
        message: '',
        date: ''
        },
      showProfileDialog: false,
      selectedStudentInfo: null,
      selectedFileData: null,
      tableData: [{
        id: '1266704',
        subID: 'COMP30022',
        appDate: '01/01/2024',
        AAPs: 'Yes',
        reqType: 'Assignment',
        taskType: 'Individual',
        reqName: 'A1 extension',
        reqDetail: 'Hi, I would like to apply for A1 extension',
        files: 'N/A',
        status: 'APPROVE',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Extension'
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
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Remark'
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
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Remark'
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
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Others'
      }]
    }
  },
  methods: {
    showStudentProfile(row) {
      this.selectedStudentInfo = row;
      this.selectedFileData = row.files;
      this.showProfileDialog = true;
    },
    handleFlagClick(row) {
      if (!row.flagClicked) {
        // mark the clicked flag as clicked
        this.flagClicked = !this.flagClicked
        // move the current row to the front
        this.tableData = [row, ...this.tableData.filter(item => item !== row)]
      }``
    },
    handleApproveClick(row) {
      this.approveForm.message = '';
      this.approveForm.date = '';
      this.selectedRow = row;
      this.approveDialogVisible = true;
    },
    handleApprove() {
      const { message, date } = this.approveForm;
      if (message && date) {
        // 更新状态和决定为'APPROVE'
        this.selectedRow.status = 'APPROVE';
        this.selectedRow.decision = 'Approved';
        // 关闭确认对话框
        this.approvalConfirmationDialogVisible = false;
      }
    },
    handleRejectClick(row) {
      // confirmation window pop out
      this.$confirm('Do you confirm your selection?(Reject)', 'Warning', {
        confirmButtonText: 'Send',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        // click confirm for reject
        row.status = 'REJECT'
      }).catch(() => {
        // user cancel the button
      })
    }
  }
}
</script>
