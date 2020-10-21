Feature: PlaceOrder

Keywords Summary : demo blaze order placement test

Scenario: place order on Demoblaze and log details

Given User navigated to Demoblaze.COM
When I click on "Laptops"
Then I click on required laptops
      | Sony vaio i5                 |
When I click on "Home "
And I wait for page load
When I click on "Laptops"
Then I click on required laptops
      | Dell i7 8gb                 |
When I click on "Cart"
Then I click on delete for product "Dell i7 8gb"
When I click on "Place Order"
Then I fill the web form fields
When I click on "Purchase"
Then I verfiy the Amount
When I click on "OK"
Then I close the browser