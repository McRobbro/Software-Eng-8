CREATE TABLE user (
    userId   INTEGER      PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    email    VARCHAR (45) NOT NULL,
    username VARCHAR (20) NOT NULL,
    password VARCHAR (25) NOT NULL
);

CREATE TABLE store (
    storeId          INTEGER        PRIMARY KEY AUTOINCREMENT
                                    NOT NULL,
    slug             VARCHAR (255)  NOT NULL UNIQUE
                                    NOT NULL,
    storeName        VARCHAR (45)   NOT NULL,
    storeImage       VARCHAR (1024),
    storeDescription VARCHAR (255)
);

CREATE TABLE store_user (
    storeUserId INTEGER      PRIMARY KEY AUTOINCREMENT
                             NOT NULL,
    userId      INT          NOT NULL,
    storeId     INT          NOT NULL,
    userRole    VARCHAR (45),
    FOREIGN KEY (userId) REFERENCES user(userId),
    FOREIGN KEY (storeId) REFERENCES store(storeId)
);

CREATE TABLE products (
    productId          INTEGER        PRIMARY KEY AUTOINCREMENT,
    storeId            INT            NOT NULL,
    productSlug        VARCHAR (45)   NOT NULL
                                      UNIQUE,
    productName        VARCHAR (45)   NOT NULL,
    productImage       VARCHAR (1024) NOT NULL,
    productDescription VARCHAR (255)  NOT NULL,
    productCategory    VARCHAR (45)   NOT NULL,
    productPrice       INT            NOT NULL,
    FOREIGN KEY (
        storeId
    )
    REFERENCES store (storeId) 
);

CREATE TABLE orders (
  orderId INTEGER PRIMARY KEY AUTOINCREMENT,
  orderDate DATE NOT NULL,
  userId,
  storeId,  
  FOREIGN KEY (userId) REFERENCES user(userId)
  FOREIGN KEY (storeId) REFERENCES store(storeId)
); 

CREATE TABLE orderline (
orderId INTEGER NOT NULL, 
productId INTEGER NOT NULL,
storeId INTEGER NOT NULL,
PRIMARY KEY(orderId, productId, storeId),
FOREIGN KEY (orderId) REFERENCES orders(orderId),
FOREIGN KEY (productId) REFERENCES product(productId)
FOREIGN KEY (storeId) REFERENCES store(storeId)
); 


