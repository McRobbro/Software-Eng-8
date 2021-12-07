<template id="product-create">
  <navbar>
  </navbar>

  <app-frame>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/stores/${storeSlug}/createProduct` method="post">

        <label for="productSlug">productSlug</label>
        <input type="text" name="productSlug" id="productSlug" v-model="productSlug" required>

        <lable for="productName">productName</lable>
        <input type="text" name="productName" id="productName" v-model="productName" required>

        <lable for="productImage">productImage</lable>
        <input type="url" name="productImage" id="productImage" v-model="productImage" required>

        <lable for="productDescription">productDescription</lable>
        <input type="text" name="productDescription" id="productDescription" v-model="productDescription" required>

        <lable for="productCategory">productCategory</lable>
        <input type="text" name="productCategory" id="productCategory" v-model="productCategory" required>

        <lable for="productPrice">productPrice</lable>
        <input type="text" name="productPrice" id="productPrice" v-model="productPrice" required>

        <input type="submit" value="product create">
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
    console.log("This store name: " + specificStore);

  }
});
</script>
<style>
</style>
