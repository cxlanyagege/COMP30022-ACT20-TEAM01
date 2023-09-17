<template>
  <div class="app-container">
    <!--     Filter Function
    <el-input v-model="filterText" placeholder="Filter keyword"/>
    <HistoryTable :data="tableData" /> 
    -->

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

      <el-table-column prop="position" label="Staff Position" ></el-table-column>
      <el-table-column prop="name" label="Staff Name" ></el-table-column>
      <el-table-column prop="email" label="Staff Email" ></el-table-column>
      <el-table-column prop="authority" label="Authority" >
 
        <template slot-scope="{ row }">
          <el-switch v-model="row.authority" :active-value="true" :inactive-value="false" active-text="Yes" inactive-text="No"></el-switch>
        </template>

      </el-table-column>
    </el-table>

  </div>
</template>

<script>
// import AuthorityTable from ''

export default {
  
  data() {
    return {
      tableData: [
        {
          position: 'Coordinator',
          name: 'William Smith',
          email: 'williams@unimelb.edu.au',
          authority: 'Yes'
        },

        {
          position: 'Lecturer',
          name: 'David Johnson',
          email: 'davidj2@unimelb.edu.au',
          authority: 'Yes'
        },
        
        {
          position: 'Head Tutor',
          name: 'James Wilson',
          email: 'jamesw@unimelb.edu.au',
          authority: 'Yes'
        },

        {
          position: 'Tutor',
          name: 'Tom Harris',
          email: 'tomh6@unimelb.edu.au',        
          authority: 'Yes'
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

