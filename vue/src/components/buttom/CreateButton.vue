<!-- The component code was written by Yawen Luo, Dennis Wang was modified the 
     front-end and back-end interaction method code at a later stage. The following code is used 
     to build the component button elements of the sidebar. -->

<template>
  <div class="custom-button-group">
    <el-button class="custom-button" @click="showForm">+ Create</el-button>
    <el-dialog :visible.sync="formVisible" width="65%" :modal="false">
      <strong style="font-size: 20px" title="COMP30022 Request Form"
        >COMP30022 Request Form</strong
      >
      <RequestForm @form-submitted="submitForm" />
    </el-dialog>
  </div>
</template>

<script>
import RequestForm from "@/components/form/index.vue";

export default {
  components: {
    RequestForm,
  },

  data() {
    return {
      formVisible: false,
    };
  },

  // MODIFIED BY DENNIS WANG
  methods: {
    showForm() {
      // reset everything in the form everytime the form is opened
      this.formVisible = true;
      this.$root.$refs.form_component.isCheck = false;
      this.$root.$refs.form_component.form.studentId = "";
      // this.$root.$refs.form_component.form.subjectCode = '';
      this.$root.$refs.form_component.form.region = "";
      this.$root.$refs.form_component.form.type = "";
      this.$root.$refs.form_component.form.name = "";
      this.$root.$refs.form_component.form.detail = "";
      this.$root.$refs.form_component.form.fileList = [];
      this.$root.$refs.form_component.form.teammates = [];
      this.$root.$refs.form_component.form.showAdditionalOptions = false;
    },
    submitForm(formData) {
      console.log("Form submitted with data:", formData);
      this.$nextTick(() => {
        this.formVisible = false;
      });
    },
  },
  created() {
    // set componenent name
    this.$root.$refs.button_component = this;
  },
};
</script>

<style scoped>
.custom-button-group {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80px;
  margin-top: 40px;
  margin-bottom: 60px;
}

.custom-button {
  padding: 18px 38px;
  font-size: 16px;
  font-weight: bold;
}
</style>
