<template>
  <div name="posts">
    <IndexPost v-for="post in posts" :post="post" :author="post.user.login" :key="post.id"/>
  </div>
</template>

<script>

import IndexPost from "./IndexPost";

export default {
  name: "Posts",
  components: {
    IndexPost,
  },
  props: ["posts", "users"]
}


</script>

<style scoped>

</style>