<template id="product-auction">
  <navbar>
  </navbar>
  <app-frame>

    <div class="itemblock" v-if="auctionProduct">
        <img v-if="auctionProduct.product.productImage" class="cover-image-frontpage" v-bind:src="auctionProduct.product.productImage">
        <img v-else class="cover-image-frontpage" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
      <div class="itemblock-wrapper">
      <p>{{auctionProduct.product.productName}}</p>
      <p>Dette produktet har en startpris på: {{auctionProduct.startPrice}},-</p>
      <p>Auksjonens starttid: {{auctionProduct.startTime}}</p>
      <p>Auksjonens sluttid: {{auctionProduct.endTime}}</p>
      <div v-if="auctionProduct.timeDurationToAuctionEnd >= 0" class="closed">
      <form v-if="auctionProduct.timeDurationToAuctionStart <= 0" class="auction-style" id="app" @submit="checkForm" :action=`/api/stores/${storeSlug}/auctions/${auctionProduct.product.productSlug}/createBid` method="post">
        <p>Nåverende høyste bud:  {{currentHighestBid}},-</p>
        <p v-if="errors.length">
        <ul>
          <li v-for="error in errors">{{ error }}</li>
        </ul>
       <label for="bid">Legg inn et bud</label> <br>
        <input type="text" name="bid" id="bid" v-model="bid" required>
        <input onclick="checkForm()" type="submit" value="Place a bid">
      </form>
        <form v-else>
          <p>Denne auksjonen starter om {{auctionProduct.timeDurationToAuctionStart}} minutter</p>
        </form>
      </div>
      <div v-else>
        <p>Denne auksjonen er stengt</p>
      </div>
      </div>
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
        this.errors.push('Error: Du må by høyere enn startpris!');
      }
      else if (bid > currentHighestBid) {
        return true;
      }
      else {
        this.errors.push('Error: Du må by høyere enn gjeldende bud!');

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
        .catch(() => alert("Feil under henting av spesifikt produkt"));

    fetch(`/api/stores/${specificStore}/auctions/${auctionProd}/currentHighestBid`)
        .then(res => res.json())
        .then(json => this.currentHighestBid = json)
        .catch(() => alert("Feil ved henting av bud"));
  }
});


</script>
<style>
img.cover-image-frontpage {
  height: auto;
  width: 35%;
  padding-bottom: 20px;
  max-height: auto;
}
.itemblock {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  display: block;

  padding: 16px;
  margin: auto;
  background: #0e0e0e;
  font-size: 1.25em;
  text-decoration: none;
  color: white;

  border-bottom: 1px solid var(--gold-color);
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}
.itemblock p {
  float: left;
}
.closed {
  display: flex;
  flex-flow: column;
  text-align: center;
  margin: 5px;
  padding: 10px;
  position: relative;
  bottom: 0;
  border-top: #FFD700 solid 1px;
}
.itemblock-wrapper {
  width: 45%;
  display: flex;
  flex-flow: column;
  float: right;
}
</style>
