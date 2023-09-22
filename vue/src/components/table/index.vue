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
            type="danger"
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
    this.updateRequests(this.pageNum, this.pageSize)
  },

  methods: {
    // 处理删除操作
    handleDelete(idNo) {
      // 根据 idNo 删除相应的请求
      deleteRequest(idNo).then(res=>{
        console.log(res.data);
        this.updateRequests(this.pageNum, this.pageSize)
      })
    },

    showRequestDetail(requestId) {
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

    updateRequests(page, pageSize) {
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
      this.updateRequests(pageNum, this.pageSize);
    }
  },
  created() {
    // set componenent name
    this.$root.$refs.table_component = this;
  }
}
</script>

