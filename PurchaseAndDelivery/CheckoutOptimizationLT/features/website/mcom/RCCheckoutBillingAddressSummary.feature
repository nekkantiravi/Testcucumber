Feature: Checkout Optimization LT Checkout Guest & Signedin Billing address display scenarios

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @mcom_rc_guest @coo-ll
  Scenario Outline: Verify if the Billing address is displayed in the summary of the credit card section
    Given I visit the website as a <user_type> user using rest services
    When I add an "<product_type>" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the <page> page as a "user_type" user
    Then I should see payment summary section with billing address text

  Examples:
    |product_type |page                |user_type  |
    |available    |order review        |guest      |
    |available    |shipping & payment  |registered |


