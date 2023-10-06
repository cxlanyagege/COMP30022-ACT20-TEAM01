<!-- written by Lanruo Su -->
<template>
  <div class="app-container">
    <!-- Authority Table -->
    <el-table
      v-loading="listLoading"
      :data="tableData"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
    >
      <el-table-column prop="position" label="Staff Position" :width="170"></el-table-column>
      <el-table-column prop="name" label="Staff Name" :width="179"></el-table-column>
      <el-table-column prop="email" label="Staff Email" :width="296"></el-table-column>
      <el-table-column prop="authority" label="Authority" :width="640">
        <template slot-scope="{ row }">
          <el-row>
            <el-col :span="4" style="width: 26%;">
              <el-checkbox v-model="row.authority.Assignment" label="Assignment" :disabled="isAssignmentDisabled" @change="handleCheckboxChange(row, 0) ">Assignment</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox v-model="row.authority.Quiz" label="Quiz" :disabled="isQuizDisabled" @change="handleCheckboxChange(row, 1)">Quiz</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox v-model="row.authority.Exam" label="Exam" :disabled="isExamDisabled" @change="handleCheckboxChange(row, 2)">Exam</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 20%;">
              <el-checkbox v-model="row.authority.Personal" label="Personal" :disabled="isPersonalDisabled" @change="handleCheckboxChange(row, 3)">Personal</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox v-model="row.authority.Others" label="Others" :disabled="isOthersDisabled" @change="handleCheckboxChange(row, 4)">Others</el-checkbox>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { EventBus } from '@/views/requestAuthority/index.vue'

export default {
  methods: {
    // 在组件创建时监听事件总线的事件
    created() {
      EventBus.$on('assignment-switch-changed', (value) => {
        // 根据接收到的状态信息来更新禁用状态
        this.isAssignmentDisabled = value === 'No'
      })
    },
    handleCheckboxChange(row, index) {
      if (row.authority === undefined) {
        row.authority = [] // 如果authority字段尚未定义，初始化为一个空数组
      }
      if (row.authority.includes(row.authority[index])) {
        // 如果已包含标签值，表示取消勾选，从数组中移除
        row.authority.splice(row.authority.indexOf(row.authority[index]), 1)
      } else {
        // 否则，表示勾选，添加标签值到数组
        row.authority.push(row.authority[index])
      }
      // 执行其他操作或更新数据
      console.log(`勾选后的authority值: ${row.authority}`)
    },
    // 根据 views/requestAuthority/index.vue 组件的数据来禁用/启用复选框
    updateAssignmentDisabled() {
      this.isAssignmentDisabled = !this.requestAuthorityData.tableData[0].Assignment
    },
    mounted() {
    // 在组件挂载后调用更新禁用状态的方法
      this.updateAssignmentDisabled()
    }
  },

  computed: {
    // 获取 views/requestAuthority/index.vue 组件的数据
    requestAuthorityData() {
      return this.$refs.requestAuthority.$data
    }
  },

  data() {
    return {
      tableData: [
        {
          position: 'Coordinator',
          name: 'William Smith',
          email: 'williams@unimelb.edu.au',
          authority: ['Yes']
        },

        {
          position: 'Lecturer',
          name: 'David Johnson',
          email: 'davidj2@unimelb.edu.au',
          authority: ['Yes']
        },

        {
          position: 'Head Tutor',
          name: 'James Wilson',
          email: 'jamesw@unimelb.edu.au',
          authority: ['Yes']
        },

        {
          position: 'Tutor',
          name: 'Tom Harris',
          email: 'tomh6@unimelb.edu.au',
          authority: ['Yes']
        }

      ],
      listLoading: false,
      isAssignmentDisabled: false, // 用于控制禁用状态
      isQuizDisabled: false,
      isExamDisabled: false, // 用于控制禁用状态
      isPersonalDisabled: false,
      isOthersDisabled: false
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>
