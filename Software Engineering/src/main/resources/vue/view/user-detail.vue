<template id="user-detail">
  <navbar>
  </navbar>

  <app-frame>

    <div class="itemblock" v-if="cookieValue === 'role=Registrert bruker'">
      <div class="changeInfo" v-if="showPages == false">
        <a href="#" @click="changeUser(user)" v-for="(user, index) in users" :key="index"><p>{{user.username}}</p></a>
      </div>

      <div class="changeInfo" v-else-if="showPages == true">
        <button @click="showPages = false">Gå tilbake</button>
        <div class="form-style">
          <form class="create" @submit="checkForm" :action=`/api/user/${selectedUser.userId}/update` method="post">

            <label for="email">E-Mail</label>
            <input type="email" name="email" id="email" v-model="selectedUser.email">

            <label for="username">Brukernavn</label>
            <input type="text" name="username" id="username" v-model="selectedUser.username">

            <label for="password">Passord</label>
            <input type="password" name="password" id="password" v-model="selectedUser.password">

            <input type="submit" value="update user">
          </form>
        </div>
      </div>
    </div>

    <div class="changeInfo" v-else>
      <p>Du er ikke en registrert bruker, og kan ikke endre informasjonen din for øyeblikket </p>
    </div>
  </app-frame>
</template>
<script>
app.component("user-detail", {
  template: "#user-detail",
  data: () => ({
    showPages: false,
    users: [],
    selectedUser: {},

    cookieValue: document.cookie,
  }),
  created() {
    fetch("/api/users")
        .then(res => res.json())
        .then(json => this.users = json)
        .catch((error) => {
          console.log(error);
          alert("Feil ved henting av brukere")
        });
  },
  methods: {
    changeUser(selectedItem) {
      this.selectedUser = selectedItem;
      this.showPages = true;
    }
  }
});
</script>
<style>
.changeInfo {
  display: flex;
  flex-flow: column;
}
button {
  width: 33%;
}
.create {
  display: flex;
  flex-flow: column;
}
label {
  margin-top: 5px;
}
input {
  margin-bottom: 5px;
}
.itemblock {
  text-align: center;
  width: 50%;
}
.itemblock p {
  text-decoration: none;
}
a {
  color: white;
}
a:hover {
  color: black;
  background-color: gold;
  transition: 0.25s;
}
</style>