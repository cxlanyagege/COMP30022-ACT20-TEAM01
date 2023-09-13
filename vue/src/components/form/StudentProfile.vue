<template>
  <div class="student-profile">
    <div>
      <div class="profile-item">
        <label>Name: </label>
        <span>{{ studentInfo.name }}</span>
      </div>
      <div class="profile-item">
        <label>Student ID: </label>
        <span>{{ studentInfo.id }}</span>
      </div>
      <div class="profile-item">
        <label>Student Email: </label>
        <span>{{ studentInfo.email }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Beneficiaries: </label>
        <span>{{ studentInfo.aap }}</span>
      </div>
      <div class="profile-item">
        <label>AAP Documentation: </label>
        <span v-if="studentInfo.aap === 'Yes'">{{ studentInfo.aap }}</span>
        <span v-else>NA</span>
        
        <!-- 仅在 AAP Beneficiaries 为 "yes" 时才显示上传文档的选项 -->
        <el-upload
          v-if="studentInfo.aap === 'Yes'"
          class="upload-demo"
          action="/your-upload-api-endpoint"
          :on-success="handleSuccess"
          :on-error="handleError"
          :accept="'*'">
          <el-button size="small" type="primary">Click to Upload</el-button>
          <div slot="tip" class="el-upload__tip">You can upload any file format</div>
        </el-upload>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    studentInfo: {
      type: Object,
      required: true
    }
  },
  // 处理文件上传成功
  handleRemove(file, fileList) {
    console.log(file, fileList);
  },
  handlePreview(file) {
    console.log(file);
  },
  handleExceed(files, fileList) {
    this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
  },
  beforeRemove(file, fileList) {
    return this.$confirm(`确定移除 ${ file.name }？`);
  },
  handleError() {
      // 在这里处理错误逻辑
  }
}
</script>

<style scoped>
.student-profile {
  padding: 20px;
}

.profile-item {
  margin-bottom: 30px;
}

.label {
  font-weight: bold;
  margin-right: 5px;
}
</style>
