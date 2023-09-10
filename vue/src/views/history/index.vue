<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />
    <Table :data="tableData" />

  </div>
</template>

<script>
import Table from '@/components/table/index.vue'

export default {
  components: {
    Table
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

