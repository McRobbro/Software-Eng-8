<template id="store-detail">
  <navbar>
  </navbar>
  <app-frame>
    <div class="itemblock-special" v-if="cookieValue === 'role=Butikkeier'">
    <a :href="'/stores/' + storeSlug + '/update'">Oppdater informasjon om butikk</a>
    <a :href="'/stores/' + storeSlug + '/createProduct'">Legg til vare</a>
    <a :href="'/stores/' + storeSlug + '/deleteProduct'">Slett vare</a>
    <a :href="'/stores/' + storeSlug + '/createAuction'">Opprett auksjon</a>
    </div>

    <h1>Auksjoner</h1>
    <ul class="store-overview-list">
      <li v-for="auction in storeAuctions">
        <a :href="`/stores/${storeSlug}/auctions/${auction.product.productSlug}`">
        <img v-if="auction.product.productImage" class="cover-image-frontpage" v-bind:src="auction.product.productImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
          {{auction.product.productName}}
        </a>
      </li>
    </ul>

    <h1>Varer til salgs</h1>
    <ul class="store-overview-list">
      <li v-for="product in storeProducts">
        <a :href="`/stores/${product.store.slug}/${product.productSlug}`">
          <img v-if="product.productImage" class="cover-image-frontpage" v-bind:src="product.productImage">
          <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
          {{product.productName}}
        </a>
      </li>
    </ul>
  </app-frame>
</template>
<script>
app.component("store-detail", {
  template: "#store-detail",
  data: () => ({
    storeProducts: [],
    storeAuctions: [],
    storeSlug: null,
    cookieValue: document.cookie
  }),
  created() {
    this.storeSlug = this.$javalin.pathParams["storeSlug"];
    const specificStore = this.$javalin.pathParams["storeSlug"];
    console.log("This store name: " + specificStore);
    fetch(`/api/stores/${specificStore}`)
        .then(res => res.json())
        .then(json => this.storeProducts = json)
        .catch(() => alert("Error while fetching products"));

    fetch(`/api/stores/${specificStore}/auctions`)
        .then(res => res.json())
        .then(json => this.storeAuctions = json)
        .catch(() => alert("Error while fetching auctions"));

  }
});
</script>
<style>
.itemblock-special {
  width: 50%;
  display: flex;
  flex-flow: column;
  padding: 16px;
  margin: auto;
  background-color: #0e0e0e;
  font-size: 1.25em;
  text-decoration: none;
  border: 1px solid var(--gold-color);
  border-radius: 12px;
}
.itemblock-special a{
  padding: 5px;
  margin: 5px 15px;
  background-color: grey;
  border-radius: 25px;
  text-decoration: none;
  color: black;
  text-align: center;
}
.itemblock-special a:hover {
  background-color: #FFD700;
  transition: 0.5s;
}
ul.store-overview-list {
  display: flex;
  flex-flow: wrap;
  justify-content: space-evenly;
  padding: 0;
  list-style: none;
}

ul.store-overview-list a {
  display: block;
  max-width: 250px;

  padding: 16px;
  background: #0e0e0e;
  text-decoration: none;
  color: white;

  border-bottom: 1px solid var(--gold-color);
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}
li {
  margin: 16px;
}
ul.store-overview-list a:hover {
  background: #676767;
}
img.cover-image-frontpage {
  height: auto;
  width: 100%;
  padding-bottom: 20px;
  max-height: auto;
}
</style>
