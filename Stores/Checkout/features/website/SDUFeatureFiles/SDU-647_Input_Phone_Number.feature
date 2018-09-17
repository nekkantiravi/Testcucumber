   #Author: Stores Domain Checkout Team
   #Story: SDU-647 - Phone Number Input Field
   #Date Created: 06/09/2017
   #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-647 @dry-run
 Feature:  As an associate, I want a clear input of phone numbers,
   so that I know when I have input a valid phone number and can advance to the next field.

   @Macy's @Send
   Scenario Outline: Macy's - I input too few or too many characters on the shipping information screen
   then I see an error message
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input "<Input>" in the "<BillingField>" for too many or few characters
     Then I verify too many or few error reads "<ErrorMsg>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |Input                          | BillingField           | ErrorMsg                       |
       | 1                             |  PhoneNumberShort      | Minimum length required is 10  |
       | 33080827412                   |  PhoneNumberLong       | Too many characters.           |


   @Macy's @Send
   Scenario Outline: Macy's - I input too few or too many characters on the billing information screen
   then I see an error message
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input "<Input>" in the "<BillingField>" for too many or few characters
     Then I verify too many or few error reads "<ErrorMsg>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |Input                          | BillingField           | ErrorMsg                       |
       | 1                             |  PhoneNumberShort      | Minimum length required is 10  |
       | 33080827412                   | PhoneNumberLong        | Too many characters.           |

   @Macy's @Send
   Scenario: Macy's - After inputting 3 characters I see the first - in the phone number field on shipping info prompt
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input three characters into the Phone Number field
     Then I can see the first dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - After inputting 6 characters I see a second - in the phone number field on shipping info prompt
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input six characters into the Phone Number field
     Then I can see the second dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - After inputting 3 characters I see the first - in the phone number field on billing info prompt
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input three characters into the Phone Number field
     Then I can see the first dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario: Macy's - After inputting 6 characters I see a second - in the phone number field on billing info prompt
     Given I am on "Macy's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input six characters into the Phone Number field
     Then I can see the second dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - I input too few or too many characters on the shipping information screen
   then I see an error message
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input "<Input>" in the "<BillingField>" for too many or few characters
     Then I verify too many or few error reads "<ErrorMsg>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |Input                          | BillingField           | ErrorMsg                       |
       | 1                             |  PhoneNumberShort      | Minimum length required is 10  |
       | 33080827412                   |  PhoneNumberLong       | Too many characters.           |

   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - I input too few or too many characters on the billing information screen then
   I see an error message
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input "<Input>" in the "<BillingField>" for too many or few characters
     Then I verify too many or few error reads "<ErrorMsg>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

     Examples:
       |Input                          | BillingField           | ErrorMsg                       |
       | 1                             |  PhoneNumberShort      | Minimum length required is 10  |
       | 33080827412                   | PhoneNumberLong        | Too many characters.           |

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - After inputting 3 characters I see the first - in the phone number field
   on shipping info prompt
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input three characters into the Phone Number field
     Then I can see the first dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - After inputting 6 characters I see a second - in the phone number field
   on shipping info prompt
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input six characters into the Phone Number field
     Then I can see the second dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - After inputting 3 characters I see the first - in the phone number field
   on billing info prompt
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input three characters into the Phone Number field
     Then I can see the first dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - After inputting 6 characters I see a second - in the phone number field
   on billing info prompt
     Given I am on "Bloomingdale's Sales Trans"
     When I add an item to the Checkout bag
     Then I can see the added to bag toast message
     And the toast message fades away after 2 seconds
     When I click on the bag icon
     Then I can see the checkout button
     When I press the checkout button
     Then I see the Shipping Method Overlay
     When I select Free Shipping
     And I click on Next Step button
     Then I see the Shipping information Overlay
     When I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input six characters into the Phone Number field
     Then I can see the second dash
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page