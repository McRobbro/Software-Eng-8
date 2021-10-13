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



