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
      style="width: 100%">

      <el-table-column prop="position" label="Staff Position" :width="170"></el-table-column>
      <el-table-column prop="name" label="Staff Name" :width="179"></el-table-column>
      <el-table-column prop="email" label="Staff Email" :width="296"></el-table-column>
      <el-table-column prop="authority" label="Authority" :width="640">
        <template slot-scope="{ row }">
          <el-row>
            <el-col :span="4" style="width: 26%;">
              <el-checkbox v-model="row.authority[0]" label="Assignment" :width ="150" @change="handleCheckboxChange(row, 0)">Assignment</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox v-model="row.authority[1]" label="Quiz" @change="handleCheckboxChange(row, 1)">Quiz</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox v-model="row.authority[2]" label="Exam" @change="handleCheckboxChange(row, 2)">Exam</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 20%;">
              <el-checkbox v-model="row.authority[3]" label="Personal" @change="handleCheckboxChange(row, 3)">Personal</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox v-model="row.authority[4]" label="Other" @change="handleCheckboxChange(row, 4)">Other</el-checkbox>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
// import AuthorityTable from ''

export default {

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
      listLoading: false
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>
