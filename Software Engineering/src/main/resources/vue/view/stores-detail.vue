<template id="store-detail">
  <navbar>
  </navbar>
  <app-frame>
    <p>{{store.storeDescription}}</p>
  </app-frame>
</template>
<script>
app.component("store-detail", {
  template: "#store-detail",
  data: () => ({
    store: [],
  }),
  created() {
    const specificStore = this.$javalin.pathParams["slug"];
    console.log("This store id: " + specificStore);
    fetch(`/api/stores/${specificStore}`)
        .then(res => res.json())
        .then(json => this.store = json)
        .catch(() => alert("Error while fetching specific store"));
  }
});
</script>
<style>
ul.store-detail-list {
  padding: 0;
  list-style: none;
}
ul.store-detail-list a {
  display: block;
  padding: 16px;
  border-bottom: 1px solid #ddd;
}
ul.store-detail-list a:hover {
  background: #00000010;
}
</style>
