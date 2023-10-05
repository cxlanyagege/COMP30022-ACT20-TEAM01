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
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="Student ID">
              <span>1266504</span>
            </el-form-item>
            <br>
            <el-form-item label="Detail">
              <span>一堆文字bilibaldfjsdnfdjvnsdvksdnva</span>
            </el-form-item>
            <br>
            <el-form-item label="File">
              <span>{{ props.row.shop }}</span>
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
            type="danger"
            size="mini"
            @click="deleteRow(scope.row)"
            v-if="scope.row.status === 'WAITING'"
            class="small-button"
          >
            DELETE
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          idNo: "123476",
          type: "Personal",
          name: "AAP Certification",
          status: "APPROVED",
        },
        {
          idNo: "123456",
          type: "Assignment",
          name: "Assignment 2 Extension",
          status: "WAITING",
        },
        {
          idNo: "123466",
          type: "Test",
          name: "Quiz Remark",
          status: "WAITING",
        },
        {
          idNo: "123486",
          type: "Personal",
          name: "Absence of Workshop",
          status: "REJECTED",
        },
      ],
    };
  },
  computed: {
    sortedTableData() {
      const waitingRows = this.tableData.filter(
        (row) => row.status === "WAITING"
      );
      const nonWaitingRows = this.tableData.filter(
        (row) => row.status !== "WAITING"
      );
      return waitingRows.concat(nonWaitingRows);
    },
  },
  methods: {
    deleteRow(row) {
      const index = this.tableData.indexOf(row);
      if (index !== -1) {
        this.tableData.splice(index, 1);
      }
    },
    addNewRequest() {
      const newRequest = {
        idNo: "123XYZ", // 你可以设置适当的值
        type: "Assignment", // 你可以设置适当的值
        name: "New Request", // 你可以设置适当的值
        status: "WAITING",
      };
      this.tableData.unshift(newRequest);
    },
  },
};
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
