<!-- The component code was written by Yawen Luo, Dennis Wang modified the
     front-end and back-end interaction method code at a later stage. The following code is used
     to create the request form compoent. -->

<template>
  <div class="app-container">
    <el-form
      ref="form"
      :label-position="labelPosition"
      :model="form"
      :rules="rules"
      label-width="140px"
    >
      <el-form-item label="Request Type" required>
        <el-select
          v-model="form.region"
          placeholder="Please select type"
        >
          <el-option
            v-for="item in requestTypes"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item
        v-if="
          form.region === 'Quiz' ||
            form.region === 'Assignment' ||
            form.region === 'Exam'
        "
        label="Task Type"
        required
      >
        <el-radio-group v-model="form.type">
          <el-radio label="individual">Individual</el-radio>
          <el-radio label="Group">Group</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item
        v-if="
          form.type === 'individual' ||
            form.type === 'Group'
        "
        label="More specific? :)"
        required
      >
        <el-radio-group v-model="form.workType">
          <el-radio label="Extension">Extension</el-radio>
          <el-radio label="Remark">Remark</el-radio>
          <el-radio label="Other">Other</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-if="form.type === 'Group'" label="Teammate" required>
        <div v-for="(email, index) in form.teammates" :key="index">
          <el-input v-model="form.teammates[index]" />
          <el-button @click="removeTeammate(index)">Remove</el-button>
        </div>
        <el-button @click="addTeammate">Add Student Email</el-button>
      </el-form-item>
      <el-form-item label="Request Name">
        <el-input
          v-model="form.name"
          placeholder="e.g. Assignment 2 Extension/Remark/Others"
        />
      </el-form-item>
      <el-form-item label="Request Detail" required>
        <el-input v-model="form.detail" type="textarea" />
      </el-form-item>
      <el-form-item label="Upload File">
        <el-upload
          v-model="form.fileList"
          class="upload-demo"
          :action="uploadURL"
          :on-success="handleSuccess"
          :on-error="handleError"
          :on-remove="handleRemove"
          :accept="'*'"
        >
          <el-button size="small" type="primary">Click to Upload</el-button>
          <div slot="tip" class="el-upload__tip">
            You can upload any file format up to 5MB
          </div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="onSubmit"
        >Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addRequest, getSubjectDetail } from '@/api/request'
import { attachmentBaseURL, uploadURL } from '@/config/config'
import { EventBus } from '@/utils/event-bus'

