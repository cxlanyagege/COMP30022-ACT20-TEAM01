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
import { EventBus } from '@/views/tutorAuthority/index.vue'

export default {
  methods: {
    handleSwitchChange(row, column, value) {
       // 更新开关状态
      row[column] = value
      // 触发事件，传递状态信息
      EventBus.$emit('assignment-switch-changed', value)
    }
  },

  data() {
    return {
      tableData: [
        {
          Assignment: true,
          Quiz: false,
          Exam: true,
          Personal: true,
          Others: false
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
