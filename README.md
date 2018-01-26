# ShoppingCart
This is a project of modelling a shopping cart for e-shop. For launching this project 
you should deploy /build/libs/Basket.war file on a web-server (for example, in case of
Tomcat it is necessary to place file in /webapps folder, for other servers - see the documentation).

The project was tested on PostgreSQL-type database with the name "basket" on 5432 port. If you wish to
use this application in another enviroment, you should appropriately change the data in 
/src/main/resources/application.properties and rebuild the .war file.

It is supposed that database contains three tables with the names "products", "orders" and "orderitems";
schemas for those tables and examples of their initial filling could be found at
/src/main/resources/create_database.sql.

When buyer visits a shop, server demonstrates him a list of available products from the "products" table
with their names and prices, and offer to put some of them into a virtual basket (which could be checked at any time).
After finishing the shopping buyer should write his name and phone number, which are stored in "orders" table,
waiting for human to execute an order. 
