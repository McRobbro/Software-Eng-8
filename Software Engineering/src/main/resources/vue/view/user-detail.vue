<template id="user-detail">
  <navbar>
  </navbar>

  <app-frame>

    <div v-if="cookieValue === 'role=USER'">
      <div class="changeInfo" v-if="showPages == false">
        <a href="#" @click="changeUser(user)" v-for="(user, index) in users" :key="index">{{user.username}}</a>
      </div>

      <div class="changeInfo" v-else-if="showPages == true">
        <button @click="showPages = false">Go Back</button>
        <div class="form-style">
          <form class="create" @submit="checkForm" :action=`/api/user/${selectedUser.userId}/update` method="post">

            <label for="email">E-Mail</label>
            <input type="email" name="email" id="email" v-model="selectedUser.email">

            <label for="username">Username</label>
            <input type="text" name="username" id="username" v-model="selectedUser.username">

            <label for="password">Password</label>
            <input type="password" name="password" id="password" v-model="selectedUser.password">

            <input type="submit" value="update user">
          </form>
        </div>
      </div>
    </div>

    <div class="changeInfo" v-else>
      <p>You are not a USER, and can not change your information at this time</p>
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
          alert("Error while fetching stores")
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
</style>