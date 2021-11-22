<template id="product-update">
  <navbar>
  </navbar>
  <app-frame>
    <form :action=`/api/stores/${storeSlug}/${prodSlug}/updateProduct` method="post">


      <label for="productSlug">storeSlug</label>
      <input type="text" name="productSlug" id="productSlug" v-model="productSlug">

      <label for="productName">productName</label>
      <input type="text" name="productName" id="productName" v-model="productName">

      <label for="productImage">productImage</label>
      <input type="text" name="productImage" id="productImage" v-model="productImage">


      <label for="productDescription">productDescription</label>
      <input type="text" name="productDescription" id="productDescription" v-model="productDescription">

      <label for="productDescription">productCategory</label>
      <input type="text" name="productCategory" id="productCategory" v-model="productCategory">

      <label for="productDescription">price</label>
      <input type="text" name="price" id="price" v-model="price">

      <button type="submit">update product</button>
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
    console.log("This store name: " + specificStore);
    const specificProduct = this.$javalin.pathParams["prodSlug"];
    this.prodSlug = specificProduct
  }

});
</script>
<style>

</style>