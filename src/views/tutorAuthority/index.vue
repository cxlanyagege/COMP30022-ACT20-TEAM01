<template>
  <div class="app-container">
    <!-- Authority Table -->
    <el-table
      v-loading="listLoading"
      :data="tableData"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="position" label="Staff Position" :width="170" />
      <el-table-column prop="name" label="Staff Name" :width="179" />
      <el-table-column prop="email" label="Staff Email" :width="296" />
      <el-table-column prop="authority" label="Authority" :width="640">
        <template slot-scope="{ row }">
          <el-row>
            <el-col :span="4" style="width: 26%;">
              <el-checkbox
                v-model="row.authority[0]"
                label="Assignment"
                :width="150"
                @change="handleCheckboxChange(row, 0)"
              >Assignment</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox
                v-model="row.authority[1]"
                label="Quiz"
                @change="handleCheckboxChange(row, 1)"
              >Quiz</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox
                v-model="row.authority[2]"
                label="Exam"
                @change="handleCheckboxChange(row, 2)"
              >Exam</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 20%;">
              <el-checkbox
                v-model="row.authority[3]"
                label="Personal"
                @change="handleCheckboxChange(row, 3)"
              >Personal</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox
                v-model="row.authority[4]"
                :checked="true"
                label="Other"
                @change="handleCheckboxChange(row, 4)"
              >Other</el-checkbox>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="ACTION">
        <template slot-scope="{ row }">
          <el-row>
            <el-col :span="4" style="width: 26%;">
              <el-button
                type="primary"
                size="small"
                @click="confirmationVisible = true"
              >
                SAVE
              </el-button>
              <el-dialog title="Save changes?" :visible.sync="confirmationVisible">
                <span>Are you sure you want to save the changes?</span>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="confirmationVisible = false">cancel</el-button>
                  <el-button type="primary" @click="updateStaffInfo(row)">confirm</el-button>
                </span>
              </el-dialog>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getStaffRoleInfo, updateStaffAuthority } from '@/api/api'

export default {
  data() {
    return {
      tableData: [],
      listLoading: false,
      confirmationVisible: false
    }
  },
  created() {
    this.getRoleInfo()
  },
  methods: {
    getRoleInfo() {
      getStaffRoleInfo(this.$store.getters.subjectId).then((res) => {
        console.log(res.data)
        this.tableData = res.data.data.map((record) => {
          const autho = []
          autho.push(record.assignmentRequest)
          autho.push(record.quizRequest)
          autho.push(record.examRequest)
          autho.push(record.personalRequest)
          autho.push(record.otherRequest)
          return {
            position: record.staffRole,
            name: record.name,
            email: record.email,
            staffId: record.staffId,
            subjectId: record.subjectId,
            authority: autho
          }
        })
      })
    },
    updateStaffInfo(row) {
      this.confirmationVisible = false
      const param = {
        assignmentRequest: row.authority[0],
        quizRequest: row.authority[1],
        examRequest: row.authority[2],
        personalRequest: row.authority[3],
        othersRequest: row.authority[4],
        subjectId: row.subjectId
      }
      console.log(row)
      updateStaffAuthority(row.staffId, param).then((res) => {
        console.log(res.data)
      })
    },
    handleCheckboxChange(row, index) {
      const isChecked = row.authority[index]
      // If it's the first row, update other rows
      if (row === this.tableData[0]) {
        for (let i = 1; i < this.tableData.length; i++) {
          this.$set(this.tableData[i].authority, index, isChecked)
        }
      } else {
        // For other rows, update the first row if necessary
        if (isChecked) {
          // Add to the first row if not already present
          if (this.tableData[0].authority.indexOf(index) === -1) {
            this.tableData[0].authority.push(index)
          }
        } else {
          // Remove from the first row if present
          const indexInFirstRow = this.tableData[0].authority.indexOf(index)
          if (indexInFirstRow !== -1) {
            this.tableData[0].authority.splice(indexInFirstRow, 1)
          }
        }
      }
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

