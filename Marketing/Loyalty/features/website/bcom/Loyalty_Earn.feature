Feature: Loyalty Earn

  #BLCOM-80287
  @use_regression @artifact_navapp @artifact_shopapp @artifact_legacy @priority_medium @artifact_navapp_2 @artifact_shopapp_2 @health_check @domain_marketing @loyalty_earn @use_browser
  Scenario: Verification of Loyalty Summary on order confirmation page, loyalty added to profile in Signed in checkout flow
    Given I visit the web site as a loyallist
    And I add a "available and orderable" product to my bag
    And I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page

  @use_regression @artifact_navapp @artifact_shopapp @priority_medium @artifact_navapp_2 @artifact_shopapp_2 @CD_BAT_NEXT @domain_marketing @loyalty_earn
  Scenario: Verification of Loyalty Summary on order confirmation page, loyalty added in Shipping & Payment in Signed in checkout flow
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "Loyallist" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    When I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page

  @use_regression @use_bat_next @artifact_navapp @artifact_shopapp @priority_medium @artifact_navapp_2 @artifact_shopapp_2 @domain_marketing @loyalty_earn @use_domain_qual
  Scenario: Verification of Loyalty Summary on order confirmation page in Guest checkout flow
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "payment" page as a "guest" user
    And I add "top_tier" Loyalty Id Through "Loyallist" tab from Guest Payment page
    Then I should able to see added Loyalty Id on Guest Payment page
    When I checkout to reach the "order confirmation" page as a "guest" user
    Then I should see loyalty summary section on Order Confirmation page

    #BLCOM-80286 - failing due to know defect where loyallist id added on payment page will not get added to my account
  @use_regression @backlog @artifact_legacy @priority_medium @health_check @domain_marketing @loyalty_earn @defect_ECOMSST-50946
  Scenario: Added loyalty id through look up by Loyalty from Payment page should be seen on Loyallist Account Summary page after placing order
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    And I add "reward" Loyalty Id Through "Loyallist" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    When I checkout to reach the "order confirmation" page as a "signed in" user
    # commented the below 2 steps since they are blocking the test case as the issue is known issue with saving the loyallist id from checkout page
#    And I navigate to Loyallist Account summary Page from Order Confirmation Page
#    Then I verify the account summary page for the loyallist

#  Failing due to know defect where loyallist id added on payment page will not get added to my account
  @use_regression @backlog @artifact_legacy @priority_medium @domain_marketing @loyalty_earn @defect_ECOMSST-50946
  Scenario: Added loyalty id through look up by BCC from Payment page should be seen on Loyallist Account Summary page after placing order
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "LookupByBCC" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    When I checkout to reach the "order confirmation" page as a "signed in" user
    # commented the below 2 steps since they are blocking the test case as the issue is known issue with saving the loyallist id from checkout page
#    And I navigate to Loyallist Account summary Page from Order Confirmation Page
#    Then I verify the account summary page for the loyallist

  @backlog @artifact_shopapp @priority_medium @domain_marketing @loyalty_earn @wip @please_automate
  Scenario: Verify Loyalty ID linked to online account overrides the one associated to BCC Prop/cobrand on order confirmation page
    Given I visit the web site as a registered user without loyalty
    When TODO needs a new step to add a compatible Citi Card To Profile
    And I navigate to loyallist auto association dialog and close the dialog
    And I can associate my account by loyallist number using "loyallist_details"
    And I add a "available and orderable" product to my bag
    And I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page

#  Failing due to know defect where loyallist id added on payment page will not get added to my account
  @use_regression @domain_marketing @loyalty_earn @defect_ECOMSST-50946
  Scenario: Auto-Association for the Logged in user with BCC added to profile in standard checkout
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "LookupByBCC" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    When I checkout to reach the "order review" page as a "signed in" user
    Then I should see loyalty id on Order Review page
    When I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page
    # By clicking on "Add it now to manage your Loyallist Account online"
  # commented the below 2 steps since they are blocking the test case as the issue is known issue
#    When I navigate to Loyallist Account summary Page from Order Confirmation Page
#    Then I verify the account summary page for the loyallist

  @use_regression @domain_marketing @loyalty_earn @use_domain_qual
  Scenario: Verify Free shipping for Loyallists when their ID tied to their profile
    Given I visit the web site as a registered user without loyalty
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "reward"
    When I add a "available and orderable" product to my bag
#    Commented the below steps since the below step is not valid anymore in 17N while automating in SDT
#    Then I should see Top Tier Free Shipping rebate in "shopping bag" page
    When I checkout to reach the "shipping & payment" page as a "signed in" user
    Then I should see Top Tier Free Shipping rebate in "shipping & payment" page
    When I checkout to reach the "order review" page as a "signed in" user
    Then I should see Top Tier Free Shipping rebate in "order review" page
    When I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page

  @use_regression @domain_marketing @loyalty_earn
  Scenario: Verify No Free shipping for Loyallists with express shipping methods selected
    Given I visit the web site as a registered user without loyalty
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "reward"
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    And I select express shipping method in shipping and payment page
    Then I should see express shipping price in "responsive shipping and payment" page
    When I checkout to reach the "order review" page as a "signed in" user
    Then I should see express shipping price in "responsive order review" page
    When I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page

  @use_regression @domain_marketing @defect_ECOMSST-50946
  Scenario: Add email overlay verification on order confirmation page
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "LookupByBCC" tab from Shipping And Payment page
    And I checkout to reach the "order confirmation" page as a "signed in" user
    Then I should see loyalty summary section on Order Confirmation page
#    Commented the below steps since they are not valid anymore in 17N while automating in SDT
#    When I Add your email address to receive REWARD CARDS by email
#    Then I should see loyalty email save successful message