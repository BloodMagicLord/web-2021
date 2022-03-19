<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :posts="posts" :users="users"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            users: [],
            posts: []
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        axios.get("/api/1/posts").then(response => {
            this.posts = response.data;
        });

        this.updateUsers();
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            axios.post("/api/1/jwt", {
              login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onRegister", (login, name, password) => {
            axios.post("/api/1/users", {
              login, name, password
            }).then(response => {
              this.updateUsers();
              localStorage.setItem("jwt", response.data);
              this.$root.$emit("onJwt", response.data);
            }).catch(error => {
              this.$root.$emit("onRegisterValidationError", error.response.data);
            });
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });
    },
    methods: {
      updateUsers: function () {
        axios.get("/api/1/users").then(response => {
          this.users = response.data;
        });
      }
    }
}
</script>

<style>
#app {

}
</style>
