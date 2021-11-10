<template id="store-detail">
  <navbar>
  </navbar>
  <app-frame>
    <ul class="store-overview-list">
      <li v-for="product in storeProducts">
        <a :href="`/stores/${product.store.slug}/products/${product.productSlug}`">
          <img v-if="product.productImage" class="cover-image-frontpage" v-bind:src="product.productImage">
          <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
          {{product.productName}}
          ({{product.productDescription}})
        </a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("store-detail", {
  template: "#store-detail",
  data: () => ({
    storeProducts: [],
  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    console.log("This store name: " + specificStore);
    fetch(`/api/stores/${specificStore}`)
        .then(res => res.json())
        .then(json => this.storeProducts = json)
        .catch(() => alert("Error while fetching products"));
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
