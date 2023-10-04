<!-- The component code was written by Yawen Luo, Dennis Wang was modified the 
     front-end and back-end interaction method code at a later stage. The following code is used 
     to build the component request table elements. -->

<template>
  <div class="app-container">
    <el-scrollbar height="300px">
      <el-table
        v-loading="listLoading"
        :data="combinedData"
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
            <el-button
              type="info"
              size="small"
              @click="handleDetailClick(scope.row.idNo)"
              >Detail</el-button
            >
            <el-dialog
              v-model="dialogVisible"
              title="Request detail"
              :visible.sync="dialogVisible"
              width="50%"
            >
              <div>
                <p>
                  <strong>Student ID:</strong> {{ requestDetail.studentId }}
                </p>
                <p>
                  <strong>Request Detail:</strong> {{ requestDetail.detail }}
                </p>
                <p><strong>Request Type:</strong> {{ requestDetail.region }}</p>
                <p><strong>Rqeuest Name:</strong> {{ requestDetail.name }}</p>
                <p><strong>Task Type:</strong> {{ requestDetail.type }}</p>

                <p v-if="requestDetail.fileList.length > 0">
                  <strong>Attachments:</strong>
                </p>
                <ul v-if="requestDetail.fileList.length > 0">
                  <li v-for="file in requestDetail.fileList" :key="file.uid">
                    <a :href="file.url" target="_blank">{{
                      file.url.substr(uploadURL.length, file.url.length)
                    }}</a>
                  </li>
                </ul>
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
    </el-scrollbar>
  </div>
</template>

<script>
import {
  getRequests,
  addRequest,
  deleteRequest,
  getRequest,
} from "@/api/request";
import { uploadURL } from "@/config/config";

export default {
  data() {
    return {
      waitingData: [],
      processedData: [],
      listLoading: false,
      dialogVisible: false,
      requestDetail: {
        studentId: "",
        detail: "",
        region: "",
        name: "",
        type: "",
        fileList: [],
      },
      uploadURL: uploadURL,
    };
  },

  computed: {
    combinedData() {
      return [...this.waitingData, ...this.processedData];
    },
  },

  mounted() {
    this.updateRequests();
  },

  methods: {
    handleDelete(idNo) {
      // delete request based on requestid
      deleteRequest(idNo).then((res) => {
        console.log(res.data);
        this.updateRequests();
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
        this.requestDetail.fileList = res.data.data.attachments.map((item) => {
          return {
            uid: item.attachmentId,
            url: this.$root.$refs.form_component.convertUrlWithPrefix(item.url),
          };
        });
      });
      console.log(this.requestDetail);
    },
    updateRequests() {
      console.log("handle requests");
      const param1 = {
        status: "WAITING",
      };
      getRequests(1266288, param1).then((res) => {
        console.log(res.data);
        if (res.data.data.length === 0) {
          this.waitingData = [];
        } else {
          const requestData = res.data.data.map((record) => {
            return {
              idNo: record.requestId,
              type: record.requestType,
              name: record.requestName,
              status: record.status,
              date: record.submissionDate,
              action: "delete",
            };
          });
          requestData.sort((a, b) => {
            const dateA = new Date(a.date);
            const dateB = new Date(b.date);
            return dateB - dateA;
          });
          this.waitingData = requestData;
        }
      });
      const param2 = {
        status: "OTHER",
      };
      getRequests(1266288, param2).then((res) => {
        console.log(res.data);
        if (res.data.data.length === 0) {
          this.processedData = [];
        } else {
          const requestData = res.data.data.map((record) => {
            return {
              idNo: record.requestId,
              type: record.requestType,
              name: record.requestName,
              status: record.status,
              date: record.submissionDate,
              action: "delete",
            };
          });
          requestData.sort((a, b) => {
            const dateA = new Date(a.date);
            const dateB = new Date(b.date);
            return dateB - dateA;
          });
          this.processedData = requestData;
        }
      });
    },
    handleScroll(e) {
      const bottomOfContainer =
        e.target.scrollHeight - e.target.scrollTop === e.target.clientHeight;
      console.log(bottomOfContainer);
      if (bottomOfContainer) {
        this.updateRequests();
      }
    },
  },
  created() {
    this.$root.$refs.table_component = this;
  },
};
</script>

<style>
.loading-indicator {
  text-align: center;
  padding: 10px;
}
</style>