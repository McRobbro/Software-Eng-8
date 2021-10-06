<template id="store-overview">
  <app-frame>
    <ul class="store-overview-list">
      <li v-for="Store in store">
        <a :href="`/stores/${StoreID}`">{{slug}} ({{storeDescription}})</a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("store-overview", {
  template: "#store-overview",
  data: () => ({
    store: [],
  }),
  created() {
    fetch("/api/stores")
        .then(res => res.json())
        .then(json => this.store = json)
        .catch(() => alert("Error while fetching stores"));
  }
});
</script>
<style>
ul.store-overview-list {
  padding: 0;
  list-style: none;
}
ul.store-overview-list a {
  display: block;
  padding: 16px;
  border-bottom: 1px solid #ddd;
}
ul.store-overview-list a:hover {
  background: #00000010;
}
</style>
