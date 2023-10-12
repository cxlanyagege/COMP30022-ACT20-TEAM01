<template>
  <el-dialog
    title="Approve Request"
    :visible.sync="visible"
    width="50%"
  >
    <el-form ref="form" :model="form" label-width="180px">
      <el-form-item label="Confirmation Message" class="custom-label-size">
        <el-input v-model="form.message" placeholder="Enter confirmation message" ></el-input>
      </el-form-item>
      <el-form-item label="Confirmation Time" class="custom-label-size">
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
    form: Object
  },
  methods: {
    handleCancel() {
      this.$emit('update:visible', false);
    },
    handleConfirm() {
      this.$emit('confirm');
    }
  }
}
</script>
