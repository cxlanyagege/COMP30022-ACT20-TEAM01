<!-- Following code is Author by Yawen Luo, code is describe the entry page in dashboard view -->
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
      tableData: []
    }
  }
}
</script>
