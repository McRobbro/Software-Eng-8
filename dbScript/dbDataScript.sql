
/*user table*/
Insert Into user ( userId, username, email, password) Values ( 100,'Alejandra','Alejandra.Alejandra@yopmail.com','lbrddeebslPdr!' );
Insert Into user ( userId, username, email, password) Values ( 101,'Collen','Collen.Collen@yopmail.com','sPooEPsasoswr' );
Insert Into user ( userId, username, email, password) Values ( 102,'Stevana','Stevana.Stevana@yopmail.com','bslbroPodEwwsr' );

/*******************************/


/*store table*/
Insert Into store ( storeId, slug, storeName, storeImage, storeDescription) Values ( 1,'johansens-butikk','Ogren','null','lorem ipsum lorem ipsum lorem ipsum' );
Insert Into store ( storeId, slug, storeName, storeImage, storeDescription) Values ( 2,'philips-butikk','Sabella','null','lorem ipsumlorem ipsumlorem ipsum' );
Insert Into store ( storeId, slug, storeName, storeImage, storeDescription) Values ( 3,'Fredriks-butikk','Colbert','null','lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum' );

/*******************/


/*store user table*/
Insert Into store_user (storeUserId, userId, storeId, userRole) values (1001, 100, 1, "consumer");
Insert Into store_user (storeUserId, userId, storeId, userRole) values (1002, 101, 2, "owner");
Insert Into store_user (storeUserId, userId, storeId, userRole) values (1003, 102, 3, "platform_admin");

/*************************/

/*products*/

insert into products (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice)
                   VALUES (1, 1, "LUX-taklampe", "LUX taklampe", "imageUrl", "loremipsum", "Belysning", 500);
            
            
insert into products (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice)
            VALUES(2, 1, "Biedermeierbenk", "Biedermeierbenk", "imageUrl", "loremipsum", "Benk", 3000);
            

insert into products (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice)
            VALUES(3, 2, "Skuvseng", "Skuvseng", "imageUrl", "loremipsum", "seng", 10000);
            


insert into products (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice)
            VALUES(4, 2, "Da-Vinci-Barglob-modell-102", "Da Vinci Barglob", "imageUrl", "loremipsum", "Serveringsvogner", 3300);
            


insert into products (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice)
            VALUES(5, 3, "Krystallysekrone-Empire-Wien", "Krystallysekrone Empire Wien", "imageUrl", "loremipsum", "taklamper", 1300);
            

insert into products (productId, storeId, productSlug, productName, productImage, productDescription, productCategory, productPrice)
            VALUES(6, 3, "Antikk-løve-dørknopper", "Antikk løve dørknopper", "imageUrl", "loremipsum", "dørknopper", 599);

/**************************/

select * from user;

select * from store;

select * from store_user;