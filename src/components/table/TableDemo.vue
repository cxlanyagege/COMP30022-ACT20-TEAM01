<!-- The component code was written by Yawen Luo. Dennis Wang write
  all the methods related to making api calls to the backend.
  The following code is used to build the component request table elements. -->

<template>
  <div class="app-container">
    <el-table
      :data="sortedTableData"
      stripe
      style="width: 100%"
      max-height="600"
      :fit="true"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form
            label-position="left"
            inline
            class="demo-table-expand"
            label-width="150px"
          >
            <el-form-item label="Submission Date">
              <span>{{ props.row.date }}</span>
            </el-form-item>
            <br>
            <template v-if="props.row.workType != null">
              <el-form-item label="Request specific">
                <span>{{ props.row.workType }}</span>
              </el-form-item>
            </template>
            <br>
            <el-form-item label="Detail">
              <span>{{ props.row.detail }}</span>
            </el-form-item>
            <br>
            <el-form-item label="File">
              <template v-if="props.row.fileList.length > 0">
                <li v-for="file in props.row.fileList" :key="file.uid">
                  <a :href="file.url" target="_blank">
                    <span style="color: rgb(0, 85, 255)">{{
                      file.url.substr(uploadURL.length, file.url.length)
                    }}</span>
                  </a>
                </li>
              </template>
              <template v-else> No file uploaded </template>
            </el-form-item>
            <br>
            <el-form-item label="Addition">
              <span>{{ props.row.shopId }}</span>
            </el-form-item>
            <br>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="idNo" label="ID NO." width="110" />
      <el-table-column prop="type" label="TYPE" width="150" />
      <el-table-column prop="name" label="NAME" width="260" />
      <el-table-column prop="status" label="STATUS">
        <template slot-scope="{ row }">
          <el-tag
            v-if="row.status === 'WAITING'"
            type="warning"
            class="small-tag"
          >
            {{ row.status }}
          </el-tag>
          <el-tag
            v-else-if="row.status === 'APPROVED'"
            type="success"
            class="small-tag"
          >
            {{ row.status }}
          </el-tag>
          <el-tag
            v-else-if="row.status === 'REJECTED'"
            type="danger"
            class="small-tag"
          >
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="ACTION">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status === 'WAITING'"
            type="danger"
            size="mini"
            class="small-button"
            @click="deleteRow(scope.row)"
          >
            DELETE
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { deleteRequest, getRequests } from '@/api/request'
import { EventBus } from '@/utils/event-bus'
import { attachmentBaseURL, uploadURL } from '@/config/config'

export default {
  data() {
    return {
      tableData: [],
      uploadURL: uploadURL
    }
  },
  computed: {
    sortedTableData() {
      const waitingRows = this.tableData.filter(
        (row) => row.status === 'WAITING'
      )
      waitingRows.sort((a, b) => {
        const dateA = new Date(a.date)
        const dateB = new Date(b.date)
        return dateB - dateA
      })
      const nonWaitingRows = this.tableData.filter(
        (row) => row.status !== 'WAITING'
      )
      nonWaitingRows.sort((a, b) => {
        const dateA = new Date(a.date)
        const dateB = new Date(b.date)
        return dateB - dateA
      })
      return waitingRows.concat(nonWaitingRows)
    }
  },
  mounted() {
    this.updateRequests().then(() => {
      EventBus.$emit('copy-data-event', this.tableData)
    })
  },
  created() {
    EventBus.$on('update-data', (data) => {
      this.tableData = data
    })
    EventBus.$on('add-request', (data) => {
      this.addNewRequest(data)
    })
  },
  methods: {
    updateRequests() {
      console.log('handle requests')
      const userId = this.$store.getters.id
      const request1 = getRequests(userId, null).then((res) => {
        console.log(res.data)
        if (res.data.data.length === 0) {
          this.tableData = []
        } else {
          const requestData = res.data.data.map((record) => {
            return {
              idNo: record.requestId,
              type: record.requestType,
              name: record.requestName,
              workType: record.workType,
              status: record.status,
              detail: record.description,
              fileList: record.attachments.map((item) => {
                return {
                  uid: item.attachmentId,
                  url: this.convertUrlWithPrefix(item.url)
                }
              }),
              date: record.submissionDate
            }
          })
          this.tableData = requestData
        }
      })
      return Promise.all([request1])
    },

    deleteRow(row) {
      deleteRequest(row.idNo).then(() => {
        const index = this.tableData.indexOf(row)
        if (index !== -1) {
          this.tableData.splice(index, 1)
        }
      })
    },

    convertUrlWithPrefix(url) {
      return attachmentBaseURL + url
    },

    addNewRequest(data) {
      const newRequest = {
        idNo: data.requestId,
        type: data.requestType,
        workType: data.workType,
        name: data.requestName,
        status: data.status,
        detail: data.description,
        date: data.submissionDate,
        fileList: data.attachments.map((item) => {
          return {
            uid: item.attachmentId,
            url: this.convertUrlWithPrefix(item.url)
          }
        })
      }
      this.tableData.unshift(newRequest)
    }
  }
}
</script>

<style>
.small-button {
  padding: 7px 7px;
}

.small-tag {
  padding: 1px 4.5px;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
