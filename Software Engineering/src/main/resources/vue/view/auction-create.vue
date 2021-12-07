<template id="auction-create">
  <navbar>
  </navbar>

  <app-frame>
    <div class="itemblock">
      <form class="itemblock-form" :action=`/api/stores/${storeSlug}/createAuction` method="post">
        <label for="auction-product-select">Velg vare </label>
        <select name="auctionProduct" id="auction-product-select" required>
          <option disabled selected value> -- velg en vare --</option>
          <option v-for="product in storeProducts">
            {{product.productSlug}}
          </option>
        </select>
        <label for="startPrice"> Startpris </label>
        <input type="text" name="startPrice" id="startPrice" v-model="startPrice" required>
        <label for="startDate"> Starttid </label>
        <input type="datetime-local" min="2021" name="startDate" id="startDate" v-model="startDate" required>
        <label for="endDate"> Slutttid </label>
        <input type="datetime-local" name="endDate" id="endDate" v-model="endDate" required>
        <button type="submit"> Oprett auksjon </button>
      </form>
    </div>
  </app-frame>
</template>
<script>
app.component("auction-create", {
  template: "#auction-create",
  data: () => ({
    storeSlug: "",
    storeProducts: [],
    startPrice: null,
    startDate: null,
    endDate: null

  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;

    fetch(`/api/stores/${specificStore}`)
        .then(res => res.json())
        .then(json => this.storeProducts = json)
        .catch(() => alert("Error while fetching products"));
  }
});
</script>
<style>
</style>
