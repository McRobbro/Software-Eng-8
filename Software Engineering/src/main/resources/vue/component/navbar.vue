<template id="navbar">
  <div class="navbar">
    <nav>
      <a :href="/stores/"><p>Hjem</p></a>
      <!--      <a :href="/cart/" id="cart" v-if="cookieValue === 'role=USER'"><p><i class="fas fa-shopping-cart">0</i></p></a> -->
      <a :href="/login/" id="login"><p>Logg inn eller registrer deg </p></a>
      <a :href="/users/" id="users" v-if="cookieValue === 'role=Administrator'"><p>registrerte brukere</p></a>
      <p class="currentUser" v-if="cookieValue === 'role=Administrator'" >Nåværende rolle: {{$javalin.state.currentUser}}</p>
      <p class="currentUser" v-if="cookieValue === 'role=Butikkeier'" >Nåværende rolle: {{$javalin.state.currentUser}}</p>
      <a :href="/user/" id="user" v-if="cookieValue === 'role=Registrert butikkunde'"><p>{{$javalin.state.currentUser}}</p></a>
    </nav>
  </div>
</template>

<script>
app.component("navbar", {
  template: "#navbar",

data: () => ({

  cookieValue: document.cookie,
})
});
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

.currentUser {
  padding: 0.5em 2em;
  color: gold;
  text-decoration: none;
  background-color: #0e0e0e;
  float: right;
}

nav #login,
nav #users,
nav #cart,
nav #user{
  float: right;
}

nav #cart {
  font-size: 1.25em;
  padding: 0.075em 1.25em;
}

</style>