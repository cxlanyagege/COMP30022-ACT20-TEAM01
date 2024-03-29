<!-- The following code is the framework that comes with the scaffolding
modified and edited by Lanruo Su, Xuan Zhang and He Shen -->
<template>
  <div>
    <div class="website-header">
      <img src="../../../../public/favicon.svg" alt="My Website Logo" class="logo">
      <span class="website-name">StuRequestHub</span>
    </div>
    <div :class="{ 'has-logo': showLogo }">
      <logo v-if="showLogo" :collapse="isCollapse" />
      <el-scrollbar wrap-class="scrollbar-wrapper">
        <el-menu
          :default-active="activeMenu"
          :collapse="isCollapse"
          :background-color="variables.menuBg"
          :text-color="variables.menuText"
          :unique-opened="false"
          :active-text-color="variables.menuActiveText"
          :collapse-transition="false"
          mode="vertical"
        >
          <sidebar-item
            v-for="route in routes"
            :key="route.path"
            :item="route"
            :base-path="route.path"
          />
          <div style="margin-top: 30px;">
            <el-button
              v-for="requestType in requestTypes"
              :key="requestType.subID"
              :class="{ selected: isSelected(requestType.subID) }"
              :style="{
                color: requestType.color,
                textAlign: 'center',
                'margin-left': '10px',
                'margin-top': '10px',
                'background-color': '#304156de',
                'font-size': '13px'
              }"
              @click="filterRequests(requestType.subID)"
            >
              <span class="dot" :style="{ backgroundColor: requestType.color }" />
              {{ requestType.subName }}
            </el-button>
          </div>
        </el-menu>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'
import { EventBus } from '@/utils/EventBus'
import { getAllSubjects } from '@/api/api'

function getRandomColor() {
  const red = Math.floor(Math.random() * 256)
  const green = Math.floor(Math.random() * 256)
  const blue = Math.floor(Math.random() * 256)
  return `rgb(${red}, ${green}, ${blue})`
}

export default {
  components: { SidebarItem, Logo },
  data() {
    return {
      isCollapse: false,
      // here is used to get all the subjects this tutor is in
      // need to update authomatically after connection with lms
      requestTypes: [],
      selectedRowIndex: null
    }
  },
  computed: {
    ...mapGetters(['sidebar']),
    ...mapState(['tableData']), // 获取tableData从store
    // 添加一个computed属性用于过滤请求类型
    routes() {
      return this.$router.options.routes
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    }
  },
  mounted() {
    // Fetch and store your request data
    // Example: this.requests = fetchDataFromServer();
    EventBus.$on('copy-data-event', (data) => {
      this.tempData = data
      this.tableData = data
    })

    // Get all subjects by this tutor
    getAllSubjects(this.$store.getters.id).then(response => {
      this.requestTypes = response.data.data.map(subject => ({
        subID: subject.subjectId,
        subName: subject.subjectName,
        color: getRandomColor()
      }))
    }).catch(error => {
      console.error('There was an error fetching the subjects: ', error)
    })
  },
  methods: {
    filterRequests(subID) {
      this.selectedRowIndex = subID
      EventBus.$emit('filter', subID) // 触发 filter 事件并传递 subID
    },
    isSelected(subID) {
      return this.selectedRowIndex === subID
    }
  }
}
</script>

<style>
/* 样式可以根据你的需求进行调整 */
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
  margin-right: 20px;
}

.website-name {
  font-size: 18px;
  font-weight: bold;
}

.dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  margin-right: 10px;
}

.subject-id-filter {
  text-align: center; /* 如果需要居中对齐 */
}

</style>
