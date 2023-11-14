<template>
  <div>
    <Filterrequest @filter="applyFilter" /> <!-- 监听FilterComponent的filter事件 -->
    <Table :table-data="filteredData" @flagClick="handleFlagClick" /> <!-- 监听TableComponent的flagClick事件 -->
  </div>
</template>

<script>
import Filterrequest from '@/components/Filterrequest/index'
import Table from '@/components/table/index'
import { EventBus } from '@/utils/EventBus'
import { getTutorRequests, updateFlagStatus } from '@/api/api'
import { attachmentBaseURL } from '@/config/config'

export default {
  components: {
    Filterrequest,
    Table
  },
  data() {
    return {
      tableData: [],
      filterCondition: 'All',
      filteredData: [],
      selectedSubID: null
    }
  },
  created() {
    // initialize the filtering to "All"
    this.applyFilter('All')
    this.getRequests()
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
    getRequests() {
      getTutorRequests(this.$store.getters.subjectId).then((res) => {
        console.log(res.data)
        if (res.data.data.length === 0) {
          this.tableData = []
        } else {
          const requestData = res.data.data.map((record) => {
            return {
              requestId: record.requestId,
              studentId: record.studentId,
              subID: record.subjectId,
              appDate: record.submissionDate,
              reqType: record.requestType,
              taskType: record.taskType,
              reqName: record.requestName,
              reqDetail: record.description,
              files: record.attachments.map((item) => {
                return {
                  uid: item.attachmentId,
                  url: this.convertUrlWithPrefix(item.url)
                }
              }),
              status: record.status === 'WAITING' ? 'UNASSESSED' : record.status,
              flagClicked: record.flagged,
              morespecific: record.workType
            }
          })
          this.tableData = requestData
        }
        console.log(this.tableData)
      })
    },
    // written by Dennis Wang
    convertUrlWithPrefix(url) {
      return attachmentBaseURL + url
    },
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
        row.flagClicked = true
        // move the current row to the front
        this.tableData = [row, ...this.tableData.filter(item => item !== row)]
        // refresh the data
        this.applyFilter(this.filterCondition)
      } else {
        row.flagClicked = false
        this.tableData = [...this.tableData.filter(item => item.flagClicked === true), ...this.tableData.filter(item => item.flagClicked === false)]
        this.applyFilter(this.filterCondition)
      }

      updateFlagStatus(row.requestId).then((res) => {
        console.log(res.data)
      })
    }
  }
}
</script>
