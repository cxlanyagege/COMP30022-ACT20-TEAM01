<template>
  <div class="dashboard-container">
    <Table :data="tableData" />
  </div>
</template>

<script>
import Table from '@/components/table/index'

export default {
  components: {
    Table
  },
  data() {
    return {
      tableData: [] // 你的表格数据
    }
  }
  // 其他组件选项和方法
}
</script>
