
CREATE TABLE user (
    userId int not null,
    email varchar(45) not null,
	username varchar(20) not null,
    password varchar(25) not null,
    PRIMARY KEY(userId)
);



CREATE TABLE store (
	storeId int not null,
	slug varchar(255) unique not null,
    storeName varchar(45) not null,
    storeImage varchar(1024),
    storeDescription varchar(255),
    PRIMARY KEY(storeId)
);



CREATE TABLE store_user (
	storeUserId int not null,
    userId int not null,
    storeId int not null,
    userRole varchar(45),
	FOREIGN KEY(userId) REFERENCES user(userId),
	FOREIGN KEY(storeId) REFERENCES store(storeId),
	PRIMARY KEY(storeUserId)
	
);






