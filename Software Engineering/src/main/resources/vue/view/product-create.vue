<template id="product-create">
  <navbar>
  </navbar>

  <app-frame>
    <div v-if="errors.length">
      <b>Please correct the following error(s):</b>
      <ul>
        <li v-for="error in errors">{{ error }}</li>
      </ul>
    </div>
    <div class="form-style">
      <form class="create" :action=`/api/stores/${storeSlug}/create` method="post">

        <label for="productSlug">productSlug</label>
        <input type="text" name="productSlug" id="productSlug" v-model="productSlug">

        <lable for="productName">productName</lable>
        <input type="text" name="productName" id="productName" v-model="productName">

        <lable for="productImage">productImage</lable>
        <input type="url" name="productImage" id="productImage" v-model="productImage">

        <lable for="productDescription">productDescription</lable>
        <input type="text" name="productDescription" id="productDescription" v-model="productDescription">

        <lable for="productCategory">productCategory</lable>
        <input type="text" name="productCategory" id="productCategory" v-model="productCategory">

        <lable for="productPrice">productPrice</lable>
        <input type="text" name="productPrice" id="productPrice" v-model="productPrice">

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
    productPrice: null,
    errors: []

  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;
    console.log("This store name: " + specificStore);

  }
});
</script>
<style>

form {


}

</style>
