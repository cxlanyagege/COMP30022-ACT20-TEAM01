<template>
  <el-dialog
    title="Approve Request"
    :visible="dialogVisible" 
    @update:visible="updateVisible"
    width="50%"
  >
    <el-form ref="form" :model="form" label-width="180px">
      <el-form-item label="Confirmation Message" class="custom-label-size">
        <el-input v-model="form.message" placeholder="Enter confirmation message" ></el-input>
      </el-form-item>
      <el-form-item label="Confirmation Time" class="custom-label-size" v-if="shouldShowDate">
        <el-date-picker v-model="form.date" type="datetime" placeholder="Select date and time" ></el-date-picker>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleCancel">Cancel</el-button>
      <el-button type="primary" @click="handleConfirm">Confirm</el-button>
    </div>
  </el-dialog>
</template>

<style scoped>
.custom-label-size .el-form-item__label {
    font-size: 15px; 
}
</style>

<script>
export default {
  props: {
    visible: Boolean,
    form: Object,
    requestType: String
  },
  data() {
    return {
      dialogVisible: this.visible // 初始化为传递的 prop 值
    }
  },
  computed: {
    shouldShowDate() {
      return this.requestType === 'Extension'  // Show the date input only if the request type is 'Extension'
    }
  },
  methods: {
    handleCancel() {
      this.dialogVisible = false
    },
    handleConfirm() {
      this.$emit('confirm')
    },
    updateVisible(value) {
      this.dialogVisible = value // 当父组件更新时，同步更新局部变量
      this.$emit('update:visible', value) // 向父组件发送事件通知
    }
  }
}
</script>
