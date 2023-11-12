<template>
  <div>
    <Filterrequest @filter="applyFilter" /> <!-- 监听FilterComponent的filter事件 -->
    <Table :tableData="filteredData" @flagClick="handleFlagClick" /> <!-- 监听TableComponent的flagClick事件 -->
  </div>
</template>

<script>
import Filterrequest from '@/components/Filterrequest/index'
import Table from '@/components/Table/index'
import { EventBus } from '@/utils/EventBus'

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
        id: '1266708',
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
      }, {
        id: '1266709',
        subID: 'COMP20008',
        appDate: '01/01/2024',
        reqType: 'Assignment',
        taskType: 'N/A',
        reqName: 'extention for A1',
        reqDetail: 'Hi, I would like to apply for extention due to my illness',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Others'
      }, {
        id: '1266710',
        subID: 'COMP30023',
        appDate: '01/01/2024',
        reqType: 'Assignment',
        taskType: 'N/A',
        reqName: 'extention for A1',
        reqDetail: 'Hi, I would like to apply for an extention',
        files: 'N/A',
        status: 'UNASSESSED',
        flagClicked: false,
        decision: 'Approved',
        morespecific: 'Others'
      }],
      filterCondition: 'All',
      filteredData: [],
      selectedSubID: null
    }
  },
  created() {
    // initialize the filtering to "All"
    this.applyFilter('All')
    // 监听 EventBus 上的 filter 事件
    EventBus.$on('filter', (subID) => {
      if (this.selectedSubID === subID) {
        // 如果点击的是当前已选中的 subID，则切换为初始未被筛选的全部请求
        this.selectedSubID = null
        this.applyFilter('All')
      } else {
        // 否则，切换显示当前 subID 的全部请求
        this.selectedSubID = subID
        this.applyFilter('All')
      }
      this.applyFilter(this.filterCondition)
    })
  },
  methods: {
    filterRequests(subID) {
      this.selectedSubID = subID
      this.applyFilter('All')
    },
    applyFilter(filterCondition) {
      // 处理筛选的逻辑
      this.filterCondition = 'All'
      // 筛选所选的 subID
      if (this.selectedSubID === null || this.selectedSubID === 'All') {
        this.filteredData = this.tableData
      } else {
        if (filterCondition === 'All') {
          this.filteredData = this.tableData.filter(item => item.subID === this.selectedSubID)
        } else {
          this.filteredData = this.tableData.filter(item => item.subID === this.selectedSubID && item.reqType === filterCondition)
        }
      }
    },
    handleFlagClick(row) {
      if (!row.flagClicked) {
        // mark the clicked flag as clicked
        this.flagClicked = !this.flagClicked
        // move the current row to the front
        this.tableData = [row, ...this.tableData.filter(item => item !== row)]
        // refresh the data
        this.applyFilter(this.filterCondition)
      }
    }
  }
}
</script>
