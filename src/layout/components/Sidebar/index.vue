<!-- The following code is the framework that comes with the scaffolding
modified and edited by Lanruo Su, Xuan Zhang -->
<template>
  <div>
    <!-- 网站logo和名字 -->
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
              <span class="dot" :style="{ backgroundColor: requestType.color }"></span>
              {{ requestType.subID }}
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

export default {
  components: { SidebarItem, Logo },
  data() {
    return {
      isCollapse: false,
      requestTypes: [
        { subID: 'COMP30023', color: 'rgb(96, 191, 255)' },
        { subID: 'COMP30022', color: 'rgb(46, 204, 113)' },
        { subID: 'COMP30026', color: 'rgb(243, 156, 18)' },
        { subID: 'COMP20008', color: 'rgb(196, 106, 239)' }
      ],
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
  height:  ;
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
