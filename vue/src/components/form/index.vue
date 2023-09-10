<template>
  <div class="app-container">
    <el-form ref="form" :label-position="labelPosition" :model="form" label-width="120px">
      <el-form-item label="Student ID">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Subject Code">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Request Type">
        <el-select v-model="form.region" placeholder="Please select type">
          <el-option label="Extension" value="Extension" />
          <el-option label="Personal" value="Personal" />
        </el-select>
      </el-form-item>
      <el-form-item v-if="form.region === 'Extension'" label="Task Type">
        <el-radio-group v-model="form.type">
          <el-radio label="Individual">Individual</el-radio>
          <el-radio label="Group">Group</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-if="form.region === 'Extension'" label="Task Name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Email Alerts">
        <el-switch v-model="form.delivery" />
      </el-form-item>
      <el-form-item label="Request Detail">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>

      <el-form-item label="Upload File">
        <el-upload
          class="upload-demo"
          action="/your-upload-api-endpoint"
          :on-success="handleSuccess"
          :on-error="handleError"
          :accept="'*'"
        >
          <el-button size="small" type="primary">Click to Upload</el-button>
          <div slot="tip" class="el-upload__tip">You can upload any file format</div>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      labelPosition: 'left',
      form: {
        studentId: '',
        subjectCode: '',
        region: '',
        showAdditionalOptions: false,
        type: '',
        name: '',
        time: '',
        email: false,
        detail: '',
        file: null
      }
    }
  },
  watch: {
    'form.region'(newValue) {
      // 根据选择的 "Request Type" 更新 showAdditionalOptions 的值
      this.form.showAdditionalOptions = newValue === 'extension'
    }
  },
  methods: {
    onSubmit() {
      // 创建一个 formData 对象，根据用户选择的动态包含 type 和 name 字段
      const formData = {
        studentId: this.form.studentId,
        subjectCode: this.form.subjectCode,
        region: this.form.region,
        time: this.form.time,
        email: this.form.email,
        detail: this.form.detail,
        file: this.form.file
      }

      // 如果 showAdditionalOptions 为 true，则包含 type 和 name 字段
      if (this.form.showAdditionalOptions) {
        formData.type = this.form.type
        formData.name = this.form.name
      }

      // 现在可以将 formData 发送到服务器或执行其他处理
      console.log('Form submitted with data:', formData)

      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    },
    // 处理文件上传成功
    handleSuccess(response, file, fileList) {
      this.form.file = response.data.fileUrl // 将文件的 URL 存储在表单数据中
      this.$message.success('File uploaded successfully')
    },
    handleError(err) {
      if (err && err.response && err.response.data) {
        const errorMessage = err.response.data.error // 假设后端响应中包含一个 "error" 字段
        this.$message.error('File upload failed: ' + errorMessage)
      } else {
        this.$message.error('File upload failed')
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
