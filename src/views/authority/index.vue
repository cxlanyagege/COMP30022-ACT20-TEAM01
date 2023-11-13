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
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="position" label="Staff Position" :width="170" />
      <el-table-column prop="name" label="Staff Name" :width="179" />
      <el-table-column prop="email" label="Staff Email" :width="296" />
      <el-table-column prop="authority" label="Authority" :width="640">
        <template slot-scope="{ row }">
          <el-row>
            <el-col :span="4" style="width: 26%;">
              <el-checkbox
                v-model="row.authority[0]"
                :disabled="rowIndex !== 0 && !row.authority[0]"
                checked="true"
                label="Assignment"
                :width="150"
                @change="handleCheckboxChange(row, 0)"
              >Assignment</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox
                v-model="row.authority[1]"
                :disabled="rowIndex !== 0 && !row.authority[1]"
                checked="true"
                label="Quiz"
                @change="handleCheckboxChange(row, 1)"
              >Quiz</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox
                v-model="row.authority[2]"
                :disabled="rowIndex !== 0 && !row.authority[2]"
                checked="true"
                label="Exam"
                @change="handleCheckboxChange(row, 2)"
              >Exam</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 20%;">
              <el-checkbox
                v-model="row.authority[3]"
                :disabled="rowIndex !== 0 && !row.authority[3]"
                checked="true"
                label="Personal"
                @change="handleCheckboxChange(row, 3)"
              >Personal</el-checkbox>
            </el-col>
            <el-col :span="4" style="width: 18%;">
              <el-checkbox
                v-model="row.authority[4]"
                :disabled="rowIndex !== 0 && !row.authority[4]"
                checked="true"
                label="Other"
                @change="handleCheckboxChange(row, 4)"
              >Other</el-checkbox>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

  <style>
    .el-table .success-row {
      background: #f0f9eb
    }
  </style>

<script>

export default {
  data() {
    return {
      tableData: [
        {
          position: 'Coordinator',
          name: 'William Smith',
          email: 'williams@unimelb.edu.au',
          authority: []
        },

        {
          position: 'Lecturer',
          name: 'David Johnson',
          email: 'davidj2@unimelb.edu.au',
          authority: []
        },

        {
          position: 'Head Tutor',
          name: 'James Wilson',
          email: 'jamesw@unimelb.edu.au',
          authority: []
        },

        {
          position: 'Tutor',
          name: 'Tom Harris',
          email: 'tomh6@unimelb.edu.au',
          authority: []
        }

      ],
      listLoading: false
    }
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 0) {
        return 'success-row'
      }
      return ''
    },
    handleCheckboxChange(row, index) {
      const isChecked = row.authority[index]
      // If it's the first row, update other rows
      if (row === this.tableData[0]) {
        for (let i = 1; i < this.tableData.length; i++) {
          this.$set(this.tableData[i].authority, index, isChecked)
        }
      } else {
        // For other rows, update the first row if necessary
        if (isChecked) {
          // Add to the first row if not already present
          if (this.tableData[0].authority.indexOf(index) === -1) {
            this.tableData[0].authority.push(index)
          }
        } else {
          // Remove from the first row if present
          const indexInFirstRow = this.tableData[0].authority.indexOf(index)
          if (indexInFirstRow !== -1) {
            this.tableData[0].authority.splice(indexInFirstRow, 1)
          }
        }
      }
    }
  }
}
</script>

  <style scoped>
  .line{
    text-align: center;
  }
  </style>
