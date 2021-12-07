<template id="users-overview">
  <navbar>
  </navbar>

  <app-frame>
    <ul class="store-overview-list">
      <li v-for="user in users">
        <a :href="`/users/${user.username}`">
          <img v-if="user.Image" class="cover-image-frontpage" v-bind:src="user.Image">
          <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
          {{user.email}} ({{user.username}})</a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("users-overview", {
  template: "#users-overview",
  data: () => ({

    users: [],
    cookieValue: document.cookie,
  }),

  created() {
    fetch("/api/users")
        .then(res => res.json())
        .then(json => this.users = json)
        .catch((error) => {
          console.log(error);
          alert("Error while fetching all users")
        });
  }


});
</script>
<style>
ul.store-overview-list {
  display: flex;
  flex-flow: wrap;
  justify-content: space-evenly;
  padding: 0;
  list-style: none;
}

ul.store-overview-list a {
  display: block;
  max-width: 250px;

  padding: 16px;
  background: #0e0e0e;
  text-decoration: none;
  color: white;

  border-bottom: 1px solid var(--gold-color);
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}
li {
  margin: 16px;
}
ul.store-overview-list a:hover {
  background: #676767;
}
img.cover-image-frontpage {
  height: auto;
  width: 100%;
  padding-bottom: 20px;
  max-height: auto;
}
</style>