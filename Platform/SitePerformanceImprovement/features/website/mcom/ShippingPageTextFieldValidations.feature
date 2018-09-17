Feature:As a SA i want to do the validation on text fields in shipping page

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the error message in text fields of the shipping info section in shipping & delivery page for Guest user
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter "empty_data" into the text fields of shipping info
Then I verify that "error message" for corresponding fields in shipping info section

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the validation symbol in text fields of the shipping info section in shipping & delivery page for Guest user
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter "data_with_value" into the text fields of shipping info
Then I verify that "fields accepting inputs" for corresponding fields in shipping info section

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the specific error messsage in name text fields when user enter a special character or not recomended character
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter special character in "firstname" text field
Then I verify the corresponding error message for "firstname" field
When I enter special character in "lastname" text field
Then I verify the corresponding error message for "lastname" field

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the specific error messsage in address text fields when user enter a special character or not recomended character
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
When I enter special character in "addressfield1" text field
Then I verify the corresponding error message for "addressfield1" field
When I enter special character in "addressfield2" text field
Then I verify the corresponding error message for "addressfield2" field
When I enter special character in "city" text field
Then I verify the corresponding error message for "city" field

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the specific error messsage in zipcode and phone-number text fields when user enter a inadequate digits
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter special character in "zipcode" text field
Then I verify the corresponding error message for "zipcode" field
And I enter special character in "phonenumber" text field
Then I verify the corresponding error message for "phonenumber" field

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the validation in apply gift cards and rewards text field when user enters a 16 digit number
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I "enter" the card details in gift card and reward section text fields
Then I verify the "validation" of the text fields apply gift card and rewards section


@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the error message in apply gift cards and rewards text field when user enters does not enter credit card number details
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I "not enter" the card details in gift card and reward section text fields
Then I verify the "error" of the text fields apply gift card and rewards section

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the credit card option choosen by default as a payment option in payment section
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter details on the shipping section and continue from shipping section
Then I verify payment option section is expanded
And I verify that credit card option is selected and paypal option is not selected by default
And I verify the default fields are enabled

@domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario: Verify the paypal choosen as a payment option in payment section
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter details on the shipping section and continue from shipping section
And I select paypal option in payment section
Then I verify the alert message displayed with note:
And I verify that "Continue to Paypal" button is available

@testfix @domain_Site_performance_Optimization @guest_checkout @Rc_Text_Fields
Scenario Outline: Verify the credit card type is choosen by given card type for credit card payment option in payment section
Given I visit the website as a guest user using rest services
When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
And I checkout until I reach the shipping page as a "guest" user
And I enter details on the shipping section and continue from shipping section
Then I verify payment option section is expanded
And I verify that credit card option is selected and paypal option is not selected by default
And I verify the default fields are enabled
When I select "<card_type>" from card type field
Then I verify corresponding information against "<card_type>"
Examples:
  |card_type|
  |Macy's   |
  |Employee Card|
  |Visa         |
  |MasterCard   |
  |American Express|
  |Macy's American Express|





