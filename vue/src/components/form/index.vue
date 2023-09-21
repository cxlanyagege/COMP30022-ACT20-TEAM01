<template>
  <div class="app-container">
    <el-form ref="form" :label-position="labelPosition" :model="form" label-width="120px">
      <el-form-item label="Student ID">
        <el-input v-model="form.studentId" />
      </el-form-item>
      <el-form-item label="Request Type">
        <el-select v-model="form.region" placeholder="Please select type">
          <el-option label="Assignment" value="Assignment" />
          <el-option label="Test" value="Test" />
          <el-option label="Exam" value="Exam" />
          <el-option label="Personal" value="Personal" />
          <el-option label="Others" value="Others" />
        </el-select>
      </el-form-item>
      <el-form-item v-if="form.region === 'Test'|| form.region === 'Assignment' || form.region === 'Exam'" label="Task Type">
        <el-radio-group v-model="form.type">
          <el-radio label="Individual">Individual</el-radio>
          <el-radio label="Group">Group</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-if="form.type === 'Group'" label="Teammate">
        <div v-for="(email, index) in form.teammates" :key="index">
          <el-input v-model="form.teammates[index]" />
          <el-button @click="removeTeammate(index)">Remove</el-button>
        </div>
        <el-button @click="addTeammate">Add Student Email</el-button>
      </el-form-item>
      <el-form-item label="Request Name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Email Alerts">
        <el-switch v-model="form.email" />
      </el-form-item>
      <el-form-item label="Request Detail">
        <el-input v-model="form.detail" type="textarea" />
      </el-form-item>

      <el-form-item label="Upload File">
        <el-upload
          v-model:file-list="form.fileList"
          class="upload-demo"
          :action="uploadURL"
          :on-success="handleSuccess"
          :on-error="handleError"
          :on-remove="handleRemove"
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
import {getRequests, addRequest, deleteRequest} from '@/api/request'
import listTable from '@/components/table/index.vue'
import {attachmentBaseURL, uploadURL} from '@/config/config'

export default {
  components: {
    listTable
  },
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
        fileList: [],
        teammates: [] // 存储队友的电子邮件地址
      },
      uploadURL: uploadURL
    }
  },
  watch: {
    'form.region'(newValue) {
      // 根据选择的 "Request Type" 更新 showAdditionalOptions 的值
      this.form.showAdditionalOptions = newValue === 'Task'
    }
  },
  methods: {
    onSubmit() {
      // 创建一个 formData 对象，根据用户选择的动态包含 type 和 name 字段
      const currentDate = new Date();

      const formData = {
        studentId: this.form.studentId,
        subjectId: this.form.subjectCode,
        requestType: this.form.region,
        submissionDate: currentDate,
        // email: this.form.email,
        description: this.form.detail,
        fileList: this.form.fileList,
        requestName: this.form.name
      }

      // 如果 showAdditionalOptions 为 true，则包含 type 和 name 字段
      if (this.form.showAdditionalOptions) {
        formData.type = this.form.type
      }

      // 包含队友的电子邮件地址
      formData.teammates = this.form.teammates

      let param = {
        description: formData.description,
        studentId: formData.studentId,
        subjectId: "COMP10012", // 需要从lms上面获取
        submissionDate: formData.submissionDate,
        requestType: formData.requestType,
        requestName: formData.requestName,
        attachments: formData.fileList.map(item => {
          return {url: this.convertUrlWithoutPrefix(item.url)}
        })
      }
      addRequest(param).then(res => {
        console.log(res.data);
        if (res.data.code == 0){
          this.$message(res.data.msg)
          this.$root.$refs.table_component.updateRequests(this.$root.$refs.table_component.pageNum,
                            this.$root.$refs.table_component.pageSize);
        } else {
          this.$message("Fail to submit!")
        }
      })

      // 现在可以将 formData 发送到服务器或执行其他处理
      console.log('Form submitted with data:', param)

      this.$message('Submit successful!')
    },
    onCancel() {
      this.$message({
        message: 'Cancel!',
        type: 'warning'
      })
    },
    convertUrlWithPrefix(url) {
      return attachmentBaseURL + url;
    },
    convertUrlWithoutPrefix(url){
      return url.substr(attachmentBaseURL.length, url.length);
    },
    // 处理文件上传成功
    handleSuccess(response, file, fileList) {
      // console.log(fileList)
      // this.form.file = response.data.fileUrl // 将文件的 URL 存储在表单数据中
      this.form.fileList.push({
        uid: file.raw.uid,
        url: this.convertUrlWithPrefix(response.data)
      })
      console.log(this.form.fileList)
      this.$message.success('File uploaded successfully')
    },
    handleError(err) {
      if (err && err.response && err.response.data) {
        const errorMessage = err.response.data.error // 假设后端响应中包含一个 "error" 字段
        this.$message.error('File upload failed: ' + errorMessage)
      } else {
        this.$message.error('File upload failed')
      }
    },
    handleRemove(file, files){
      // console.log(file, files)
      this.form.fileList = this.form.fileList.filter(item => item.uid != file.uid);
      console.log(this.form.fileList)
    },
    // 添加队友的电子邮件地址输入框
    addTeammate() {
      this.form.teammates.push('')
    },
    // 删除队友的电子邮件地址输入框
    removeTeammate(index) {
      this.form.teammates.splice(index, 1)
    }
  }
}
</script>
