<template id="product-detail">
  <navbar>
  </navbar>
  <app-frame>
    <a v-if="cookieValue === 'role=Butikkeier'" :href="'/stores/' + storeSlug + '/' + prodSlug + '/updateProduct'"><button>Oppdater vare</button></a>
    <div v-if="product">
      <h1>{{product.store.slug}}</h1>
      <div class="itemblock">
      <img v-if="product.productImage" class="cover-image-frontpage" v-bind:src="product.productImage">
      <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
      <div class="itemblock-wrapper">
        <p>{{product.productName}}</p>
        <p>{{product.productDescription}}</p>
        <p>{{product.price}},-</p>
        <form :action=`/api/stores/${product.store.slug}/${product.productSlug}/purchase` method="post">
          <button type="submit" v-on:click="ConfirmWindow()">Kjøp vare</button>
        </form>
      </div>
      </div>
    </div>
  </app-frame>
</template>
<script>
app.component("product-detail", {
  template: "#product-detail",
  data: () => ({
    product: null,
    storeName: "",
    cookieValue: document.cookie
  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeName = specificStore;
    console.log("Butikken: " + specificStore);
    const specificProduct = this.$javalin.pathParams["prodSlug"];
    this.product = specificProduct
    fetch(`/api/stores/${specificStore}/${specificProduct}`)
        .then(res => res.json())
        .then(json => this.product = json)
        .catch(() => alert("Feil ved henting av varer"));
  },
  methods: {
    ConfirmWindow() {
      if(confirm("Vil du kjøpe denne varen?")) {

      }
    }
  }
});
</script>
<style>
img.cover-image-frontpage {
  height: auto;
  width: 35%;
  padding-bottom: 20px;
  max-height: auto;
}
h1 {
  text-align: center;
}
.itemblock {
  width: 50%;
}
.itemblock-wrapper {
  width: 45%;
  display: flex;
  flex-flow: column;
  float: right;
}
</style>
