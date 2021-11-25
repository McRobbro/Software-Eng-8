<template id="product-auction">
  <navbar>
  </navbar>
  <app-frame>

    <div class="auction-style" v-if="auctionProduct">
      <p>
        <img v-if="auctionProduct.product.productImage" class="cover-image-frontpage" v-bind:src="auctionProduct.product.productImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
        {{auctionProduct.product.productName}} start price: {{auctionProduct.startPrice}}
      </p>
      <form :action=`/api/` method="post">
        <label for="bid">Place a bid</label>
        <input type="text" name="bid" id="bid" v-model="bid" required>
        <input type="submit" value="Place a bid">
      </form>
    </div>
  </app-frame>
</template>
<script>
app.component("product-auction", {
  template: "#product-auction",
  data: () => ({
    auctionProduct: null,
    storeSlug: "",
    cookieValue: document.cookie,
    bid: null
  }),
  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;
    const auctionProd = this.$javalin.pathParams["auctionProd"];
    this.auctionProduct = auctionProd
    console.log(auctionProd);
    fetch(`/api/stores/${specificStore}/auctions/${auctionProd}`)
        .then(res => res.json())
        .then(json => this.auctionProduct = json)
        .catch(() => alert("Error while fetching specific product"));
  }
});
</script>
<style>
img.cover-image-frontpage {
  height: auto;
  width: 10%;
  padding-bottom: 20px;
  max-height: auto;
}

.auction-style {
  display: flex;
  flex-flow: column;
}
</style>
