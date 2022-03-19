<template>
  <article>
    <div class="title">
      <a href="#" @click="fullPost(post.id)"> {{ post.title }} | ID: {{ post.id }} </a>
    </div>

    <div class="information">By {{ author }}</div>

    <div class="body"> {{ post.text }}</div>

    <div class="footer">
      <div class="left">
        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
        <span class="positive-score">+173</span>
        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
      </div>
      <div class="right">
        <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
        <a> {{ commentsCount }}</a>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  name: "IndexPost",
  props: ["post", "author", "commentsCount"],
  methods: {
      fullPost: function (postId) {
          this.$root.$emit("onCheckPost", postId);
      }
  }
}
</script>

<style scoped>

</style>