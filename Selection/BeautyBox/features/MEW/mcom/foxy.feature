Feature: Beauty Box foxy Feature File

  @comment @wip
  Scenario: As a guest user I want to see what in the box
    Given I visit the web site as a guest user
    Then I see beauty box on home page
    And I see dropdown to select the monthly beauty box
    When I select a specified month from dropdown
    Then I should see sample products for that month

  @comment @wip
  Scenario: As a guest user I want to see what in the box with out header and footer
    Given I visit the web site as a guest user
    Then I should not see header and footer on home
    And I see dropdown to select the monthly beauty box
    When I select a specified month from dropdown
    Then I should see sample products for that month


  @1 @tag @about2 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see subscribe now on about page
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page


    Examples:
     |records|dmls|status|
     |BeautyBox_Subscription.sql|BB_SubscribeNow.sql|SUBSCRIBE NOW|

  @2 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see user to subscribe now for monthly box
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
     |records|dmls|status|
     |BeautyBox_Subscription.sql|BB_Subscribe_Suspend.sql|SUBSCRIBE NOW|

  @3 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see user as subscribe now button to subscribe for monthly box when the WL quantity is lessthan maxquantity and wl % is -1
    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
      |dmls|status|
      |lessthanmaxqty_wlperis-1.sql|SUBSCRIBE NOW|

  @5 @4 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want user to see Join Waitlist button to subscribe for monthly box when the WL quantity >= maxquantity and wl % is -1
    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
      |dmls|status|
      |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|
      |qtyequaltomaxqty_wlperis-1.sql|JOIN THE WAITLIST|

  @6 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see user to subscribe for monthly box when the WL quantity is lessthan maxquantity and wl % is 0
    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
      |dmls|status|
      |lessthanmaxqty_wlperis0.sql|SUBSCRIBE NOW|

  @8 @7 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see user to subscribe for monthly box when the WL quantity >= maxquantity and wl % is 0
    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
      |dmls|status|
      |qtygrtthanmaxqty_wlperis0.sql|currently full|
      |qtyequalmaxqty_wlperis0.sql|currently full|


  @9 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see user to subscribe for monthly box as wait list status
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
    |records|dmls|status|
    |BeautyBox_Subscription.sql|BB_WaitList.sql|JOIN THE WAITLIST|


  @10 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want to see user to be in waitlist for subscription
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
      |records|dmls|status|
      |BeautyBox_Subscription.sql|BB_WaitList.sql|JOIN THE WAITLIST|

  @11 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user I want user to see subscription closed status for monthly box
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
    |records|dmls|status|
    |BeautyBox_Subscription.sql|BB_CurrentlyFull.sql|currently full|

  @12 @tag @about1 @cta @beauty @must9
  Scenario Outline: As a logged in user - user to see subscription closed status for monthly box
    Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page

    Examples:
    |records|dmls|status|
    |BeautyBox_Subscription.sql|BB_CF_WL_Cancel_SP.sql|currently full|


  @13 @tag @about1 @ctav @beauty @precon
  Scenario Outline: Reset back the original data after executing automation
  #  Given I visit the web site as a guest user
    When I visit postgresql database to insert or delete "<records>" as a precondition

    Examples:
      |records|
      |postexecution_insertdata.sql|


  @13 @WIP @beauty @mustv
  Scenario: As a logged in user I want to see about page to validate beautybox video
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I should see video on the about subscribe page and I play the video
    And I click on video to pause the video


  @14 @tag1 @abouthowitworksa @beauty
  Scenario: As a logged in user I want to see how it works on beauty box monthly subscription page
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I see how it works on monthly subscription page
    And I see join and its description
    And I see get your box and its description
    And I see discover and its description

  @15 @tag1 @about @WIP
  Scenario: As a logged in user I want to see all months banners romance text and  monthly boxes
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I see ourboxes section and its dropdown background image and monthly text


  @16 @tag @abouttopbanner
  Scenario: As a logged in user I want to see top and bottom banners
     Given I visit the web site as a guest user
     And I launch beautybox about page url
     Then I see top banner on about page
    And I see bottom banner on about page

  @18 @17 @WIP
   Scenario Outline: As a logged in user I want to add a product to bag
     Given I visit the web site as a guest user
     And I launch beautybox about page url
     When I click on "<shopTheProduct>" on about page for default month
     Then I navigate to ptp full size products to validate images and description

      Examples:
        |shopTheProduct|
        |Shop the products.|
        |shoptheproductimage|


  @20 @19 @PTPFullSizeProducts @PTPTest
  Scenario Outline: As a logged in user I want to see PTP full size products when user navigates from about page
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I click on "<shopTheProduct>" on about page for default month "<ptpPrice>"
    Then I navigate to ptp full size products to validate products and description

    Examples:
      |shopTheProduct|ptpPrice|
    #  |Shop the products.|productsDesc|
    #  |shoptheproductimage|productsDesc|
      |SHOP FULL SIZE PRODUCTS|productsDesc|

  @21 @comparePTPProductsOriginalPriceandServiceOrgPrice
  Scenario Outline: As a logged in user I want to compare PTP product original price and service response original prices
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    When I click on "<shopTheProduct>" on about page for default month "<ptpPrice>"
    Then I navigate to ptp full size products to validate products original price

    Examples:
      |shopTheProduct|ptpPrice|
      |Shop the products.|originalPrice|
      |shoptheproductimage|originalPrice|

    @22  @compPTPProductsSalepriceandServicesSalePrice
    Scenario Outline: As a logged in user I want to compare PTP product sale price and service response sale prices
      Given I visit the web site as a guest user
      And I launch beautybox about page url
      When I click on "<shopTheProduct>" on about page for default month "<ptpPrice>"
      Then I navigate to ptp full size products to validate products retail price

      Examples:
        |shopTheProduct|ptpPrice|
        |Shop the products.|retailPrice|
        |shoptheproductimage|retailPrice|

    @23 @PTPFullSizeProducts @ptp
    Scenario Outline: As a logged in user I want to see PTP full size products
      Given I visit the web site as a guest user
     # And I launch beautybox about page url
      When I select month and year from "<pageName>" page dropdown to validate "<ptpPrice>"

      Examples:
        |pageName|ptpPrice|
        |monthlyBox|productsDesc|
        |monthlyBox|originalPrice|
        |monthlyBox|retailPrice|

    @24 @createsubscription @beauty @must
    Scenario Outline: As a logged in user I want to subscribe for beautybox using different credit cards
      Given I visit the web site as a guest user
      And I create a new profile
      And I launch beautybox about page url
      Then I see "<status>" on about page
      And I click on subscribe on about page
      When I click on add new to add shipping address on checkout
      Then I enter address "<shippingaddress>" Information
      Then I click on save to save the shipping address
      When I click on add new to add creditcard on checkout
      Then I Enter "<cardtype>" Information
      And I enter address "<shippingaddress>" Information
      Then I click on save to save the credit card address
      And I accept terms and conditions on subscription checkout
      And I click on continue button on create subscription
      Then I click on subscribe button to create subscription
      And I see user successfully subscribed for beautybox

      Examples:
        |status       |cardtype|shippingaddress|
        |SUBSCRIBE NOW|visa    |address1   |
        |SUBSCRIBE NOW|master  |address1   |
        |SUBSCRIBE NOW|amex    |address1   |
        |SUBSCRIBE NOW|discover|address1   |



  @24 @createsubscription @beauty @must
  Scenario Outline: As a logged in user I want to create subscription without redirecting to myaccount
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I create a new profile
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox

    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |
      |SUBSCRIBE NOW|master  |address1       |
      |SUBSCRIBE NOW|amex    |address1       |
      |SUBSCRIBE NOW|discover|address1       |


  @25 @createsubscription @beauty @must
  Scenario Outline: As a logged in user I want to cancel the subscription for beautybox
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    When I click on cancel subscription to cancel the beautybox subscription
    And I click on yes for cancel subscription confirmation
    Then I capture subscription status after canceling the subscription
    Then I visit database to update configuration as a precondition
    Then I Sign out of the website


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1   |


  @24 @createsubscription @beauty @must
  Scenario Outline: As a logged in user I want to suspend the subscription for beautybox
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox
    Then I update the database to see the suspended status
    And I see suspended status on managesubscription
 #   And I see no cancel button for suspended user
    And I click on change button to add new shipping address
    And I click on change button to add new payment card


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1   |


    @26 @Cancelsubscription @beauty @must
    Scenario Outline: As a logged in user I want to add multiple shipping and payment card information and subscribe by making second shipping and credit card as default for subscription
        Given I visit the web site as a guest user
        And I create a new profile
        And I launch beautybox about page url
        Then I see "<status>" on about page
        And I click on subscribe on about page
        When I click on add new to add shipping address on checkout
        Then I enter address "<shippingAddress>" Information
        Then I click on save to save the shipping address
        When I click on add new to add creditcard on checkout
        Then I Enter "<cardtype>" Information
        Then I enter address "<shippingAddress>" Information
        Then I click on save to save the credit card address
        And I click on change button to add new shipping address
        When I click on add new to add shipping address on checkout
        And I enter add new "<shippingAddress2>" Information
        Then I click on save to save the shipping address
        And I click on change button to add new shipping address
        Then I select address2 as my default address for subscription
        And I click on save to save the updated shipping address on cs
        And I click on change button to add new payment card
        Then I validate the updated shipping address information
        When I click on add new to add creditcard on checkout
        And I enter new payment "<cardtype2>" and billing information
        And I enter add new "<shippingAddress2>" Information
        Then I click on save to save the credit card address
        And I click on change button to add new payment card
        Then I select payment card2 as my default payment for subscription
        Then I click on save to save the credit card address on cs
        Then I validate the updated card information
        And I accept terms and conditions on subscription checkout
        And I click on continue button on create subscription
        Then I click on subscribe button to create subscription


    Examples:
      |status       |cardtype|shippingAddress|shippingAddress2|cardtype2|
      |SUBSCRIBE NOW|visa    |address1       |address2        |master   |

    @27 @createsubscription @beauty @must
    Scenario Outline: As a subscribed user I want to add second shipping address and credit card information on manage subscription
      Given I visit the web site as a guest user
      And I create a new profile
      And I launch beautybox about page url
      Then I see "<status>" on about page
      And I click on subscribe on about page
   #   Then I check the checkbox status on create subscription
      When I click on add new to add shipping address on checkout
      Then I enter address "<shippingaddress>" Information
      Then I click on save to save the shipping address
      Then I check the checkbox status on create subscription
      When I click on add new to add creditcard on checkout
      Then I Enter "<cardtype>" Information
      Then I enter address "<shippingaddress>" Information
      Then I click on save to save the credit card address
      And I accept terms and conditions on subscription checkout
      And I click on continue button on create subscription
      Then I click on subscribe button to create subscription
      And I click on change button to add new shipping address
      When I click on add new to add shipping address on checkout
      And I enter add new "<shippingAddress2>" Information
      Then I click on save to save the shipping address
      And I click on change button to add new shipping address
      Then I select address2 as my default address for subscription
      And I click on save to save the updated shipping address
      And I click on change button to add new payment card
      Then I validate the updated shipping address information
      When I click on add new to add creditcard on checkout
      And I enter new payment "<cardtype2>" and billing information
      And I enter add new "<shippingAddress2>" Information
      Then I click on save to save the credit card address
      And I click on change button to add new payment card
      Then I select payment card2 as my default payment for subscription
      And I click on save to save the updated payment card
      Then I validate the updated card information


      Examples:
        |status       |cardtype|shippingaddress|shippingAddress2|cardtype2|
        |SUBSCRIBE NOW|visa    |address1       |address2        |master   |

    @28 @createsubscription @beauty @must
    Scenario Outline: As a registered user I want to add invalid shipping address to check the shipping address validation
      Given I visit the web site as a guest user
      And I create a new profile
      And I launch beautybox about page url
      Then I see "<status>" on about page
      And I click on subscribe on about page
      When I click on add new to add shipping address on checkout
      Then I enter address "<shippingaddress>" Information
      Then I click on save to save the shipping address
      And I see error message to the user

      Examples:
        |status       |shippingaddress  |
        |SUBSCRIBE NOW|invalidAddress   |
        |SUBSCRIBE NOW|invalidAddress_2 |
        |SUBSCRIBE NOW|invalidAddress_3 |
        |SUBSCRIBE NOW|invalidAddress_4 |
        |SUBSCRIBE NOW|invalidAddress_5 |
        |SUBSCRIBE NOW|invalidAddress_6 |
        |SUBSCRIBE NOW|invalidAddress_7 |
        |SUBSCRIBE NOW|invalidAddress_8 |
        |SUBSCRIBE NOW|invalidAddress_9 |
        |SUBSCRIBE NOW|invalidAddress_10 |
        |SUBSCRIBE NOW|invalidAddress_11 |
        |SUBSCRIBE NOW|invalidAddress_12 |
        |SUBSCRIBE NOW|invalidAddress_13 |

  @24 @createsubscription @beauty989 @must
  Scenario Outline: As a new user I want to create subscription without redirecting to myaccount and validate the payment card months
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I create a new profile
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox
    Then I navigate to manage subscription
    Then I click on edit payment card on beauty ms

    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |


  @29 @createsubscription @beauty @must
  Scenario Outline: As a logged in user I want to cancel the subscription and resubscribe for beautybox
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    When I click on cancel subscription to cancel the beautybox subscription
    And I click on yes for cancel subscription confirmation
  #  Then I capture subscription status after canceling the subscription
    Then I Sign out of the website
    When I sign in to my existing profile
    When I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    And I see user successfully subscribed for beautybox
    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1   |

  @30 @createsubscription @beauty @must2
  Scenario Outline: As a logged in user I want to see subscription tile in the MyAccount page
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    Then I see user successfully subscribed for beautybox
    And I capture subscriptionstatus on Managesubscription page
    When I click on MyAccount link on ManageSubscription page
    Then I see subscription tile in MyAccount page
    And I should see subscriptionstatus in Subscription tile
    When I click on beautybox logo in MyAccount page
    Then I see "<status>" on about page
    When I browser back
    And I see beauty box about page link on subscription tile
    When I click on beauty box about page link
    Then I see "<status>" on about page
    When I browser back
    And I see shop full size product link on subscription tile
    When I click on shop full size product link
    Then I see shop full size product page with full size products
    When I browser back
    And I see Beauty Box FAQ link
    When I click on Beauty Box FAQ link
    Then I see beauty box FAQ page
    When I browser back
    Then I see manage subscription link in MyAccount page
    When I click on manage subscription link
    Then I see manage subscription page with subscriptionstatus


    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |


  @31 @createsubscription @beauty @must3
  Scenario Outline: As a logged in user I want to see subscription tile in the MyAccount page for cancelled subscription
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    When I click on add new to add shipping address on checkout
    Then I enter address "<shippingaddress>" Information
    Then I click on save to save the shipping address
    When I click on add new to add creditcard on checkout
    Then I Enter "<cardtype>" Information
    And I enter address "<shippingaddress>" Information
    Then I click on save to save the credit card address
    And I accept terms and conditions on subscription checkout
    And I click on continue button on create subscription
    Then I click on subscribe button to create subscription
    Then I see user successfully subscribed for beautybox
    When I click on cancel subscription to cancel the beautybox subscription
    And I click on yes for cancel subscription confirmation
    Then I capture subscription status after canceling the subscription
    And I capture subscriptionstatus on Managesubscription page
    When I click on MyAccount link on ManageSubscription page
    Then I see subscription tile in MyAccount page
    And I should see subscriptionstatus in Subscription tile
    When I click on beautybox logo in MyAccount page
    Then I see "<status>" on about page
    When I browser back
    And I see beauty box about page link on subscription tile
    When I click on beauty box about page link
    Then I see "<status>" on about page
    When I browser back
    And I see shop full size product link on subscription tile
    When I click on shop full size product link
    Then I see shop full size product page with full size products
    When I browser back
    And I see Beauty Box FAQ link
    When I click on Beauty Box FAQ link
    Then I see beauty box FAQ page
    When I browser back
    Then I see manage subscription link in MyAccount page
    When I click on manage subscription link
    Then I see manage subscription page with subscriptionstatus

    Examples:
      |status       |cardtype|shippingaddress|
      |SUBSCRIBE NOW|visa    |address1       |

  @32 @createsubscription @beauty @must4
  Scenario: As a user I should not see subscription tile in the MyAccount page for newly created user
    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    When I navigate to my account page
    Then I should not see subscription tile in MyAccount page

  @scenario33 @must
  Scenario Outline: As a guest user I want to naviagte to beautybox page and select global navigation menu
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                      |
      | Beauty                    |
      | Macy's monthly beauty box |
    When I click on Macys logo and navigate to home page
    When I navigate the global navigation menu as follows:
      | Shop                      |
      | Beauty                    |
      | Macy's monthly beauty box |
    Then I see "<status>" on about page
    And I click on subscribe on about page

    Examples:
      |status       |
      |SUBSCRIBE NOW|


  @scenario34 @must
  Scenario: As a guest user I want to naviagte to beautybox page and navigating to PDP
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                      |
      | Beauty                    |
      | Macy's monthly beauty box |
    And I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                      |
      |Men|
      |Accessories   |
      |Watches     |
    And I select a random member product using mobile website
    And I should be redirected to PDP page using mobile website
