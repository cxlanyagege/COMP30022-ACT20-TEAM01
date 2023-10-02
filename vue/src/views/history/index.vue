<!-- Following code is Author by Yawen Luo and modified by Dennis Wang code is describe 
     the entry page in history view -->

<template>
  <div class="app-container">
    <el-input
      v-model="filterText"
      @input="filterTable"
      placeholder="Filter keyword"
    />
    <HistoryTable />
  </div>
</template>

<script>
import HistoryTable from "@/components/table/historyTable";

export default {
  components: {
    HistoryTable,
  },
  data() {
    return {
      tableData: [],
      filterText: "",
      tempData: this.$root.$refs.history_component.tableData,
    };
  },
  methods: {
    // MODIFIed BY DENNIS
    filterTable() {
      // in order to make case-insensitive search
      const keyword = this.filterText.toLowerCase().trim();
      // filter through the requests to find wanted requests
      this.tableData = this.tempData.filter((item) => {
        if (item.name.toLowerCase().includes(keyword)) {
          return item;
        }
      });
      // update the tableData that's required to render the table on the webpage
      this.$root.$refs.history_component.tableData = this.tableData;
    },
  },
};
</script>
