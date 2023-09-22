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
import {getRequests} from '@/api/request'

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
    updateRequests(page, pageSize) {
      console.log('handle requests');
      const param = {
        pageNum: page,
        pageSize,
        status: "OTHER"
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
    this.$root.$refs.history_component = this;
  }
}
</script>


