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
      <el-table-column prop="subject" label="SUBJECT CODE" />
      <el-table-column prop="name" label="TASK NAME" />
      <el-table-column prop="type" label="TASK TYPE" />
      <el-table-column prop="status" label="STATUS" />
      <el-table-column prop="date" label="DUE DATE" />
      <el-table-column label="ACTION">
        <template slot-scope="scope">
          <el-button
            type="danger"
            size="small"
            @click="handleDelete(scope.row.idNo)"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          idNo: '123456',
          subject: 'COMP30002',
          name: 'Asssignment 1',
          type: 'Individial',
          status: 'UNASSESSED',
          date: '23/08/2023',
          action: 'delete'
        }
      ],
      listLoading: false
    }
  },
  methods: {
    // 处理删除操作
    handleDelete(idNo) {
      // 根据 idNo 删除相应的请求
      const index = this.tableData.findIndex((item) => item.idNo === idNo)
      if (index !== -1) {
        this.tableData.splice(index, 1)
      }
    }
  }
}
</script>

