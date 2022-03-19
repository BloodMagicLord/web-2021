<template>
    <div class="middle">
        <Sidebar :posts="sortedPosts.slice(0, 2)"/>
        <main>
            <Index :posts="sortedPosts" :users="users" :commentsCount="commentsCount" v-if="page === 'Index'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Users :users="users" v-if="page === 'Users'"/>
            <Post :post="post" :users="users" :postComments="postComments" :commentsCount="commentsCount.find(x => x.id === post.id).value" v-if="page === 'Post'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Users from "./page/Users";
import Post from "./page/Post";
import Register from "./page/Register";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: null
        }
    },
    components: {
      Post,
        WritePost,
        Enter,
        Register,
        Index,
        Sidebar,
        EditPost,
        Users
    },
    props: ["posts", "users", "comments"],
    computed: {
        sortedPosts: function () {
          return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        commentsCount: function () {
          let commentsCount = [];
          let commentsValues = Object.values(this.comments);
          let postsValues = Object.values(this.posts);

          for (let i = 0; i < postsValues.length; i++) {
            let postId = postsValues[i]["id"];
            commentsCount.push({"id": postId, "value": 0});
          }

          for (let i = 0; i < commentsValues.length; i++) {
            let postId = commentsValues[i]["postId"];
            commentsCount[commentsCount.findIndex((x => x.id === postId))].value += 1;
          }

          return commentsCount;
        },
        postComments: function () {
          let postComments = [];
          let commentsValues = Object.values(this.comments);

          for (let i = 0; i < commentsValues.length; i++) {
            let postId = commentsValues[i]["postId"];
            if (this.post.id === postId) {
              postComments.push(commentsValues[i]);
            }
          }

          return postComments
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);
        this.$root.$on("onCheckPost", (postId) => {
          this.page = "Post";
          this.post = this.posts[postId];
        });
    }
}
</script>

<style scoped>

</style>
