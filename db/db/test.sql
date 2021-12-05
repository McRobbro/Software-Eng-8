--
-- File generated with SQLiteStudio v3.3.3 on Sun Dec 5 11:54:59 2021
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: auction
CREATE TABLE auction (auctionId INTEGER PRIMARY KEY AUTOINCREMENT, storeId INTEGER NOT NULL, productId INTEGER NOT NULL, startPrice DOUBLE, start_time text NOT NULL, end_time text NOT NULL, FOREIGN KEY (storeId) REFERENCES store (storeId), FOREIGN KEY (productId) REFERENCES product (productId));
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (95, 5, 31, 300.0, '25-11-2021 17:00:00', '26-11-2021 18:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (96, 1, 1, 800.0, '28-11-2021 20:00:00', '29-11-2021 20:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (97, 8, 1, 800.0, '16-11-2021 17:00:00', '18-11-2021 20:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (98, 8, 1, 800.0, '16-11-2021 17:00:00', '18-11-2021 20:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (99, 8, 1, 800.0, '16-11-2021 17:00:00', '18-11-2021 20:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (100, 8, 1, 800.0, '16-11-2021 17:00:00', '18-11-2021 20:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (101, 8, 1, 800.0, '16-11-2021 17:00:00', '18-11-2021 20:30:00');
INSERT INTO auction (auctionId, storeId, productId, startPrice, start_time, end_time) VALUES (102, 8, 1, 800.0, '16-11-2021 17:00:00', '18-11-2021 20:30:00');

-- Table: bid
CREATE TABLE bid (
    bidId INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    auctionId INTEGER NOT NULL,
    amount DOUBLE NOT NULL,
    FOREIGN KEY (userId) REFERENCES user(userId),
    FOREIGN KEY (auctionId) REFERENCES auction(auctionId)
    
);
INSERT INTO bid (bidId, userId, auctionId, amount) VALUES (61, 100, 95, 600.0);
INSERT INTO bid (bidId, userId, auctionId, amount) VALUES (62, 100, 95, 1500.0);
INSERT INTO bid (bidId, userId, auctionId, amount) VALUES (70, 100, 95, 1600.0);
INSERT INTO bid (bidId, userId, auctionId, amount) VALUES (71, 100, 95, 1700.0);
INSERT INTO bid (bidId, userId, auctionId, amount) VALUES (93, 100, 96, 800.0);
INSERT INTO bid (bidId, userId, auctionId, amount) VALUES (94, 100, 96, 900.0);

-- Table: order
CREATE TABLE `order`(
  orderId INTEGER PRIMARY KEY AUTOINCREMENT,
  orderDate DATE NOT NULL,
  userId,
  storeId,
  FOREIGN KEY (userId) REFERENCES user(userId)
  FOREIGN KEY (storeId) REFERENCES store(storeId)
);

-- Table: orderline
CREATE TABLE orderline (
orderId INTEGER NOT NULL, 
productId INTEGER NOT NULL,
storeId INTEGER NOT NULL,
PRIMARY KEY(orderId, productId, storeId),
FOREIGN KEY (orderId) REFERENCES `order`(orderId),
FOREIGN KEY (productId) REFERENCES product(productId)
FOREIGN KEY (storeId) REFERENCES store(storeId)
);

-- Table: product
CREATE TABLE "product" (
	"productId"	INTEGER,
	"storeId"	INT NOT NULL,
	"productSlug"	VARCHAR(45) NOT NULL UNIQUE,
	"productName"	VARCHAR(45) NOT NULL,
	"productImage"	VARCHAR(1024) NOT NULL,
	"productDescription"	VARCHAR(255) NOT NULL,
	"productCategory"	VARCHAR(45) NOT NULL,
	"productPrice"	INT NOT NULL,
	PRIMARY KEY("productId" AUTOINCREMENT),
	FOREIGN KEY("storeId") REFERENCES "store"("storeId")
);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (1, 1, 'LUX-taklampe', 'LUX taklampe', '', 'loremipsum', 'Belysning', 500);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (2, 1, 'Biedermeierbenk', 'Biedermeierbenk', '', 'loremipsum', 'Benk', 3000);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (4, 2, 'Da-Vinci-Barglob-modell-102', 'Da Vinci Barglob', '', 'loremipsum', 'Serveringsvogner', 3300);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (5, 3, 'Krystallysekrone-Empire-Wien', 'Krystallysekrone Empire Wien', '', 'loremipsum', 'taklamper', 1300);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (6, 3, 'Antikk-løve-dørknopper', 'Antikk løve dørknopper', '', 'loremipsum', 'dørknopper', 599);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (28, 3, 'philip', 'ffgdg', 'https://www.boconcept.com/on/demandware.static/-/Sites-master-catalog/default/dwcfe950b0/images/1100000/1106997.jpg', 'sdfdsf', 'gdfdfg', 300);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (29, 0, 'DummySlug', 'DummyName', 'DummyImg', 'DummyDesc', 'DummyCat', 12.09);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (30, 1, 'phila', 'phila', 'https://www.boconcept.com/on/demandware.static/-/Sites-master-catalog/default/dwcfe950b0/images/1100000/1106997.jpg', 'sdfdsf', 'gdfdfg', 111);
INSERT INTO product (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice) VALUES (31, 5, 'Lampe', 'Lampe', 'https://www.boconcept.com/on/demandware.static/-/Sites-master-catalog/default/dwcfe950b0/images/1100000/1106997.jpg', 'fin lampe', 'Lamper', 500);

-- Table: store
CREATE TABLE store (
    storeId          INTEGER        PRIMARY KEY AUTOINCREMENT
                                    NOT NULL,
    slug             VARCHAR (255)  NOT NULL UNIQUE
                                    NOT NULL,
    storeName        VARCHAR (45)   NOT NULL,
    storeImage       VARCHAR (1024),
    storeDescription VARCHAR (255)
);
INSERT INTO store (storeId, slug, storeName, storeImage, storeDescription) VALUES (1, 'johansens-butikk', 'Ogren', 'https://image.shutterstock.com/shutterstock/photos/230619400/display_1500/stock-vector-shop-icon-store-230619400.jpg', 'lorem ipsum lorem ipsum lorem ipsum');
INSERT INTO store (storeId, slug, storeName, storeImage, storeDescription) VALUES (2, 'Fredriks-butikk', 'Colbert', '', 'lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum');
INSERT INTO store (storeId, slug, storeName, storeImage, storeDescription) VALUES (5, 'Andreas-butikk', 'Andreas', '', 'lorem ipsum');
INSERT INTO store (storeId, slug, storeName, storeImage, storeDescription) VALUES (8, 'Heidi-butikk', 'Heidi', '', 'lorem ipsum');

-- Table: store_user
CREATE TABLE store_user (
    storeUserId INTEGER      PRIMARY KEY AUTOINCREMENT
                             NOT NULL,
    userId      INT          NOT NULL,
    storeId     INT          NOT NULL,
    userRole    VARCHAR (45),
    FOREIGN KEY (userId) REFERENCES user(userId),
    FOREIGN KEY (storeId) REFERENCES store(storeId)
);
INSERT INTO store_user (storeUserId, userId, storeId, userRole) VALUES (1001, 100, 1, 'consumer');
INSERT INTO store_user (storeUserId, userId, storeId, userRole) VALUES (1002, 101, 2, 'owner');
INSERT INTO store_user (storeUserId, userId, storeId, userRole) VALUES (1003, 102, 3, 'platform_admin');

-- Table: user
CREATE TABLE user (
    userId   INTEGER      PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    email    VARCHAR (45) NOT NULL,
    username VARCHAR (20) NOT NULL,
    password VARCHAR (25) NOT NULL
);
INSERT INTO user (userId, email, username, password) VALUES (100, 'Alejandra.Alejandra@yopmail.com', 'Alejandra', 'lbrddeebslPdr!');
INSERT INTO user (userId, email, username, password) VALUES (101, 'Collen.Collen@yopmail.com', 'Collen', 'sPooEPsasoswr');
INSERT INTO user (userId, email, username, password) VALUES (102, 'Stevana.Stevana@yopmail.com', 'Stevana', 'bslbroPodEwwsr');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
