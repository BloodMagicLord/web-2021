<template>
  <div class="comment">
    <div class="information">By {{ author }} | ID: {{ comment.id }} </div>
    <div class="body"> {{ comment.text }}</div>
  </div>
</template>

<script>
export default {
  name: "IndexPost",
  props: ["comment", "author"]
}
</script>

<style scoped>

</style>