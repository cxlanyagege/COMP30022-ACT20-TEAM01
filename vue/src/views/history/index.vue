<template>
  <div class="app-container">
    <el-input v-model="filterText" @input="filterTable" placeholder="Filter keyword"/>
    <HistoryTable :data="filteredTableData" />

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
      // tableData: [], // 你的表格数据
      filterText: '',
      filteredTableData: []
    }
  },
  methods: {
    filterTable() {
      // Convert filterText to lowercase for case-insensitive filtering
      const keyword = this.filterText.toLowerCase().trim();
      
      // Filter the tableData based on the keyword
      this.filteredTableData = this.$root.$refs.history_component.tableData.filter(item => {
        // Customize this logic to match your data structure and filter requirements
        // For example, you can filter based on a specific column (e.g., item.name)
        // Adjust the condition as needed to match your use case.
        if (item.name.toLowerCase().includes(keyword)){
          return item;
        }
      });
      console.log(this.filteredTableData)
    }
  }
  // 其他组件选项和方法
}
</script>

