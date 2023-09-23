<template>
  <div>
    <h1>Filtered Content</h1>
    <p>Filter: {{ filterParam }}</p>
    <!-- 在这里展示根据筛选参数过滤后的内容 -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      filterParam: ''// 用于存储筛选参数
    }
  },
  created() {
    // 获取筛选参数并存储在data中
    this.filterParam = this.$route.params.filterParam
  }
}
</script>
