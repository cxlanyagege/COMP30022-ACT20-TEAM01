<!-- written by Lanruo Su -->
<template>
  <div class="app-container">
    <!-- Request Authority Table -->
    <el-table
      v-loading="listLoading"
      :data="tableData"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
    >
      <el-table-column prop="Assignment" label="Assignment" style="width: 26%">
        <template slot-scope="{ row }">
          <!-- <el-switch v-model="row.Assignment" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" @change="handleSwitchChange(row, 'Assignment')"></el-switch> -->
          <el-switch v-model="row.Assignment" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" />
        </template>
      </el-table-column>
      <el-table-column prop="Quiz" label="Quiz" style="width: 18%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Quiz" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" />
        </template>
      </el-table-column>
      <el-table-column prop="Exam" label="Exam" style="width: 18%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Exam" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" />
        </template>
      </el-table-column>
      <el-table-column prop="Personal" label="Personal" style="width: 20%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Personal" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" />
        </template>
      </el-table-column>
      <el-table-column prop="Others" label="Others" style="width: 18%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Others" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" />
        </template>
      </el-table-column>
      <el-table-column label="ACTION">
        <template>
          <el-button
            type="primary"
            size="small"
            @click="confirmationVisible = true"
          >
            SAVE CHANGES
          </el-button>
          <el-dialog title="Save changes?" :visible.sync="confirmationVisible">
            <span>Are you sure you want to save the changes?</span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="confirmationVisible = false">cancel</el-button>
              <el-button type="primary" @click="updateAllowedRequests()">confirm</el-button>
            </span>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
// '@/views/tutorAuthority/index.vue'
// import { EventBus } from '@/utils/EventBus'
import { updateSubjectApprovedRequests, getSubjectDetail } from '@/api/api'

export default {
  data() {
    return {
      tableData: [
        {
          Assignment: undefined,
          Quiz: undefined,
          Exam: undefined,
          Personal: undefined,
          Others: undefined
        }
      ],
      listLoading: false,
      confirmationVisible: false
    }
  },

  // written by Dennis Wang, initialize the types of requests
  // that can be authorised to process
  created() {
    getSubjectDetail(this.$store.getters.subjectId).then((res) => {
      console.log(res.data)
      this.tableData[0].Assignment = res.data.data.assignmentRequest
      this.tableData[0].Quiz = res.data.data.quizRequest
      this.tableData[0].Exam = res.data.data.examRequest
      this.tableData[0].Personal = res.data.data.personalRequest
      this.tableData[0].Others = res.data.data.othersRequest
    })
  },

  methods: {
    // written by Dennis Wang, update the type of requests this subject
    // can recieved by coordinator
    updateAllowedRequests() {
      this.confirmationVisible = false
      const param = {
        assignmentRequest: this.tableData[0].Assignment,
        quizRequest: this.tableData[0].Quiz,
        examRequest: this.tableData[0].Exam,
        personalRequest: this.tableData[0].Personal,
        othersRequest: this.tableData[0].Others
      }
      updateSubjectApprovedRequests(this.$store.getters.subjectId, param).then((res) => {
        console.log(res.data)
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>
