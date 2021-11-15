<template id="store-overview">
  <navbar>
  </navbar>

  <app-frame>
    <a :href="'stores/create'"><p>create store</p></a>
    <ul class="store-overview-list">
      <li v-for="store in stores">
        <a :href="`/stores/${store.slug}`">
        <img v-if="store.storeImage" class="cover-image-frontpage" v-bind:src="store.storeImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
        {{store.storeName}} ({{store.slug}})</a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("store-overview", {
  template: "#store-overview",
  data: () => ({
    stores: [],
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