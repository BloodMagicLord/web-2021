<template>
  <div name="post">
    <IndexPost :post="post" :author="users[post.userId].login" :commentsCount="commentsCount"/>
    <Comments :postComments="postComments" :users="users"/>
  </div>
</template>

<script>

import IndexPost from "../main/IndexPost";
import Comments from "../main/Comments";

export default {
  name: "Post",
  props: ["post", "postComments", "users", "commentsCount"],
  components: {
    IndexPost,
    Comments
  }
}
</script>

<style scoped>

</style>
