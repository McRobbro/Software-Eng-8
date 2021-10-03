# Kravspesifikasjon

### Butikkeiere
1. Butikkeiere skal kunne opprette sin egen nettbutikk i platformen
2. Butikkeiere skal kunne liste sine produkter for salg
3. Butikkeiere skal kunne selge sine produkter til sluttbuker via tradisjonell metode
    1. Liste et produkt med en beskrivelse, et bilde og en pris
    2. La sluttbrukere legge produktet i sin handlevogn
    3. La sluttbrukere kjøpe produktene i sin handlevogn
4. Butikkeiere skal kunne selge sine produkter til sluttbrukere via auksjon
    1. Starte en tidsbegrenset auksjon
    2. Motta bud på produkt
    3. Deklarere vinneren av en auksjon
    4. Kommunisere til sluttbrukeren som vant auksjonen at han/hun vant


### Sluttbrukere
1. Sluttbrukere skal kunne opprette en bruker i butikkeierenes nettbutikk
2. Sluttbrukere skal kunne se produkter for salg
3. Sluttbrukere skal kunne se pågående auksjoner
4. Sluttbrukere skal kunne kjøpe produkter
5. Sluttbrukere skal kunne delta i auksjoner

### Platform admin
1. Platform admin skal kunne se alle nettbutikker og deres informasjon (stores, store_admins, users)


## Database tabeller

stores
- id
- name
- address
- description
- slug (antikviteter.no/(johansens-butikk))

products
- id
- title
- description
- image
- stock
- category_id
- store_id

auctions
- id
- store_id
- product_id
- start_time
- end_time
- winner_id

bids
- id
- user_id
- auction_id
- amount

orders
- id
- user_id
- store_id
- date

order_line
- order_id
- product_id
- price
- amount

platform_admins
- id
- role (???)
- name
- email
- address
- phone_number
- password_hash 

store_admins
- id
- store_id
- role (owner, employee)
- name
- email
- address
- phone_number
- password_hash

users
- id
- store_id
- name
- email
- address
- phone_number
- password_hash


## Filstruktur/Klasseoversikt

#### Routes:
/stores/[store]/ -> home page (banner, featured products, active actions)
/stores/[store]/products -> all products (heading, searchable list of products)
/stores/[store]/products/[id] -> product page (information about a specific product, add to cart, buy button)
/stores/[store]/auctions -> list all auctions (action information, product information, link to auction)
/stores/[store]/actions/[id] -> auction page (information about the auction, product information, bid form, bid history, live??)
/stores/[store]/account -> account page / user adminstration page (User information, order history, active bids)

/stores/[store]/admin-account -> account page for admins (user information, store information, store information form, add/remove/update/delete product form, add/remove/update/delete + start/stop auction)

/platform-admin-account -> platform admin account page
/stores -> list all stores (list all stores, create new store)


/login -> log user into the system (shared by all types of users)
/logout -> log user out of the system (shared by all types of users)

#### controllers/

StoresController
- Create a store
- List all stores

StoreController
- Update store
- List store info
- Delete store

ProductController
- Add product to store
- Update product 
- Delete product
- List product information

ActionController
- Create auction
- Update auction
- Delete auction
- List auction information
- Start auction
- End auction
- Get auction winner
- Get auction bids

OrderController
- create order for one or more products

AuthenticationController
- login user
- logout user

// User account creation logic (maybe just have one user table and one UserController???)
UserAccountController
- create user

StoreAdminController
- create store admin

PlatformAdminController
- create platform admin


#### models/
Store
Product
Auction
Bid
Order
OrderLine
User (or different user types)


#### repository/ (read/write to the database)
StoreRepository
ProductRepository
AuctionRepository
BidRepository
OrderRepository
UserRepository

#### service/

AuthenticationService
AuctionService
OrderService

#### utils/
Validation utilities (not important)
Date and time utilities
- How long until auction is completed

#### config/
- Database ip and port
- Database credentials (read from .env file)

#### resources
front end application (Vue)

assets
    /images
    /svg
    /pdf


#### Tests

Run test requests against controllers and assert on responses from the controllers

Run service methods and assert on side effects and return values



























