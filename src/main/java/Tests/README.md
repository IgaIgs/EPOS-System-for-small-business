##Testing


Tests are kept within a separate directory of our main EPOS system, as per the client requirements :

`Leave any testing code in the main section of the program, all test code must be in a separate directory
`
-----

###These are the tests being ran in order to check the database is fully functional :


#####Create a new product within the database

>Expected result : A new product is created



#####Read / search for products within the database using name of product

>Expected result : Product IDs are returned of products which contain what we searched for



#####Update existing products within the database

>Expected result : Any of the fields of a product can be updated based on parameters passed to the method.



#####Updates on non-existent IDs don't cause crashes

>Expected result : No crash will occur



#####Delete products within the database

>Expected result : Product is deleted



#####Produce a receipt of a transaction within the database

>Expected result : A receipt is created in the receipts table



#####Updating stock levels without use of a purchase

>Expected result : Stock field is updated with new amount. (Same method as other updates, although this was a specific
client requirement)



#####Selling an item and producing a receipt

>Expected result : Stock quantity of that product will decrease and a receipt will be produced. Item is added to
purchase history



#####Selling more items than are in stock

>Expected result : User will not be allowed to do this and a programmed message will appear in the terminal saying
there is insufficient stock for this.



#####Customer gives insufficient funds

>Expected result : A message will appear in the terminal.



#####Selling multiple items at once

>Expected result : Items are added to the basket and then processed. Previous checks of whether or not we have
enough stock and customer has given us sufficient money will occur, receipt is produced and products are added to
our purchase history


------

###Other Notes
All the tests can be ran in a single operation if required, however I would recommend only running one of the tests at a
time for easier tracking of successful operations, as it may be difficult to notice individual changes in the database
when all tests are ran concurrently.