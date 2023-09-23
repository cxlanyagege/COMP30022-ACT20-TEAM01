<template>
  <div class="custom-button-group">
    <el-button
      class="custom-button"
      @click="showForm"
    >+ Create</el-button>
    <el-dialog :visible.sync="formVisible" width="65%" :modal="false">
      <strong style="font-size: 20px;" title="COMP30022 Request Form">COMP30022 Request Form</strong>
      <RequestForm @form-submitted="submitForm" />
    </el-dialog>

  </div>
</template>

<script>
import RequestForm from '@/components/form/index.vue'

export default {
  components: {
    RequestForm
  },
  data() {
    return {
      formVisible: false,
    }
  },
  methods: {
    // ADDED BY DENNIS WANG
    showForm() {
      // reset everything in the form everytime the form is opened
      this.formVisible = true
      this.$root.$refs.form_component.isCheck = false
      this.$root.$refs.form_component.form.studentId = '';
      // this.$root.$refs.form_component.form.subjectCode = '';
      this.$root.$refs.form_component.form.region = '';
      this.$root.$refs.form_component.form.type = '';
      this.$root.$refs.form_component.form.name = '';
      this.$root.$refs.form_component.form.detail = '';
      this.$root.$refs.form_component.form.fileList = [];
      this.$root.$refs.form_component.form.teammates = [];
      this.$root.$refs.form_component.form.email = false;
      this.$root.$refs.form_component.form.showAdditionalOptions = false;
    },
    submitForm(formData) {
      // 这里处理提交表单后的逻辑，formData 包含表单数据
      // 例如，您可以将数据发送到服务器
      console.log('Form submitted with data:', formData)

      // 使用 $nextTick 来确保在关闭表单后再隐藏
      this.$nextTick(() => {
        this.formVisible = false
      })
    }
  },
  // WRITTEN BY DENNIS WANG
  created() {
    // set componenent name
    this.$root.$refs.button_component = this;
  },
}
</script>

<style scoped>
.custom-button-group {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80px;
  margin-top: 25px;
  margin-bottom: 25px;
}

.custom-button {
  padding: 18px 38px;
  font-size: 16px;
  font-weight: bold;
}
</style>
