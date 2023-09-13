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
      <el-table-column label="STATUS">
        <template>
          <el-tag type="warning">UNASSESSED</el-tag>
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
          status: 'UNASSESSED',
          date: '23/08/2023'
        },
        {
          idNo: '123578',
          type: 'Personal',
          name: 'Leave of Absence for Tut',
          status: 'UNASSESSED',
          date: '30/08/2023'
        }
      ],
      listLoading: false
    }
  }
}
</script>

