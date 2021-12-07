<template id="product-delete">
  <navbar>
  </navbar>
  <app-frame>
    <form :action=`/api/stores/${storeSlug}/deleteProduct` method="post">
      <label for="product-delete-select">Velg vare </label>
      <select name="product-delete" id="product-delete-select">
        <option v-for="product in storeProducts">
          {{product.productSlug}}
        </option>
      </select>
      <button type="submit">Slett vare</button>
    </form>
  </app-frame>
</template>
<script>
app.component("product-delete", {
  template: "#product-delete",
  data: () => ({
    storeSlug: "",
    storeProducts: [],
    prodSlug: ""
  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;
    console.log("Butikken: " + specificStore);
    fetch(`/api/stores/${specificStore}`)
        .then(res => res.json())
        .then(json => this.storeProducts = json)
        .catch(() => alert("Feil ved henting av varer"));
  }

});
</script>
<style>
</style>