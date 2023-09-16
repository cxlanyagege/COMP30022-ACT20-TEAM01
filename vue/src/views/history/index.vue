<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword"/>
    <HistoryTable :data="tableData" />

  </div>
</template>

<script>
import HistoryTable from '@/components/table/historyTable'

export default {
  components: {
    HistoryTable
  },
  data() {
    return {
      tableData: [], // 你的表格数据
      filterText: ''
    }
  }
  // 其他组件选项和方法
}
</script>

