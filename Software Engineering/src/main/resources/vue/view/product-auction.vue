<template id="product-auction">
  <navbar>
  </navbar>
  <app-frame>

    <div class="auction-style" v-if="auctionProduct">
      <p>
        <img v-if="auctionProduct.product.productImage" class="cover-image-frontpage" v-bind:src="auctionProduct.product.productImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
        {{auctionProduct.product.productName}}
      </p>
      <p>
        This product has a start price on: {{auctionProduct.startPrice}}$ <br>
        auction start date: {{auctionProduct.startTime}} <br>
        auction end date: {{auctionProduct.endTime}} <br>
      </p>
      <p v-if="auctionProduct.timeDurationToAuctionStart <= 0">
             This auction will end in {{auctionProduct.timeDurationToAuctionEnd}} minutes
        <br>
        <br>
              current highest bid: {{currentHighestBid}}
      </p>
      <p v-else>
         This auction will open in {{auctionProduct.timeDurationToAuctionStart}} minutes
      </p>
      <form v-if="auctionProduct.timeDurationToAuctionStart <= 0" id="app" @submit="checkForm" :action=`/api/stores/${storeSlug}/auctions/${auctionProduct.product.productSlug}/createBid` method="post">
        <p v-if="errors.length">
        <ul>
          <li v-for="error in errors">{{ error }}</li>
        </ul>
       <label for="bid">Place a bid</label> <br>
        <input type="text" name="bid" id="bid" v-model="bid" required>
        <input onclick="checkForm()" type="submit" value="Place a bid">
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
    currentHighestBid: null,
    bid: null,
    errors:[],
    startPrice: null
  }),

  methods: {
    checkForm: function (e) {
      this.errors = [];
      let startPrice = this.auctionProduct.startPrice;
      let bid = this.bid;
      let currentHighestBid = this.currentHighestBid;
      if(startPrice > bid) {
        this.errors.push('you need to bid higher then start price!');
      }
      else if (bid > currentHighestBid) {
        return true;
      }
      else {
        this.errors.push('you need to bid higher then current bid!');

      }
      e.preventDefault();
    }

  },

  created() {
    const specificStore = this.$javalin.pathParams["storeSlug"];
    this.storeSlug = specificStore;
    const auctionProd = this.$javalin.pathParams["auctionProd"];
    this.auctionProduct = auctionProd


    fetch(`/api/stores/${specificStore}/auctions/${auctionProd}`)
        .then(res => res.json())
        .then(json => this.auctionProduct = json)
        .catch(() => alert("Error while fetching specific product"));

    fetch(`/api/stores/${specificStore}/auctions/${auctionProd}/currentHighestBid`)
        .then(res => res.json())
        .then(json => this.currentHighestBid = json)
        .catch(() => alert("Error while fetching bids"));
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
