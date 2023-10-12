<template>
  <div>
    <Filterrequest @filter="applyFilter" /> <!-- 监听FilterComponent的filter事件 -->
    <Table :tableData="filteredData" @flagClick="handleFlagClick" /> <!-- 监听TableComponent的flagClick事件 -->
  </div>
</template>

<script>
import Filterrequest from '@/components/Filterrequest/index'
import Table from '@/components/Table/index'

export default {
  components: {
    Filterrequest,
    Table
  },
  data() {
    return {
      tableData: [{
        id: '1266704',
        subID: 'COMP30022',
        appDate: '01/01/2024',
        AAPs: 'Yes',
        reqType: 'Assignment',
        taskType: 'Individual',
        reqName: 'A1 extension',
        reqDetail: 'Hi, I would like to apply for A1 extension',
        files: 'N/A',
        status: 'APPROVE',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Extension'
      }, {
        id: '1266705',
        subID: 'COMP30026',
        appDate: '01/01/2024',
        reqType: 'Test',
        taskType: 'N/A',
        reqName: 'MST extension',
        reqDetail: 'HI, I would like to apply for MST extension',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Extension'
      }, {
        id: '1266706',
        subID: 'COMP30023',
        appDate: '01/01/2024',
        reqType: 'Exam',
        taskType: 'N/A',
        reqName: 'Exam remark',
        reqDetail: 'HI, I would like tp apply for remarking my exam',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Remark'
      }, {
        id: '1266704',
        subID: 'COMP20008',
        appDate: '01/01/2024',
        reqType: 'Others',
        taskType: 'N/A',
        reqName: 'AAP certification',
        reqDetail: 'Hi, I would like to ask about my AAP certification stuff',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Others'
      }],
      filterCondition: 'All',
      filteredData: []
    }
  },
  created() {
    // initialize the filtering to "All"
    this.applyFilter('All')
  },
  computed: {
    // 使用计算属性来根据筛选条件过滤数据
    filteredData() {
      if (this.filterCondition === 'All') {
        return this.tableData
      } else {
        return this.tableData.filter(item => item.reqType === this.filterCondition)
      }
    }
  },
  methods: {
    applyFilter(filterCondition) {
      // 处理筛选的逻辑
      this.filterCondition = filterCondition
      if (filterCondition === 'Assignment') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Assignment')
      } else if (filterCondition === 'Test') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Test')
      } else if (filterCondition === 'Exam') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Exam')
      } else if (filterCondition === 'Individual') {
        this.filteredData = this.tableData.filter(item => item.taskType === 'Individual')
      } else if (filterCondition === 'Others') {
        this.filteredData = this.tableData.filter(item => item.reqType === 'Others')
      } else {
        this.filteredData = this.tableData
      }
    },
    handleFlagClick(row) {
      if (!row.flagClicked) {
        // mark the clicked flag as clicked
        this.flagClicked = !this.flagClicked
        // move the current row to the front
        this.tableData = [row, ...this.tableData.filter(item => item !== row)]
      }
    }
  }
}
</script>
