<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2021 by Mike Mirzayanov
        <div name="usersCount"> Registered users: {{ usersCount }}</div>
        <div name="postsCount"> Posts written:  {{ postsCount }} </div>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersCount", "postsCount"],
}
</script>

<style scoped>

</style>