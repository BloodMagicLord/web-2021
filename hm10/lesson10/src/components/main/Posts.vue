<template>
  <div name="posts">
    <IndexPost v-for="post in posts" :post="post"
               :author="users[post.userId].login"
               :commentsCount="commentsCount.find(x => x.id === post.id).value"
               :key="post.id"/>
  </div>
</template>

<script>

import IndexPost from "./IndexPost";

export default {
  name: "Posts",
  components: {
    IndexPost,
  },
  props: ["posts", "users", "commentsCount"],
  computed: {
  }
}


</script>

<style scoped>

</style>