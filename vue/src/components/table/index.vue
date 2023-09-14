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
          type: 'Task',
          name: 'Asssignment 1',
          status: 'UNASSESSED',
          date: '23/08/2023',
          action: 'delete'
        },
        {
          idNo: '123578',
          type: 'Personal',
          name: 'Leave of Absence for Tut',
          status: 'APPROVE',
          date: '30/08/2023',
          action: 'delete'
        }
      ],
      listLoading: false
    }
  },
  computed: {
    // 根据状态过滤数据
    filteredData() {
      return this.tableData.filter(item => item.status === 'UNASSESSED');
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

