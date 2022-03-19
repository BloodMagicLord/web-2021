<template>
  <Posts :posts="posts" :users="users" :commentsCount="commentsCount"/>
</template>

<script>

import Posts from "../main/Posts";

export default {
  name: "Index",
  props: ["posts", "users", "commentsCount"],
  components: {Posts}
}
</script>

<style scoped>

</style>
