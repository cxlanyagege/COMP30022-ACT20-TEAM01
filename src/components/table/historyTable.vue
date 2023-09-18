<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="filteredData"
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
          <el-tag v-if="row.status === 'UNASSESSED'" type="warning">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'APPROVE'" type="success">{{ row.status }}</el-tag>
          <el-tag v-else-if="row.status === 'REJECT'" type="danger">{{ row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="APPLICATION DATE" />
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
          type: 'Task',
          name: 'Asssignment 1',
          status: 'REJECT',
          date: '23/08/2023'
        },
        {
          idNo: '123578',
          type: 'Personal',
          name: 'Leave of Absence for Tut',
          status: 'APPROVE',
          date: '30/08/2023'
        }
      ],
      listLoading: false
    }
  },
  computed: {
    // 根据状态过滤数据
    filteredData() {
      return this.tableData.filter(item => item.status === 'APPROVE' || item.status === 'REJECT');
    }
  },
}
</script>

