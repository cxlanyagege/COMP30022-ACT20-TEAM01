<template>
  <div>
    <Filterrequest @filter="applyFilter" /> <!-- 监听FilterComponent的filter事件 -->
    <Table :tableData="tableData" @flagClick="handleFlagClick" /> <!-- 监听TableComponent的flagClick事件 -->
  </div>
</template>

<script>
import Filterrequest from '@/components/Filterrequest/index';
import Table from '@/components/Table/index';

export default {
  components: {
    Filterrequest,
    Table
  },
  
  methods: {
    applyFilter(filterCondition) {
      // 过滤数据的逻辑
      // 更新filteredData
    },
    handleFlagClick(row) {
      // flag点击的逻辑
    }
  }
}
</script>
