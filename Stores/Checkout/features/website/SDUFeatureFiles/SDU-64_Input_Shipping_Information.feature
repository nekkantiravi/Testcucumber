# Author: Ingrid - Stores Domain Checkout Team
# Story: SDU-64 Input Shipping Information
# Date Created: 04/13/2017
# Date Signed Off:


@Macy's
Feature: As an associate I want to add data in input boxes from input shipping address modal.


Background: Associate arrives on the Shipping Info overlay
  Given I am on "Macy's Sales Trans"
  When I checkout an item and arrive on the "Shipping Information" overlay


#  @Macy's
#  Scenario Outline: Macy's - Verify valid input boxes from input shipping address modal
#    And I input "<First name>", "<Last name>", "<Adress line 1>", "<Address line 2>", "<City>", "<State>", "<Zip Code>", "<Phone number>", "<Email Address>"
#    And I click on Next Step button
#    Then the Enter Billing Address overlay should be displayed
#    When I call Cancel
#    Then I can see the sales trans landing page
#  Examples:
#    | First name | Last name | Adress line 1 | Address line 2 | City            | State |Zip Code   | Phone number   | Email Address     |
#    | George     |   Cloe    |1 Str.america    |11,3            |San Francisco    |CA     |A1A 1A1    |202-345-6789    |abcdef@g.com       |
#    | George     |   Cloe    |2 Str.america    |                |San Francisco    |CA     |A1A1A1     |202-345-6789    |abcdef@g.com       |
#    | George     |   Cloe    |33 Str.america    |11,3            |San Francisco    |CA     |A1A 1A1     |202-345-6789    |                   |
#    | George     |   Cloe    |332 Str.america    |                |San Francisco    |CA     |A1A 1A1     |202-345-6789    |                   |
#    | George     |   Cloe    |44434 Str.america    |11,3            |San Francisco    |CA     |88889      | 202-345-6789  |abcdef@g.com       |
#    | George     |   Cloe    |54466 Str.america    |11,3            |San Francisco    |CA     |8888991234 | 202-345-6789   |abcdef@g.com       |
#
#
#  @Macy's
#  Scenario: Macy's -View error messages when you press next before inputting shipping information

#
#    And I click on Next Step button
#    Then I see the empty information error messages
#    When I call Cancel
#    Then I can see the sales trans landing page

#  @Macy's
#  Scenario Outline: Macy's -When I input invalid format I see an error message
#    And I input "<Input>" in the "<BillingField>" for invalid format
#    Then I verify invalid format error reads "<InvalidError>"
#    When I call Cancel
#    Then I can see the sales trans landing page
#
#    Examples:
#      |Input        | BillingField          | InvalidError   |
#      | 123         | Address1              | Invalid Address line 1  |
#      | mainst      | Address1              | Invalid Address line 1   |
#      | 123mainst   | Address1              | Invalid Address line 1   |
#      | 123 main st | Address1              | Pass               |
#      | Cleveland   | City                  | Pass               |
#      | The Land    | City                  | Pass               |
      ##| ZZ          |State                  |Invalid State.      |
#      | OH           |State                  |Pass                |
#      |abc123        | ZipCode               |Invalid Zip Code   |
#      | 12345         |ZipCode               |Pass   |
#      | 123456        |  ZipCode             |Invalid Zip Code   |
#      | 1234567890    |   ZipCode            |Pass   |
#      | T2T5Y7        |  ZipCode             |Pass   |
#      | S7N1M7        |    ZipCode           |Pass   |
#      | M4B1V4        |    ZipCode           |Pass   |
#      | 1111111111    |    PhoneNumber       | Invalid Phone Number |
#      | 5552015689    |    PhoneNumber       |  Invalid Phone Number|
#      | 2008870879    | PhoneNumber          | Invalid Phone Number |
#      | 3301990879    | PhoneNumber          | Invalid Phone Number |
#      | 3308080405    | PhoneNumber          | Pass   |
#      | 3308082741    | PhoneNumber          | Pass   |
#      | mike@gmail.com-   |     Email        | Invalid Email address |
#      | mike@gmail..com   | Email            | Invalid Email address |
#      | mike@.gmail.com  | Email             |Invalid Email address  |
#      | mike@@gmail.com  | Email             |Invalid Email address  |
#      | mike.@gmail.com  | Email             |Invalid Email address  |
#      | !#$%&@gmail.com  | Email             |Invalid Email address  |
#      | @gmail.com       | Email             |Invalid Email address  |
#      | .@gmail.com      | Email             |Invalid Email address  |
##      | mike@google.c    | Email             |Invalid Email address (optional).  |
#      | Mike@google.com  | Emali              | Pass|
#      | Mike@google.org  | Email              |Pass|
#      | Mike-Dillon@google.net  |Email       | Pass|

