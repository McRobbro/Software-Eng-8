<template id="store-create">
  <navbar>
  </navbar>

  <app-frame>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/stores/create` method="post">

        <label for="slug">Slug (identifiserende del av URLen til butikken): </label>
        <input type="text" name="slug" id="slug" v-model="slug" required>

        <lable for="name"> Butikknavn: </lable>
        <input type="text" name="storeName" id="storeName" v-model="storeName" required>

        <lable for="storeImage"> URL til bilde av butikken: </lable>
        <input type="url" name="storeImage" id="storeImage" v-model="storeImage">

        <lable for="storeDescription"> Beskrivelse av butikken: </lable>
        <input type="text" name="storeDescription" id="storeDescription" v-model="storeDescription">

        <input type="submit" value="Opprett butikk">
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
