<template>
  <Posts :posts="posts" :users="users"/>
</template>

<script>

import Posts from "../middle/Posts";

export default {
    name: "Index",
    components: {
      Posts
    },
    props: ["posts", "users"]
}
</script>

<style scoped>

</style>