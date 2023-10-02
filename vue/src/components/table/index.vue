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
          <el-tag v-if="row.status === 'WAITING'" type="warning">{{
            row.status
          }}</el-tag>
          <el-tag v-else-if="row.status === 'APPROVED'" type="success">{{
            row.status
          }}</el-tag>
          <el-tag v-else-if="row.status === 'REJECTED'" type="danger">{{
            row.status
          }}</el-tag>
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
            >Detail</el-button
          >
          <el-dialog v-model="dialogVisible" title="Request detail" width="30%">
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
            >Delete</el-button
          >
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
import {
  getRequests,
  addRequest,
  deleteRequest,
  getRequest,
} from "@/api/request";

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
        studentId: "",
        detail: "",
        region: "",
        name: "",
        type: "",
        fileList: [],
      },
    };
  },

  mounted() {
    this.updateRequests(this.pageNum, this.pageSize);
  },

  methods: {
    handleDelete(idNo) {
      // delete request based on requestid
      deleteRequest(idNo).then((res) => {
        console.log(res.data);
        this.updateRequests(this.pageNum, this.pageSize);
      });
    },

    handleDetailClick(idNo) {
      this.dialogVisible = true;
      this.showRequestDetail(idNo);
    },

    showRequestDetail(requestId) {
      getRequest(requestId, null).then((res) => {
        console.log(res.data);
        this.requestDetail.studentId = res.data.data.studentId;
        this.requestDetail.detail = res.data.data.description;
        this.requestDetail.region = res.data.data.requestType;
        this.requestDetail.name = res.data.data.requestName;
        this.requestDetail.type = res.data.data.taskType;
        // this.requestDetail.teammates = res.data.data.teammates;
        // editableItem.subjectId = "";
        // editableItem.requestId = requestId;
        this.requestDetail.fileList = res.data.data.attachments.map((item) => {
          return {
            uid: item.attachmentId,
            url: this.$root.$refs.form_component.convertUrlWithPrefix(item.url),
          };
        });
      });
      console.log(this.requestDetail);
    },

    addNewRequest(newRequest) {
      // 将新请求插入表格数据的开头
      this.tableData.unshift(newRequest);

      // 如果新请求的状态是 WAITING，将其置顶
      if (newRequest.status === "WAITING") {
        this.tableData.sort((a, b) => {
          if (a.status === "WAITING" && b.status !== "WAITING") {
            return -1;
          }
          if (a.status !== "WAITING" && b.status === "WAITING") {
            return 1;
          }
          return 0;
        });
      }
      // 更新总数等其他状态信息（如果需要）
      this.total += 1;
    },

    // 在你的 updateRequests 方法中，修改 tableData 的获取逻辑
    updateRequests(page, pageSize) {
      console.log("handle requests");
      const param = {
        pageNum: page,
        pageSize,
      };
      getRequests(1266288, param).then((res) => {
        console.log(res.data);
        // 先保存等待状态的请求，然后将其从表格数据中删除
        const waitingRequests = this.tableData.filter(
          (item) => item.status === "WAITING"
        );
        this.tableData = this.tableData.filter(
          (item) => item.status !== "WAITING"
        );
        // 处理请求数据，将其转换成表格数据
        const requestData = res.data.data.records.map((record) => ({
          idNo: record.requestId,
          type: record.requestType,
          name: record.requestName,
          status: record.status,
          date: record.submissionDate,
          action: "delete",
        }));
        // 将获取到的请求数据添加到表格数据的前面
        this.tableData = [...requestData, ...this.tableData];
        // 将等待状态的请求重新添加到表格数据的最底部
        this.tableData = [...this.tableData, ...waitingRequests];
        this.total = res.data.data.total;
        this.pageNum = res.data.data.current;
      });
    },

    handlePageChange(pageNum) {
      // used to handle the pagination
      this.updateRequests(pageNum, this.pageSize);
    },
  },
  created() {
    // set componenent name
    this.$root.$refs.table_component = this;
  },
};
</script>

