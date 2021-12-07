<template id="product-create">
  <navbar>
  </navbar>

  <app-frame>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/stores/${storeSlug}/createProduct` method="post">

        <label for="productSlug">Slug (identifiserende del av URLen til varen): </label>
        <input type="text" name="productSlug" id="productSlug" v-model="productSlug" required>

        <label for="productName"> Varenavn: </label>
        <input type="text" name="productName" id="productName" v-model="productName" required>

        <label for="productImage"> URL til bilde av varen: </label>
        <input type="url" name="productImage" id="productImage" v-model="productImage" required>

        <label for="productDescription"> Beskrivelse av varen: </label>
        <input type="text" name="productDescription" id="productDescription" v-model="productDescription" required>

        <label for="productCategory"> Varekategori: </label>
        <input type="text" name="productCategory" id="productCategory" v-model="productCategory" required>

        <label for="productPrice"> Pris på varen: </label>
        <input type="text" name="productPrice" id="productPrice" v-model="productPrice" required>

        <input type="submit" value="Opprett vare">
      </form>
    </div>
  </app-frame>
</template>
<script>
app.component("product-create", {
  template: "#product-create",
  data: () => ({
    storeSlug: "",
    productSlug: null,
    productName: null,
    productImage: null,
    productDescription: null,
    productCategory: null,
    productPrice: null
  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;
    console.log("Butikken: " + specificStore);

  }
});
</script>
<style>
</style>
