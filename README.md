CSC1035: Practical 3 - EPOS system for a small business
====================
This project is a back-end implementation of an EPOS system customised to the needs of our client, 
the owner of the "Tricky Trinkets" shop.

It's a simple system allowing owners of small businesses to carry out transactions such a selling an item and 
generating a receipt, as well as, keeping record of all previous purchases and keeping the stock up to date.

Since it is only a back-end version of the program, all user operations are carried out via the console.

Dependencies and languages:
-------------
* [Hibernate version: 5.4.6.Final](https://hibernate.org/)
* [JUnit version: 4.11](https://junit.org/junit4/)
* [MySQL version: 5.1.48](https://www.mysql.com/products/)
* [Java version: 12.02.2](https://docs.oracle.com/en/java/javase/12/)

See [pom.xml](pom.xml) for more details

How to use:
-----------
This program has following functionality:
* [Add a new product to the database](#add-a-new-product-to-the-database)
* [Display current categories](#display-current-categories)
* [Display all products in chosen category](#display-all-products-in-chosen-category)
* [Update product attributes](#update-product-attributes)
* [Search for product ID by name](#search-for-product-id-by-name)
* [Delete a product from the database](#delete-a-product-from-the-database)
* [Add products to the basket](#add-products-to-the-basket)
* [Remove items from the basket](#remove-items-from-the-basket)
* [Checkout items from the basket](#checkout-items-from-the-basket)
* [Generate a receipt](#generate-a-receipt)
* [Keep track of purchase history](#keep-track-of-purchase-history)

##### Add a new product to the database
The user can add any product to their product table after specifying all of its attributes: name, category, whether it's
perishable or not, cost, stock and selling price.
> In the background: this uses the create(E e) method which adds a given instance of the product object to the database.
##### Display current categories
The user can check what categories are currently in use in his stock.
This also makes it easier to search for a product if the user does not remember the name of the product or which category it belongs to or if the customer
asks about a certain type of product. 

A list of all categories with corresponding numbers is displayed in the console
for the user to then choose which category they want to browse items from.
> In the background: this uses the getCategories() and printCategories() methods which save all product categories 
currently found in the database and prints out every item with pointing to an integer for further use by the user.
##### Display all products in chosen category
Allows user to see what products fall under a chosen category. It gives the user a list of the names and IDs of the products,
which enables them to perform further operations using the product ID.
> In the background: this used the readProduct(int userCat) method which extracts the names and IDs of the product with
the specified category as one of their attributes.
##### Update product attributes
The user can change any of the details about any of the products in their stock by specifying the ID of the product, 
which detail they want to change and to what new value.

This way the user can easily update the stock of a product independently of actual customer purchases (e.g. they had to 
throw away some out-of-date products or gave them away to someone without a customer transaction etc.)
> In the background: this uses the updateProduct(int id, String field, String newValue) method with string queries which
allow for the user input to supply the fields
##### Search for product ID by name
The user can get the ID and a full name of a product by its name or just a partial name of it. 

This is useful for the user does not have to memorise all their products' IDs, nor they have to remember full product
names and still be able to carry out operations.
> In the background: this uses the readByProdNameReturnId(String name) method which prints out all the product names and
IDs of the product table records containing the String value provided by the user.
##### Delete a product from the database
The user can "delete" a product from their stock if they no longer want to sell it. 

The record of this product along with
its ID will still stay in the database to enable the user to check previous transactions involving this product and 
access receipts containing this product, e.g. in case of past customer's inquiry.
> In the background: this used the delete(int id) method which sets the stock of the "deleted" item to zero (0) to 
overcome key constraints between the PurchaseHistory, Receipt and Product tables.
##### Add products to the basket
When a customer wants to buy some products, the user adds them to a basket before checkout. It is an equivalent of 
barcode scanning in a supermarket but instead of a barcode, the product ID and quantity are being used.

This enables the user to check what is on the to-buy list of the current customer, add up the total amount to be paid,
as well as, allow for any changes of mind from the customer. 
> In the background: this uses the addToBasket(int id, in qty) method which checks the stock of the item to be added 
and if there is enough stock for the desired quantity to be purchased, the item gets added to the basket
##### Remove items from the basket
In case of the customer changing their mind before finalising the transaction, the user can delete some of the items 
from the basket if the customer no longer wants to buy them. The user only needs to give the ID of the product and the 
quantity (how many instances of this product they want to delete). 

The user will be asked whether they want to delete
multiple different products.
> In the background: this uses the removeFromBasket(int id, int qty) method.
##### Checkout items from the basket
When the customer is happy with their basket and want to move forward with their purchase, this takes care of the stock
update, receipt generation and purchase history generation for the user. It also automatically checks whether the customer
has paid enough money for the transaction to go through.
>In the background: this uses the checkout(double paid) method, which checks whether the basket is not empty, calculates
the total amount due by customer and whether they paid enough, then calls the generateReceipt and generatePurchaseHistory
methods to create a receipt record in the database and create a transaction record.
##### Generate a receipt
The user can get a printed receipt of a purchase in the console, as well as have all of their receipts saved in
a back-up text or spreadsheet files. 

This is useful when answering queries of past customers or wanting to keep track of what each customer bought, when and
in what quantity.
> In the background: the receipt is printed into the console by the ReceiptUtil.java and saved to the 2 different files
by the FileWriter.java's writeReceiptToCSV() and writetoTXT() methods.
##### Keep track of purchase history
User might want to keep track of the purchase history to be able to make analysis-based interpretations about their business,
such as which products to discontinue, how to manage prices and general marketing. 

The purchase history is being backed up in a form of spreadsheet file. 
> In the background: this uses the writePurchaseHistoryToCSV method from the FileWriter.java.=
     
Running the tests:
------------------
Tests are stored within the Tests directory, in the Tests.java class. The readme in the tests directory contains a list
of methods that are in the class, as well as the expected result.

Built With:
-----------
IntelliJ - IDE
Maven - Dependency management

License:
--------
See [LICENSE.md](LICENSE.md) for more details

Acknowledgements:
-----------------
* Coraline Ada Ehmke: [Contibutor Covenant - CODE OF CONDUCT](https://www.contributor-covenant.org/version/1/4/code-of-conduct/)
