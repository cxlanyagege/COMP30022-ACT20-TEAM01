<!-- The component code was written by Yawen Luo, and Dennis Wang and He Shen were modified the 
     front-end and back-end interaction method code at a later stage. The following code is used 
     to build the component button elements of the sidebar. -->

<template>
  <div class="custom-button-group">
    <el-button
      class="custom-button"
      @click="showForm"
    >+ Create</el-button>
    <el-dialog :visible.sync="formVisible" width="65%" :modal="false">
      <strong style="font-size: 20px;" :title="subjectInfo.subjectName + ' Request Form'">{{ subjectInfo.subjectName }} Request Form</strong>
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
      formVisible: false
    }
  },
  computed: {
    subjectInfo() {
      return {
        subjectName: this.$store.state.subject.subjectName,
        subjectId: this.$store.state.subject.subjectId
      }
    }
  },
  created() {
    this.$root.$refs.button_component = this
  },

  methods: {
    // MODIFIED BY DENNIS WANG & HE SHEN
    async showForm() {
      try {
        // get subject info
        await this.$store.dispatch('subject/getSubjectInfo')
        // reset everything in the form everytime the form is opened
        this.formVisible = true
        this.$root.$refs.form_component.isCheck = false
        this.$root.$refs.form_component.form.studentId = ''
        this.$root.$refs.form_component.form.subjectCode = ''
        this.$root.$refs.form_component.form.region = ''
        this.$root.$refs.form_component.form.type = ''
        this.$root.$refs.form_component.form.name = ''
        this.$root.$refs.form_component.form.time = ''
        this.$root.$refs.form_component.form.detail = ''
        this.$root.$refs.form_component.form.fileList = []
        this.$root.$refs.form_component.form.teammates = []
        this.$root.$refs.form_component.form.email = false
        this.$root.$refs.form_component.form.showAdditionalOptions = false
      } catch (error) {
        console.error('Error getting subject info', error)
      }
    },
    submitForm(formData) {
      console.log('Form submitted with data:', formData)
      this.$nextTick(() => {
        this.formVisible = false
      })
    }
  }
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
