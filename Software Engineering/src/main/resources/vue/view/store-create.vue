<template id="store-create">
  <navbar>
  </navbar>

  <app-frame>
    <div class="form-style">
      <form class="create" :action=`/api/stores/create` method="post">

        <label for="slug">storeSlug</label>
        <input type="text" name="slug" id="slug" v-model="slug" required>

        <lable for="name">storeName</lable>
        <input type="text" name="storeName" id="storeName" v-model="storeName" required>

        <lable for="storeImage">storeImage</lable>
        <input type="url" name="storeImage" id="storeImage" v-model="storeImage">

        <lable for="storeDescription">storeDescription</lable>
        <input type="text" name="storeDescription" id="storeDescription" v-model="storeDescription">

        <input type="submit" value="Create store">
      </form>
    </div>
  </app-frame>
</template>
<script>
app.component("store-create", {
  template: "#store-create",
  data: () => ({
    slug: null,
    storeName: null,
    storeImage: null,
    storeDescription: null
  }),
});
</script>
<style>
</style>