#  @Macy's
#Scenario Outline: Macy's - When I input too few or too many characters I see an error message
#  And I input "<Input>" in the "<BillingField>" for too many or few characters
#  Then I verify too many or few error reads "<ErrorMsg>"
#    When I call Cancel
#    Then I can see the sales trans landing page
#
#  Examples:
#    |Input                          | BillingField           | ErrorMsg   |
#    | twentyonecharactersdd         | FirstNameLong          | Too many characters. Maximum allowed is 20   |
#    | twentyonecharactersdd         | LastNameLong           | Too many characters. Maximum allowed is 20  |
#    | 1                             | Address1Short          | Minimum length required is 3   |
#    | 1fourtycharactersmaximumhdhdhdhdhdhdhdhdd  |   Address1Long  | Too many characters. Maximum allowed is 40  |
#    | U                             |  CityShort             | Minimum length required is 2    |
#    | twentysixcharactershshshsd    | CityLong               | Too many characters. Maximum allowed is 25 |
#    | O                             |  StateShort            | Minimum length required is 2     |
#    | OHD                           | StateLong              | Too many characters. Maximum allowed is 2 |
#    | 1                             |  ZipShort              | Minimum length required is 5   |
#    | 440537890873                  | ZipLong                | Too many characters. Maximum allowed is 11 |
#    | 1                             |  PhoneNumberShort      | Minimum length required is 10  |
#    | 330808274127374                | PhoneNumberLong        | Too many characters. Maximum allowed is 14 |
#    | 1                             |  EmailShort            | Minimum length required is 6 |
#    | 1fourtycharactersmaximumhdhdhdhdhdhdhdhdd7 |  EmailLong | Too many characters. Maximum allowed is 40 |

  @Macy's
  Scenario Outline: Macy's - When I input Invalid Characters I see an error message
    And I input "<Input>" in the "<BillingField>"
    Then I verify special character error displays with the Invalid "<Input>"
    When I call Cancel
    Then I can see the sales trans landing page

  Examples:
    |Input   | BillingField       |
    | 1      | FirstName          |
#    | 1      | LastName           |
#    | 1      | City               |
#    | #      | City               |
#    | #      | State              |
#    | *      | Zip                |
#    | #      | PhoneNumber        |
#    | A      | PhoneNumber        |




# @manual
#    Scenario: Verify if the virtual numerical keyboard is displayed
#      And I tap on Phone text box
#      Then a numerical virtual keyboard should be displayed
#      When I insert phone number
#      Then the phone number should be correctly displayed
#
#    @manual
#    Scenario: Verify if Email shortcut keyboard is displayed ( maybe we are using regex validations )
#      And I click on input box for email address
#      Then the Email shortcut keyboard should be displayed
#      And I can easily insert an email address
#
#    @manual
#    Scenario: Verify alpha-numeric keyboard should be displayed, starting with the numeric side, then change to alpha after a space is entered
#      And I click on address input box
#      Then a keyboard numeric should be displayed
#      When I insert the number of the street and a space character
#      Then an alpha keyboard should be displayed
#      When I insert the name of the street
#      Then the address field is correctly displayed
#
#
#    @manual
#    Scenario: Verify if first letter of alpha keyboard is always capital case
#      And I click on First name, Last name, Adress line 1, City
#      Then an alpha keyboard should be displayed
#      When I insert text in those input fields
#      Then first letter of alpha keyboard should always be capital case