<template>
    <div id="app">
<!--        <Header :userId="userId" :users="users"/>-->
        <Header :user="users[userId]"/>
        <Middle :users="users" :posts="posts" :comments="comments"/>
        <Footer :usersCount="Object.keys(users).length" :postsCount="Object.keys(posts).length"/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";

export default {
  name: 'App',
  components: {
    Footer,
    Middle,
    Header
  },
  data: function () {
    return this.$root.$data;
  },
  beforeCreate() {
    this.$root.$on("onEnter", (login, password) => {
      if (password === "") {
        this.$root.$emit("onEnterValidationError", "Password is required");
        return;
      }

      const users = Object.values(this.users).filter(u => u.login === login);
      if (users.length === 0) {
        this.$root.$emit("onEnterValidationError", "No such user");
      } else {
        this.userId = users[0].id;
        this.$root.$emit("onChangePage", "Index");
      }
    });

    this.$root.$on("onRegister", (login, name, isAdmin) => {
      const loginRegex = new RegExp('^[a-z]+$');

      if (!login || login.length > 16 || login.length < 3 || !loginRegex.test(login)) {
        this.$root.$emit("onRegisterValidationError", "Invalid login. Login must contain only a-z from 3 to 16 letters.");
      } else if (Object.values(this.users).find(x => x.login === login)) {
        this.$root.$emit("onRegisterValidationError", "Login is already in use.");
      } else if (!name || name.length > 32 || name.trim() === "") {
        this.$root.$emit("onRegisterValidationError", "Invalid name. Must contain at least 1 letter from 1 to 32 letters.");
      } else {
        const id = Math.max(...Object.keys(this.users)) + 4;
        this.$root.$set(this.users, id, {id, login, name, isAdmin});
        this.$root.$emit("onChangePage", "Enter");
      }
    });

    this.$root.$on("onLogout", () => this.userId = null);

    this.$root.$on("onWritePost", (title, text) => {
      if (this.userId) {
        if (!title || title.length < 5) {
          this.$root.$emit("onWritePostValidationError", "Title is too short");
        } else if (!text || text.length < 10) {
          this.$root.$emit("onWritePostValidationError", "Text is too short");
        } else {
          const id = Math.max(...Object.keys(this.posts)) + 1;
          this.$root.$set(this.posts, id, {
            id, title, text, userId: this.userId
          });
          this.$root.$emit("onChangePage", "Index");
        }

      } else {
        this.$root.$emit("onWritePostValidationError", "No access");
      }
    });

    this.$root.$on("onEditPost", (id, text) => {
      if (this.userId) {
        if (!id) {
          this.$root.$emit("onEditPostValidationError", "ID is invalid");
        } else if (!text || text.length < 10) {
          this.$root.$emit("onEditPostValidationError", "Text is too short");
        } else {
          let posts = Object.values(this.posts).filter(p => p.id === parseInt(id));
          if (posts.length) {
            posts.forEach((item) => {
              item.text = text;
            });
          } else {
            this.$root.$emit("onEditPostValidationError", "No such post");
          }
        }
      } else {
        this.$root.$emit("onEditPostValidationError", "No access");
      }
    });
  }
}
</script>

<style>
#app {

}
</style>