export default {

  data() {
    return {
      requestTypes: [],
      labelPosition: 'left',
      form: {
        studentId: '',
        subjectCode: '',
        region: '',
        showAdditionalOptions: false,
        type: '',
        workType: '',
        name: '',
        detail: '',
        fileList: [],
        teammates: []
      },
      uploadURL: uploadURL,
      rules: {
        studentId: [
          {
            required: true,
            message: 'Please enter the student id',
            trigger: 'blur'
          }
        ],
        region: [
          {
            required: true,
            message: 'Please select the request type',
            trigger: 'change'
          }
        ]
      }
    }
  },

  watch: {
    'form.region'(newValue) {
      // update showAdditionOptions based on the region
      if (
        newValue === 'Quiz' ||
        newValue === 'Assignment' ||
        newValue === 'Exam'
      ) {
        this.form.showAdditionalOptions = true
      } else {
        this.form.showAdditionalOptions = false
      }
    }
  },

  mounted() {
    EventBus.$on('update-form', () => {
      this.form.studentId = ''
      this.form.region = ''
      this.form.type = ''
      this.form.workType = ''
      this.form.name = ''
      this.form.detail = ''
      this.form.showAdditionalOptions = false
      this.form.fileList = []
      this.form.teammates = []
      this.clearUploadList()
    })
  },
  // written by Dennis Wang, initialise the request types through
  // getting the authorities from database
  created() {
    // set componenent name
    this.$root.$refs.form_component = this

    getSubjectDetail(this.$store.getters.subjectId).then((res) => {
      if (res.data.data.assignmentRequest === true) {
        this.requestTypes.push({
          value: 'Assignment',
          label: 'Assignment'
        })
      }
      if (res.data.data.examRequest === true) {
        this.requestTypes.push({
          value: 'Exam',
          label: 'Exam'
        })
      }
      if (res.data.data.quizRequest === true) {
        this.requestTypes.push({
          value: 'Quiz',
          label: 'Quiz'
        })
      }
      if (res.data.data.othersRequest === true) {
        this.requestTypes.push({
          value: 'Others',
          label: 'Others'
        })
      }
      if (res.data.data.personalRequest === true) {
        this.requestTypes.push({
          value: 'Personal',
          label: 'Personal'
        })
      }
    })
  },

  // MODIFIED BY DENNIS WANG
  methods: {
    clearUploadList() {
      const uploadList = document.querySelector('.el-upload-list')
      if (uploadList) {
        uploadList.innerHTML = ''
      }
    },

    onSubmit() {
      const currentDate = new Date()
      // construct a formData to finalise all the information in the
      // form, used later to pass data to the request
      const formData = {
        studentId: this.form.studentId,
        subjectId: this.form.subjectCode,
        requestType: this.form.region,
        submissionDate: currentDate,
        description: this.form.detail,
        fileList: this.form.fileList,
        requestName: this.form.name
      }
      if (this.form.showAdditionalOptions) {
        formData.type = this.form.type
        formData.workType = this.form.workType
      }
      formData.teammates = this.form.teammates
      const param = {
        description: formData.description,
        studentId: this.getCurrentUserId(),
        subjectId: this.getCurrentSubjectId(),
        submissionDate: formData.submissionDate,
        requestType: formData.requestType,
        requestName: formData.requestName,
        taskType: formData.type,
        workType: formData.workType,
        teammates: formData.teammates,
        flagged: false,
        attachments: formData.fileList.map((item) => {
          return { url: this.convertUrlWithoutPrefix(item.url) }
        })
      } // send the add request to the server to save the request
      // info in the DB
      addRequest(param).then((res) => {
        console.log(res.data)
        if (res.data.code === 0) {
          this.$message(res.data.msg)
          // after successfully saving the request, it should be shown on the
          // web page as well, so update the request table here
          EventBus.$emit('add-request', res.data.data)
          EventBus.$emit('request-saved')
        } else {
          this.$message('Fail to submit!')
        }
      })
    },
    handleRequestTypeChange() {
      this.form.region = ''
      this.form.type = ''
      this.showAdditionalOptions = false
    },

    // MODIFIED BY DENNIS WANG
    onCancel() {
      EventBus.$emit('close-form')
    },
    // WRITTEN BY DENNIS WANG
    // used to show the files on the form page, given access to
    // the file from the client side
    convertUrlWithPrefix(url) {
      return attachmentBaseURL + url
    },
    // used to store the data in the server side as it doesn't
    // require extra address to access
    convertUrlWithoutPrefix(url) {
      return url.substr(attachmentBaseURL.length, url.length)
    },
    // handle files uploaded successfully
    handleSuccess(response, file, fileList) {
      this.form.fileList.push({
        uid: file.raw.uid,
        url: this.convertUrlWithPrefix(response.data)
      })
      console.log(this.form.fileList)
      this.$message.success('File uploaded successfully')
    },
    handleError(err) {
      if (err && err.response && err.response.data) {
        const errorMessage = err.response.data.error
        this.$message.error('File upload failed: ' + errorMessage)
      } else {
        this.$message.error('File upload failed')
      }
    },
    // WRITTEN BY DENNIS WANG
    handleRemove(file, files) {
      this.form.fileList = this.form.fileList.filter(
        (item) => item.uid !== file.uid
      )
      console.log(this.form.fileList)
    },
    addTeammate() {
      this.form.teammates.push('')
    },
    removeTeammate(index) {
      this.form.teammates.splice(index, 1)
    },
    // get student id
    getCurrentUserId() {
      return this.$store.getters.id
    },
    // get subject id
    getCurrentSubjectId() {
      return this.$store.getters.subjectId
    }
  }
}
</script>
