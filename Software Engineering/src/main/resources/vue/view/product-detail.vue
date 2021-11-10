<template id="product-detail">
  <navbar>
  </navbar>
  <app-frame>
    <div v-if="product">
      <p>
        <img v-if="product.productImage" class="cover-image-frontpage" v-bind:src="product.productImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
        {{product.productName}} Sold By {{product.store.slug}}
      </p>
    </div>
  </app-frame>
</template>
<script>
app.component("product-detail", {
  template: "#product-detail",
  data: () => ({
    product: null,
    storeName: "",
  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeName = specificStore;
    console.log("This store name: " + specificStore);
    const specificProduct = this.$javalin.pathParams["prodSlug"];
    this.product = specificProduct
    fetch(`/api/stores/${specificStore}/products/${specificProduct}`)
        .then(res => res.json())
        .then(json => this.product = json)
        .catch(() => alert("Error while fetching specific product"));
  }
});
</script>
<style>
img.cover-image-frontpage {
  height: auto;
  width: 10%;
  padding-bottom: 20px;
  max-height: auto;
}
</style>
