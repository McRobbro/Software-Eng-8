<template id="navbar">
  <div class="navbar">
    <nav>
      <a :href="/stores/"><p>Home</p></a>
      <a :href="/login/" id="login"><p>Login or Sign up</p></a>
    </nav>
  </div>
</template>

<script>
app.component("navbar", {template: "#navbar"});
</script>

<style scoped>

nav {
  position: fixed;
  top: 0;
  left: 0;

  width: 100%;
  margin: auto;
  background: #0e0e0e;
  border-bottom: 1px solid var(--gold-color);
}

nav a {
  padding: 0.5em 2em;
  color: gold;
  text-decoration: none;
  background-color: #0e0e0e;
  float: left;
}

nav a:hover {
  color: black;
  background-color: gold;
  transition: 0.25s;
}

nav #login,
nav #cart {
  float: right;
}

nav #cart {
  font-size: 1.25em;
  padding: 0.075em 1.25em;
}

</style>