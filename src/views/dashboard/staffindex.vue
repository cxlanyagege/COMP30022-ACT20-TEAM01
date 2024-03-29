<template>
  <div>
    <Filterrequest @filter="applyFilter" /> <!-- 监听FilterComponent的filter事件 -->
    <Table :table-data="filteredData" @flagClick="handleFlagClick" /> <!-- 监听TableComponent的flagClick事件 -->
  </div>
</template>

<script>
import Filterrequest from '@/components/Filterrequest/index.vue'
import Table from '@/components/table/index.vue'
import { getSubjectRequests, updateFlagStatus } from '@/api/api'
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
      filteredTable: []
    }
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
  created() {
    // initialize the filtering to "All"
    this.applyFilter('All')
  },
  // written by Dennis Wang
  mounted() {
    this.getRequests()
  },
  methods: {
    // written by Dennis Wang, get all the requests
    getRequests() {
      console.log('Get all the requests related to the subject')
      getSubjectRequests(this.$store.getters.subjectId).then((res) => {
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
      })
    },
    // written by Dennis Wang
    convertUrlWithPrefix(url) {
      return attachmentBaseURL + url
    },
    applyFilter(filterCondition) {
      // 处理筛选的逻辑
      this.filterCondition = filterCondition
      if (filterCondition === 'Assignment') {
        this.filteredTable = this.tableData.filter(item => item.reqType === 'Assignment')
      } else if (filterCondition === 'Quiz') {
        this.filteredTable = this.tableData.filter(item => item.reqType === 'Quiz')
      } else if (filterCondition === 'Exam') {
        this.filteredTable = this.tableData.filter(item => item.reqType === 'Exam')
      } else if (filterCondition === 'Personal') {
        this.filteredTable = this.tableData.filter(item => item.reqType === 'Personal')
      } else if (filterCondition === 'Others') {
        this.filteredTable = this.tableData.filter(item => item.reqType === 'Others')
      } else {
        this.filteredTable = this.tableData
      }
    },
    // modified by Dennis Wang, update the flag status in the backend
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
