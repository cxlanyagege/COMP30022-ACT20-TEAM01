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
          <el-button
            type="info"
            size="small"
            @click="showRequestDetail(scope.row.idNo)"
          >Detail</el-button>
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
    }
  },
  mounted() {
    // show the request list when the component's been added to DOM
    this.updateRequests(this.pageNum, this.pageSize)
  },

  methods: {
    // MODIFIED BY DENNIS
    handleDelete(idNo) {
      // delete request based on requestid
      deleteRequest(idNo).then(res=>{
        console.log(res.data);
        this.updateRequests(this.pageNum, this.pageSize)
      })
    },

    // MODIFIED BY DENNIS
    showRequestDetail(requestId) {
      // get the details of a specified request when student
      // want to check the content of it
      this.$root.$refs.button_component.formVisible = true;
      this.$root.$refs.form_component.isCheck = true;
      getRequest(requestId, null).then(res => {
        console.log(res.data);
        this.$root.$refs.form_component.form.studentId = res.data.data.studentId;
        this.$root.$refs.form_component.form.detail = res.data.data.description;
        this.$root.$refs.form_component.form.region = res.data.data.requestType;
        this.$root.$refs.form_component.form.name = res.data.data.requestName;
        this.$root.$refs.form_component.form.email = false;
        this.$root.$refs.form_component.form.type = res.data.data.taskType;
        // editableItem.subjectId = "";
        // editableItem.requestId = requestId;
        this.$root.$refs.form_component.form.fileList = res.data.data.attachments.map(item => {
          return {uid: item.attachmentId, url: this.$root.$refs.form_component.convertUrlWithPrefix(item.url)}
        })
      })
    },

    // WRITTEN BY DENNIS & HE SHEN
    updateRequests(page, pageSize) {
      // send the update request to the server and return all requests 
      // that are still waiting to be accessed 
      console.log('handle requests');
      const param = {
        pageNum: page,
        pageSize,
        status: "WAITING"
      }
      const userId = this.$store.getters.id
      getRequests(userId, param).then((res) => {
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

    // WRITTEN BY DENNIS
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

