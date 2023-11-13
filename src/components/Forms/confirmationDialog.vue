<template>
  <el-dialog
    title="Approve Request"
    :visible.sync="visible"
    width="50%"
    :show-close="false"
  >
    <el-form ref="form" :model="form" label-width="180px">
      <el-form-item label="Confirmation Message" class="custom-label-size">
        <el-input v-model="form.message" placeholder="Enter confirmation message" />
      </el-form-item>
      <el-form-item v-if="shouldShowDate" label="Confirmation Time" class="custom-label-size">
        <el-date-picker v-model="form.date" type="datetime" placeholder="Select date and time" />
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
    form: {
      type: Object,
      default: () => ({})
    },
    requestType: {
      type: String,
      default: ''
    }
  },
  computed: {
    shouldShowDate() {
      // Show the date input only if the request type is 'Extension'
      return this.requestType === 'Extension'
    }
  },
  methods: {
    handleCancel() {
      this.$emit('update:visible', false)
    },
    handleConfirm() {
      this.$emit('confirm')
    }
  }
}
</script>
