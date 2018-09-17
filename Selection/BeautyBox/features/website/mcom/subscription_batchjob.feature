Feature: Beauty Box subscription and suspend by using batch job

  @subscription
  Scenario Outline: As a logged in user I want to join in join the waitlist and when the slot is available I want to subscribe after notifying

    Given I visit the web site as a guest user
    Then I visit database to insert "<dmls>" as a precondition
    And I launch beautybox about page url
    Then I see "<status>" on about page
    And I click on subscribe on about page
    And I create a new profile
    Then I should navigate to welcome waitlist page
    When I visit postgresql database to insert or delete "<records>" as a precondition
    #  Then I notify slot available for wait list customer
    Then I run a batchjob "<batch_job>" to verify
    And I check the status waitlist notified status on manage subscription
    And I launch beautybox about page url
    And I click on subscribe now on about page
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
    |dmls|status|records|cardtype|shippingaddress|batch_job|
    |qtyegrtthanmaxqty_wlperis-1.sql|JOIN THE WAITLIST|update_sub_prod_availability.sql|visa|address1|waitlistNotificationBatchJob|


  @subscription @wip
  Scenario Outline: As a logged in user I want to suspend the subscription for beautybox by adding invalid address from myaccount

    Given I visit the web site as a guest user
    And I create a new profile
    And I launch beautybox about page url
    # And I visit the web site as a registered user
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
    And I navigate to my address book page
    Then I run a batchjob "<batch_job>" to verify
    And I see suspended status on managesubscription
    And I click on change button to add new shipping address
    When I click on add new to add shipping address on checkout
    And I enter add new "<shippingAddress2>" Information
    Then I click on save to save the shipping address
    Then I run a batchjob "<batch_job2>" to verify
    And I see subscribed status on managesubscription
        # create a json to have all batch jobs so that I can reuse by passing it to one parameter.

    Examples:
    |status       |cardtype|shippingaddress|batch_job|shippingAddress2|batch_job2|
    |SUBSCRIBE NOW|visa    |address1   |profileValidationBatchJob|address2|cleanUpSuspensionBatchJob|


  @subscription
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|

