<!-- The component code was written by Yawen Luo, and Dennis Wang and He Shen were modified the 
     front-end and back-end interaction method code at a later stage. The following code is used 
     to build the component table elements of the dashboard. -->

<template>
  <div class="app-container">
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
      <el-table-column prop="idNo" label="ID NO." />
      <el-table-column prop="type" label="REQUEST TYPE" />
      <el-table-column prop="name" label="REQUEST NAME" />
      <el-table-column label="STATUS" prop="status">
        <template slot-scope="{ row }">
          <el-tag v-if="row.status === 'WAITING'" type="warning">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'APPROVED'" type="success">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'REJECTED'" type="danger">{{ row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="APPLICATION DATE" />
      <el-table-column label="ACTION">
        <template slot-scope="scope">
          <!-- showRequestDetail(scope.row.idNo) -->
          <el-button
            type="info"
            size="small"
            @click="handleDetailClick(scope.row.idNo)"
          >Detail</el-button>
          <el-dialog
            v-model="dialogVisible"
            title="Request detail"
            width="30%"
          >
            <div>
              <p><strong>Student ID:</strong> {{ requestDetail.studentId }}</p>
              <p><strong>Request Detail:</strong> {{ requestDetail.detail }}</p>
              <p><strong>Request Type:</strong> {{ requestDetail.region }}</p>
              <p><strong>Rqeuest Name:</strong> {{ requestDetail.name }}</p>
              <p><strong>Task Type:</strong> {{ requestDetail.type }}</p>
            </div>
          </el-dialog>
          <el-divider direction="vertical" />
          <el-button
            type="danger"
            size="small"
            @click="handleDelete(scope.row.idNo)"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="pageNum"
      :page-size="pageSize"
      :total="total"
      layout="prev, pager, next"
      @current-change="handlePageChange"
    />
  </div>
</template>

<script>
import {getRequests, addRequest, deleteRequest, getRequest} from '@/api/request'

export default {
  data() {
    return {
      tableData: [],
      listLoading: false,
      pageNum: 1,
      pageSize: 5,
      total: 0,
      dialogVisible: false,
      requestDetail: {
        studentId: '',
        detail: '',
        region: '',
        name: '',
        type: '',
        fileList: []
      }
    }
  },

  mounted() {
    this.updateRequests(this.pageNum, this.pageSize)
  },

  methods: {
    handleDelete(idNo) {
      // delete request based on requestid
      deleteRequest(idNo).then(res=>{
        console.log(res.data);
        this.updateRequests(this.pageNum, this.pageSize)
      })
    },

    handleDetailClick(idNo) {
      this.dialogVisible = true;
      this.showRequestDetail(idNo);
    },

    showRequestDetail(requestId) {
      getRequest(requestId, null).then(res => {
        console.log(res.data);
        this.requestDetail.studentId = res.data.data.studentId;
        this.requestDetail.detail = res.data.data.description;
        this.requestDetail.region = res.data.data.requestType;
        this.requestDetail.name = res.data.data.requestName;
        this.requestDetail.type = res.data.data.taskType;
        // this.requestDetail.teammates = res.data.data.teammates;
        // editableItem.subjectId = "";
        // editableItem.requestId = requestId;
        this.requestDetail.fileList = res.data.data.attachments.map(item => {
          return {uid: item.attachmentId, url: this.$root.$refs.form_component.convertUrlWithPrefix(item.url)}
        })
      })
      console.log(this.requestDetail)
    },

    // WRITTEN BY DENNIS
    updateRequests(page, pageSize) {
      // send the update request to the server and return all requests 
      // that are still waiting to be accessed 
      console.log('handle requests');
      const param = {
        pageNum: page,
        pageSize,
        status: "WAITING"
      }
      getRequests(1266288, param).then((res) => {
        console.log(res.data)

        if (res.data.data.records.length == 0 && page != 0){
          this.pageNum = page - 1
          this.updateRequests(this.pageNum, pageSize)
        } else {
          this.tableData = (res.data.data.records.length == 0 && page == 0) ? [] :
          res.data.data.records.map(record => {
            return {
              idNo: record.requestId,
              type: record.requestType,
              name: record.requestName,
              status: record.status,
              date: record.submissionDate,
              action: 'delete'
            }
          })
          this.total = res.data.data.total
          this.pageNum = res.data.data.current
        }
        // console.log(this.pageNum, this.pageSize)
      });
    },
    handlePageChange(pageNum) {
      // used to handle the pagination
      this.updateRequests(pageNum, this.pageSize);
    }
  },
  created() {
    // set componenent name
    this.$root.$refs.table_component = this;
  }
}
</script>

