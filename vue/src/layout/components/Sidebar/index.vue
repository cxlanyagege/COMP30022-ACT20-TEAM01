 <!-- 自己写的 -->
 
<template>
  <div>
    <div class="website-header">
      <img
        src="../../../../public/favicon.svg"
        alt="My Website Logo"
        class="logo"
      />
      <span class="website-name">StuRequestHub</span>
    </div>
    <CustomButtonGroup />
    <div class="request-type-title">
      <span>Request Type</span>
    </div>
    <div>
      <ul class="request-filter">
        <li
          class="request-li"
          v-for="(type, index) in requestTypes"
          :key="index"
          @mouseover="highlightRow(index)"
          @mouseout="clearHighlight(index)"
          @click="toggleSelection(index, type)"
          :class="{
            highlighted: highlightedRows[index],
            selected: isSelected(index),
          }"
        >
          <span class="dot" :style="{ backgroundColor: type.color }"></span>
          {{ type.name }}
        </li>
      </ul>
    </div>
  </div>
</template>


<script>
import { mapGetters } from "vuex";
import Logo from "./Logo";
import SidebarItem from "./SidebarItem";
import variables from "@/styles/variables.scss";
import CustomButtonGroup from "@/components/buttom/CreateButton.vue";
import { EventBus } from "@/utils/event-bus"

export default {
  data() {
    return {
      requestTypes: [
        { name: "Assignment", color: "rgb(52, 152, 219)" },
        { name: "Test", color: "rgb(46, 204, 113)" },
        { name: "Exam", color: "rgb(243, 156, 18)" },
        { name: "Personal", color: "rgb(231, 76, 60)" },
        { name: "Others", color: "rgb(155, 89, 182)" },
      ],
      highlightedRows: [],
      clickedRows: [],
      tableData: [],
      tempData: [],
    };
  },

  components: { SidebarItem, Logo, CustomButtonGroup },

  computed: {
    ...mapGetters(["sidebar"]),
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo;
    },
    variables() {
      return variables;
    },
  },

  mounted() {
    EventBus.$on("copy-data-event", (data) => {
      this.tempData = data
    });
  },

  methods: {
    filterRequests(requestType) {
      this.tableData = this.tempData.filter(item => {
        return item.type === requestType;
      })
      EventBus.$emit("update-data", this.tableData);
    },
    highlightRow(index) {
      if (!this.clickedRowIndex) {
        this.$set(this.highlightedRows, index, true);
      }
    },
    clearHighlight(index) {
      if (!this.clickedRowIndex) {
        this.$set(this.highlightedRows, index, false);
      }
    },
    toggleSelection(index, type) {
      if (this.selectedRowIndex === index) {
        this.selectedRowIndex = null;
        EventBus.$emit("update-data", this.tempData);
      } else {
        this.selectedRowIndex = index;
        this.filterRequests(type.name);
      }
      this.clearHighlightRowsExcept(index);
    },
    isSelected(index) {
      return this.selectedRowIndex === index;
    },
    clearHighlightRowsExcept(index) {
      for (let i = 0; i < this.highlightedRows.length; i++) {
        if (i !== index) {
          this.$set(this.highlightedRows, i, false);
        }
      }
    },
  },
};
</script>


<style>
.website-header {
  display: flex;
  align-items: center;
  padding: 10px;
  background-color: #304156;
  color: rgb(255, 255, 255);
  height: 50px;
  margin-top: 25px;
}

.logo {
  width: 30px;
  height: auto;
  margin-right: 20px;
}

.website-name {
  font-size: 18px;
  font-weight: bold;
}

ul {
  list-style-type: none;
}

.dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  margin-right: 10px;
}

.request-type-title {
  font-family: "element-icons";
  font-size: 12px;
  margin: 0;
  padding-left: 13.5px;
  color: rgb(255, 255, 255);
  margin-bottom: 23px;
  font-weight: bold;
}

.request-li {
  font-family: "element-icons";
  font-size: 14.5px;
  color: rgb(255, 255, 255);
  height: 37px;
  cursor: pointer;
  display: flex;
  align-items: center;
  padding-left: 25px;
  border-radius: 7px;
}

.request-filter {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.highlighted {
  background-color: #3d5067;
  border-radius: 7px;
}

.selected {
  background-color: #5b728f;
  border-radius: 7%;
}
</style>