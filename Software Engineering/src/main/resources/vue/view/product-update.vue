<template id="product-update">
  <navbar>
  </navbar>
  <app-frame>
    <form :action=`/api/stores/${storeSlug}/${prodSlug}/updateProduct` method="post">


      <label for="productSlug">Slug (identifiserende del av URLen til butikken): </label>
      <input type="text" name="productSlug" id="productSlug" v-model="productSlug">

      <label for="productName"> Varenavn: </label>
      <input type="text" name="productName" id="productName" v-model="productName">

      <label for="productImage"> URL til bilde av varen: </label>
      <input type="text" name="productImage" id="productImage" v-model="productImage">


      <label for="productDescription"> Beskrivelse av varen: </label>
      <input type="text" name="productDescription" id="productDescription" v-model="productDescription">

      <label for="productDescription"> Varekategori: </label>
      <input type="text" name="productCategory" id="productCategory" v-model="productCategory">

      <label for="productDescription"> Pris på varen: </label>
      <input type="text" name="price" id="price" v-model="price">

      <button type="submit">Oppdater vare</button>
    </form>



  </app-frame>
</template>
<script>
app.component("product-update", {
  template: "#product-update",
  data: () => ({
    prodSlug: null,
    storeSlug: "",


  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;
    console.log("Butikken: " + specificStore);
    const specificProduct = this.$javalin.pathParams["prodSlug"];
    this.prodSlug = specificProduct
  }

});
</script>
<style>

</style>