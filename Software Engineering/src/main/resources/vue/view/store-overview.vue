<template id="store-overview">
  <app-frame>
    <ul class="store-overview-list">
      <li v-for="store in stores">
        <a :href="`/stores/${store.storeName}`">{{store.storeName}} ({{store.slug}})</a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("store-overview", {
  template: "#store-overview",
  data: () => ({
    stores: [],
  }),
  created() {
    fetch("/api/stores")
        .then(res => res.json())
        .then(json => this.stores = json)
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
