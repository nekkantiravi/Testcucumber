 #Author: Stores Domain Checkout Team
      #Story: SDU-60 - Checkout :: Input Billing Information
      #Date Created: 04/18/2017
      #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-60
 Feature: As an associate, I want an efficient way to input a customer's billing information,
   so that I can quickly complete her order and reduce transaction processing time.


   @Macy's @Send
   Scenario: Macy's - View error messages when you press next before inputting any billing information
     Given I am on "Macy's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I click on Next Step button
     Then I see the empty information error messages
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page

   @Macy's @Send
   Scenario Outline: Macy's - When I input Invalid Characters I see an error message
     Given I am on "Macy's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input "<Input>" in the "<BillingField>"
     Then I verify special character error displays with the Invalid "<Input>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page


     Examples:
       | Input | BillingField |
       | 1     | FirstName    |
       | 1     | LastName     |
       | 1     | City         |
       | #     | City         |
       | #     | State        |
       | *     | Zip          |
       | #     | PhoneNumber  |
       | A     | PhoneNumber  |


   @Macy's @Send
   Scenario Outline: Macy's - When I input invalid format I see an error message
     Given I am on "Macy's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input "<Input>" in the "<BillingField>" for invalid format
     Then I verify invalid format error reads "<InvalidError>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page


     Examples:
       | Input                  | BillingField | InvalidError           |
       | 123                    | Address1     | Invalid Address line 1 |
       | mainst                 | Address1     | Invalid Address line 1 |
       | 123mainst              | Address1     | Invalid Address line 1 |
       | 123 main st            | Address1     | Pass                   |
       | Cleveland              | City         | Pass                   |
       | The Land               | City         | Pass                   |
       #| ZZ          |State                  |Invalid State.      |
       | OH                     | State        | Pass                   |
       | abc123                 | ZipCode      | Invalid Zip Code       |
       | 12345                  | ZipCode      | Pass                   |
       | 123456                 | ZipCode      | Invalid Zip Code       |
       | T1A 7L4                | ZipCode      | Pass                   |
       | 1234567890             | ZipCode      | Pass                   |
       | T2T5Y7                 | ZipCode      | Pass                   |
       | S7N1M7                 | ZipCode      | Pass                   |
       | M4B1V4                 | ZipCode      | Pass                   |
       | 1111111111             | PhoneNumber  | Invalid Phone Number   |
       | 5552015689             | PhoneNumber  | Invalid Phone Number   |
       | 2008870879             | PhoneNumber  | Invalid Phone Number   |
       | 3301990879             | PhoneNumber  | Invalid Phone Number   |
       | 3308080405             | PhoneNumber  | Pass                   |
       | 3308082741             | PhoneNumber  | Pass                   |
       | mike@gmail.com-        | Email        | Invalid Email address  |
       | mike@gmail..com        | Email        | Invalid Email address  |
       | mike@.gmail.com        | Email        | Invalid Email address  |
       | mike@@gmail.com        | Email        | Invalid Email address  |
       | mike.@gmail.com        | Email        | Invalid Email address  |
       | !#$%&@gmail.com        | Email        | Invalid Email address  |
       | @gmail.com             | Email        | Invalid Email address  |
       | .@gmail.com            | Email        | Invalid Email address  |
       | mike@google.c          | Email        | Invalid Email address  |
       | Mike@google.com        | Pass         | Pass                   |
       | Mike@google.org        | Pass         | Pass                   |
       | Mike-Dillon@google.net | Email        | Pass                   |


   @Macy's @Send
   Scenario Outline: Macy's - When I input too few or too many characters I see an error message
     Given I am on "Macy's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
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
       | Input                                      | BillingField     | ErrorMsg                      |
       | twentyonecharactersdd                      | FirstNameLong    | Too many characters.          |
       | twentyonecharactersdd                      | LastNameLong     | Too many characters.          |
       | 1                                          | Address1Short    | Minimum length required is 3  |
       | 1fourtycharactersmaximumhdhdhdhdhdhdhdhdd  | Address1Long     | Too many characters.          |
       | U                                          | CityShort        | Minimum length required is 2  |
       | s     +' '                                 | CityShort        | Minimum length required is 2  |
       | twentysixcharactershshshsd                 | CityLong         | Too many characters.          |
       | O                                          | StateShort       | Minimum length required is 2  |
       | OHD                                        | StateLong        | Too many characters.          |
       | 1                                          | ZipShort         | Minimum length required is 5  |
       | 440537890873                               | ZipLong          | Too many characters.          |
       | 1                                          | PhoneNumberShort | Minimum length required is 10 |
       | 330808274127374                            | PhoneNumberLong  | Too many characters.          |
       | 1                                          | EmailShort       | Minimum length required is 6  |
       | 1fourtycharactersmaximumhdhdhdhdhdhdhdhdd7 | EmailLong        | Too many characters.          |


   @Macy's @Send
   Scenario: Macy's - Billing Information is the same as Shipping
     Given I am on "Macy's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I click on Next Step button
     Then I see the Order Review Overlay
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page



