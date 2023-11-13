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
              <template v-if="props.row.files.length > 0">
                <li v-for="file in props.row.files" :key="file.uid">
                  <a :href="file.url" target="_blank">
                    <span style="color: rgb(0, 85, 255)">{{
                      file.url.substr(uploadURL.length, file.url.length)
                    }}</span>
                  </a>
                </li>
              </template>
              <template v-else> No file uploaded </template>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        min-width="40"
        label="Student ID"
        prop="studentId"
      >
        <template slot-scope="{ row }">
          <el-button type="text" @click="showStudentProfile(row)">{{ row.studentId }}</el-button>
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
          <el-tag v-else-if="row.status === 'APPROVED'" type="success">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'REJECTED'" type="danger">{{ row.status }}</el-tag>
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
            <span v-if="row.status === 'APPROVED'">Approved</span>
            <span v-if="row.status === 'REJECTED'">Rejected</span>
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
      <student-profile :studentInfo="selectedStudentInfo"/>
    </el-dialog>
    <ConfirmationDialog
      :visible.sync="approveDialogVisible"
      :form.sync="approveForm"
      :workType="selectedRow ? selectedRow.morespecific : ''"
      @confirm="handleApprove()"
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
import StudentProfile from '@/components/Forms/studentProfile.vue'
import ConfirmationDialog from '@/components/Forms/confirmationDialog.vue'
import { uploadURL } from "@/config/config"
import { getStudentDetail, updateRequest } from "@/api/api"

export default {
  components: {
    StudentProfile,
    ConfirmationDialog
  },
  props: {
    tableData: Array
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
      // selectedFileData: null,
      selectedRow: null,
      uploadURL: uploadURL
    }
  },
  methods: {
    showStudentProfile(row) {
      // console.log(row)
      let param = {
        subjectId: this.$store.getters.subjectId
      }
      getStudentDetail(row.studentId, param).then((res) => {
        // console.log(res.data)
        this.selectedStudentInfo = res.data.data
        // this.selectedFileData = res.data.data.aapAttachment
        this.showProfileDialog = true
      })
    },
    handleFlagClick(row) {
      this.$emit('flagClick', row)
    },
    handleApproveClick(row) {
      console.log(row)
      this.approveForm.message = ''
      this.approveForm.date = ''
      this.selectedRow = row
      this.approveDialogVisible = true
    },
    handleApprove() {
      // const { message, date } = this.approveForm
      let param = {
        status: "APPROVED",
        message: this.approveForm.message
      }
      updateRequest(this.selectedRow.requestId, param).then((res) => {
        // 更新状态和决定为'APPROVE'
        this.selectedRow.status = 'APPROVED'
        // 关闭确认对话框
        this.approveDialogVisible = false
      })
    },
    handleRejectClick(row) {
      // confirmation window pop out
      this.$confirm('Do you confirm your selection?(Reject)', 'Warning', {
        confirmButtonText: 'Send',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        // click confirm for reject
        let param = {
          status: "REJECTED",
          message: "Request rejected"
        }
        updateRequest(row.requestId, param).then((res) => {
          row.status = 'REJECTED'
        })
      }).catch(() => {
        // user cancel the button
      })
    }
  }
}
</script>
