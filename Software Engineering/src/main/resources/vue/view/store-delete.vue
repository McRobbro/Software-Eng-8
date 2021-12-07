<template id="store-delete">
  <navbar>
  </navbar>
  <app-frame>

    <form :action=`/api/stores/delete` method="post">
      <label for="delete-select">Velg butikk: </label>
      <select name="store-delete" id="delete-select">
        <option v-for="store in stores">
          {{store.slug}}
        </option>
      </select>
      <button type="submit">Slett butikk</button>
    </form>
  </app-frame>
</template>
<script>
app.component("store-delete", {
  template: "#store-delete",
  data: () => ({
    stores: [],

  }),
  created() {
    fetch("/api/stores")
        .then(res => res.json())
        .then(json => this.stores = json)
        .catch((error) => {
          console.log(error);
          alert("Feil ved henting av butikker")
        });
  }

});
</script>
<style>
</style>