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
      <el-table-column prop="Assignment" label="Assignment" style="width: 26%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Assignment" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" @change="handleSwitchChange(row, 'Assignment')"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="Quiz" label="Quiz" style="width: 18%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Quiz" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" @change="handleSwitchChange(row, 'Quiz')"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="Exam" label="Exam" style="width: 18%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Exam" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" @change="handleSwitchChange(row, 'Exam')"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="Personal" label="Personal" style="width: 20%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Personal" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" @change="handleSwitchChange(row, 'Personal')"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="Others" label="Others" style="width: 18%">
        <template slot-scope="{ row }">
          <el-switch v-model="row.Others" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No" @change="handleSwitchChange(row, 'Others')"></el-switch>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
// import AuthorityTable from ''

export default {
  methods: {
    handleSwitchChange(row, column, value) {
      // 在这里可以根据需要更新row的值
      // 例如：如果value为'No'，可以将row中对应的数据更新为'No'，以便在tutorAuthority表格中禁用相关的Checkbox
      row[column.property] = value
    }
  },

  data() {
    return {
      tableData: [
        {
          Assignment: 'Yes',
          Quiz: 'Yes',
          Exam: 'Yes',
          Personal: 'Yes',
          Others: 'No'
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