# ###   @manual
# ###   Scenario: Manually Verify the correct Keyboard displays on the device.
# ###     Given I am on Sales Trans
# ###     And I checkout an item and arrive on the "Shipping Information" overlay
# ###     When I input the Shipping Information
# ###     And Press continue
# ###     Then I see the same as Shipping prompt
# ###     When I uncheck the same as shipping checkbox
# ###     And I press next steps
# ###     Then I can see the Input Billing Information screen
# ###     When I touch the first name field
# ###     Then I can see the Alpha keyboard
# ###     When I press the Last name Field
# ###     Then I can see the Alpha keyboard
# ###     When I press the address field
# ###     Then I can see the numeric keyboard
# ###     When I input a number and press the space key
# ###     Then I can see the Alpha keyboard
# ###     When I touch the City Field
# ###     Then I can see the Alpha keyboard
# ###     When I press the State field
# ###     Then I can see the Alpha keyboard
# ###     When I press the Zip Code field
# ###     Then I can see the numeric keyboard
# ###     When I press the phone number field
# ###     Then I see the numeric keyboard
# ###     When I press the email field
# ###     Then I see the alpha keyboard that has email shortcuts
# ###


   @Bloomingdale's @Send
   Scenario: Bloomingdale's - View error messages when you press next before inputting any billing information
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I click on Next Step button
     Then I see the empty information error messages
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page


   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - When I input Invalid Characters I see an error message
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input "<Input>" in the "<BillingField>"
     Then I see the red error rectangle
     Then I verify special character error displays with the Invalid "<Input>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page


     Examples:
       | Input | BillingField |
       | 1     | FirstName    |
       | 1     | LastName     |
       | 1     | City         |
       | #     | City         |
       | #     | State        |
       | *     | Zip          |
       | #     | PhoneNumber  |
       | A     | PhoneNumber  |


   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - When I input invalid format I see an error message
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I uncheck the same as shipping checkbox
     Then I can see the Input Billing Information screen
     When I input "<Input>" in the "<BillingField>" for invalid format
     Then I verify invalid format error reads "<InvalidError>"
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page


     Examples:
       | Input                  | BillingField | InvalidError           |
       | 123                    | Address1     | Invalid Address line 1 |
       | mainst                 | Address1     | Invalid Address line 1 |
       | 123mainst              | Address1     | Invalid Address line 1 |
       | 123 main st            | Address1     | Pass                   |
       | Cleveland              | City         | Pass                   |
       | The Land               | City         | Pass                   |
       #| ZZ          |State                  |Invalid State.      |
       | OH                     | State        | Pass                   |
       | abc123                 | ZipCode      | Invalid Zip Code       |
       | 12345                  | ZipCode      | Pass                   |
       | 123456                 | ZipCode      | Invalid Zip Code       |
       | T1A 7L4                | ZipCode      | Pass                   |
       | 1234567890             | ZipCode      | Pass                   |
       | T2T5Y7                 | ZipCode      | Pass                   |
       | S7N1M7                 | ZipCode      | Pass                   |
       | M4B1V4                 | ZipCode      | Pass                   |
       | 1111111111             | PhoneNumber  | Invalid Phone Number   |
       | 5552015689             | PhoneNumber  | Invalid Phone Number   |
       | 2008870879             | PhoneNumber  | Invalid Phone Number   |
       | 3301990879             | PhoneNumber  | Invalid Phone Number   |
       | 3308080405             | PhoneNumber  | Pass                   |
       | 3308082741             | PhoneNumber  | Pass                   |
       | mike@gmail.com-        | Email        | Invalid Email address  |
       | mike@gmail..com        | Email        | Invalid Email address  |
       | mike@.gmail.com        | Email        | Invalid Email address  |
       | mike@@gmail.com        | Email        | Invalid Email address  |
       | mike.@gmail.com        | Email        | Invalid Email address  |
       | !#$%&@gmail.com        | Email        | Invalid Email address  |
       | @gmail.com             | Email        | Invalid Email address  |
       | .@gmail.com            | Email        | Invalid Email address  |
       | mike@google.c          | Email        | Invalid Email address  |
       | Mike@google.com        | Pass         | Pass                   |
       | Mike@google.org        | Pass         | Pass                   |
       | Mike-Dillon@google.net | Email        | Pass                   |


   @Bloomingdale's @Send
   Scenario Outline: Bloomingdale's - When I input too few or too many characters I see an error message
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
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
       | Input                                      | BillingField     | ErrorMsg                      |
       | twentyonecharactersdd                      | FirstNameLong    | Too many characters.          |
       | twentyonecharactersdd                      | LastNameLong     | Too many characters.          |
       | 1                                          | Address1Short    | Minimum length required is 3  |
       | 1fourtycharactersmaximumhdhdhdhdhdhdhdhdd  | Address1Long     | Too many characters.          |
       | U                                          | CityShort        | Minimum length required is 2  |
       | twentysixcharactershshshsd                 | CityLong         | Too many characters.          |
       | O                                          | StateShort       | Minimum length required is 2  |
       | OHD                                        | StateLong        | Too many characters.          |
       | 1                                          | ZipShort         | Minimum length required is 5  |
       | 440537890873                               | ZipLong          | Too many characters.          |
       | 1                                          | PhoneNumberShort | Minimum length required is 10 |
       | 330808274127374                            | PhoneNumberLong  | Too many characters.          |
       | 1                                          | EmailShort       | Minimum length required is 6  |
       | 1fourtycharactersmaximumhdhdhdhdhdhdhdhdd7 | EmailLong        | Too many characters.          |


   @Bloomingdale's @Send
   Scenario: Bloomingdale's - Billing Information is the same as Shipping
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item and arrive on the "Shipping Information" overlay
     And I input the Shipping Information
     And I click on Next Step button
     Then I see the same as Shipping prompt
     When I click on Next Step button
     Then I see the Order Review Overlay
     When I close the overlay
     And I call Cancel
     Then I can see the sales trans landing page
