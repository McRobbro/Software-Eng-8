<template id="store-overview">
  <navbar>
  </navbar>

  <app-frame>

    <button v-if="cookieValue === 'role=PLATFORM_OWNER'" onclick="window.location.href='stores/create'">Create store</button>
    <button v-if="cookieValue === 'role=PLATFORM_OWNER'" onclick="window.location.href='stores/delete'">Delete store</button>

    <ul class="store-overview-list">
      <li v-for="store in stores">
        <a :href="`/stores/${store.slug}`">
        <img v-if="store.storeImage" class="cover-image-frontpage" v-bind:src="store.storeImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
        {{store.storeName}}</a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("store-overview", {
  template: "#store-overview",
  data: () => ({
    stores: [],
    cookieValue: document.cookie,
  }),

  created() {
    fetch("/api/stores")
        .then(res => res.json())
        .then(json => this.stores = json)
        .catch((error) => {
          console.log(error);
          alert("Error while fetching stores")
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
button {
  left: 45%;
}
</style>