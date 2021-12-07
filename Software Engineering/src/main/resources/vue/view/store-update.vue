<template id="store-update">
  <navbar>
  </navbar>

  <app-frame>
    <div v-if="errors.length">
      <b>Please correct the following error(s):</b>
      <ul>
        <li v-for="error in errors">{{ error }}</li>
      </ul>
    </div>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/stores/${storeSlug}/update` method="post">

        <label for="slug">storeSlug</label>
        <input type="text" name="slug" id="slug" v-model="slug">

        <lable for="name">storeName</lable>
        <input type="text" name="storeName" id="storeName" v-model="name">

        <lable for="storeImage">storeImage</lable>
        <input type="url" name="storeImage" id="storeImage" v-model="storeImage">

        <lable for="storeDescription">storeDescription</lable>
        <input type="text" name="storeDescription" id="storeDescription" v-model="storeDescription">

        <input type="submit" value="update store">
      </form>
    </div>
  </app-frame>
</template>
<script>
app.component("store-update", {
  template: "#store-update",
  data: () => ({
    storeSlug: "",
    store: null,
    slug: null,
    storeName: null,
    storeImage: null,
    storeDescription: null,
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
.create {
  display: flex;
  flex-flow: column;
}
</style>