<template>
  <ul class="comments">
    <IndexComment v-for="comment in postComments" :comment="comment" :author="users[comment.userId].login" :key="comment.id"/>
  </ul>
</template>

<script>

import IndexComment from "./IndexComment";

export default {
  name: "Comments",
  components: {
    IndexComment
  },
  props: ["postComments", "users"]
}

</script>

<style scoped>

</style>