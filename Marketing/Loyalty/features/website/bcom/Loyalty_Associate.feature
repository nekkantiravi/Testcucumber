Feature: Loyalty Association
  #For tests associating a loyalty account to profile
  #Requires data in yml file to be accurate for environment
  #TODO GET DATA IN 15F ENVs

  #Account Page Lookups:
  @use_bat_next @artifact_shopapp @priority_high @artifact_shopapp_2 @CD_BAT_NEXT  @use_regression @domain_marketing @loyalty_associate
  Scenario: Verify Association From Loyalty Summary Page via Loyallist Number
    Given I visit the web site as a registered user without loyalty
    When I navigate to the loyalty associate page
    Then I can associate my account by loyallist number using "base_tier"
    Then I verify the account summary page for the loyallist

  @artifact_shopapp @priority_high @artifact_shopapp_2 @CD_BAT_NEXT  @use_regression @domain_marketing @loyalty_associate
  Scenario: Verify Association From Loyalty Summary Page via Credit Card
    Given I visit the web site as a registered user without loyalty
    When I navigate to the loyalty associate page
    Then I can associate my account by credit card number using "top_tier"
    Then I verify the account summary page for the loyallist

#  @use_regression @domain_marketing @loyalty_associate @wip @please_automate
#  Scenario: Verify Association From Loyalty Summary Page With Loyalty CC Tied to Profile
#    #Given I visit the web site as a registered user
#    #And TODO needs a new step to add a compatible Citi Card To Profile
#    Given I sign in with user having "new_prop_user" added in profile
#    When I navigate to the loyalty account summary page
#    #And I close the loyalty auto association dialog
#    Then I can associate my account by credit card number using "top_tier"
#    # Note: Auto association works only with new profiles

  @uft @priority_high @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Verify auto association on the account summary page
    Given I visit the web site as a registered user
    And TODO needs a new step to add a compatible Citi Card To Profile
    Then I can auto associate my loyalty account using "toptier_less_than_2500_points" from the account summary page

  @uft @priority_high @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Verify auto association and email prompt the on account summary page
    Given I visit the web site as a registered user
    And TODO needs a new step to add a compatible Citi Card To Profile
    Then I can auto associate my loyalty account using "more_than_2500_points" from the account summary page

  @uft @priority_high @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Verify auto association on the auto assocation page
    Given I visit the web site as a registered user
    And TODO needs a new step to add a compatible Citi Card To Profile
    Then I can auto associate my loyalty account using "toptier_less_than_2500_points" from the enrollment page

  @uft @priority_high @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Verify auto association and email prompt the auto assocation page
    Given I visit the web site as a registered user
    And TODO needs a new step to add a compatible Citi Card To Profile
    Then I can auto associate my loyalty account using "more_than_2500_points" from the enrollment page

  @priority_medium @manual @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Verify association when loyalty id is inactive due to due to opt-out or reported lost/stolen
    Given I visit the web site as a registered user
    When TODO needs a new step to add a compatible Citi Card To Profile
    And I navigate to loyallist landing page from footer link
    Then I should not see loyalty auto association page
  #Note: We are adding credit card which is associated to a loyalty id that is inactive due to due to either opt-out or reported lost/stolen

  @uft @priority_medium @artifact_shopapp @artifact_shopapp_2 @domain_marketing @defect_MCOMRE-83473 @loyalty_associate @wip @please_automate
  Scenario: Verify association when user has removed loyallist id associated to bloomingdales card previously
    Given I visit the web site as a registered user
    When TODO needs a new step to add a compatible Citi Card To Profile
    And I can auto associate my loyalty account using "reward" from the enrollment page
    And I can remove my loyallist number
    And I sign out from profile
    And I sign in using existing profile
    Then I click the loyallist footer link as a registered user

  @deprecated_15GA  @uft @priority_medium @artifact_shopapp @artifact_shopapp_2 @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Select cancel button on auto association page
    Given I visit the web site as a registered user
    When TODO needs a new step to add a compatible Citi Card To Profile
    And I navigate to the auto association page
    And I select cancel button on association page
    Then I verify that the "Loyallist Enrollment Page" page is rendered
    And I click the loyallist footer link as a registered user

  @deprecated_15GA  @uft @priority_medium @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Select cancel button on auto association page after five unsuccessful attempts
    Given I visit the web site as a registered user
    When TODO needs a new step to add a compatible Citi Card To Profile
    And I navigate to the auto association page
    And I continue with invalid entries for five times on loyallist auto association page
    And I select cancel button on association page
    Then I verify that the "Loyallist Enrollment Page" page is rendered
    And I navigate to the auto association page

  #
  #Checkout Page Lookups:
  #

  @artifact_shopapp @artifact_legacy @priority_high @artifact_shopapp_2 @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Adding Loyalty Through Add Your Loyallist tab on shipping and payment page in Signed in checkout flow
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "Loyallist" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page

  @artifact_shopapp @artifact_legacy @priority_high @artifact_shopapp_2 @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Adding Loyalty Through Lookup by Bcc tab on shipping and payment page in Signed in checkout flow
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "LookupByBCC" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page

  @artifact_navapp @artifact_shopapp @priority_medium @artifact_navapp_2 @artifact_shopapp_2 @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Adding Loyalty Through Add Your Loyallist tab on payment page in Guest checkout flow
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I add "top_tier" Loyalty Id Through "Loyallist" tab from Guest Payment page
    Then I should able to see added Loyalty Id on Guest Payment page

  @artifact_navapp @artifact_shopapp @priority_medium @artifact_navapp_2 @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Adding Loyalty Through Lookup by Bcc tab on payment page in Guest checkout flow
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I add "reward" Loyalty Id Through "LookupByBCC" tab from Guest Payment page
    Then I should able to see added Loyalty Id on Guest Payment page

  @backlog @artifact_legacy @priority_low @use_regression @domain_marketing @loyalty_associate
  Scenario: Display LTYID and edit link on Billing page-Guest user has entered an ID in checkout
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I add "top_tier" Loyalty Id Through "Loyallist" tab from Guest Payment page
    Then I should able to see added Loyalty Id on Guest Payment page
    And Lookup LoyaltyID overlay should be displayed when edited

  @backlog @artifact_shopapp @priority_low @mustpass @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Enrolled loyalty id display on Order review page
    Given I visit the web site as a registered user without loyalty
    When I enroll into the Loyalty program using existing profile
    And I navigate to the loyalty account summary page
    Then I verify the account summary page for the loyallist
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see loyalty id on Order Review page


  @backlog @artifact_shopapp @priority_low @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of loyalty added in profile display on Order review page
    Given I visit the web site as a registered user without loyalty
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "top_tier"
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see loyalty id on Order Review page


  @backlog @artifact_shopapp @priority_low @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Added loyalty through look up by Loyalty on payment page should display on Order review page
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add "reward" Loyalty Id Through "Loyallist" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    Then I continue checkout until I reach the order review page as an "signed in" user
    Then I should see loyalty id on Order Review page


  @backlog @artifact_shopapp @priority_low @use_regression @domain_marketing @loyalty_associate
  Scenario: Verification of Added loyalty through look up by BCC on payment page should display on Order review page
    Given I visit the web site as a registered user without loyalty
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "LookupByBCC" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    Then I continue checkout until I reach the order review page as an "signed in" user
    Then I should see loyalty id on Order Review page


  @backlog @artifact_shopapp @priority_low @use_regression @domain_marketing @loyalty_associate @wip @please_automate
  Scenario: Verification of Added loyalty through look up by BCC on payment page should display on Order review page using CC dropdown @wip @please_automate
    Given I visit the web site as a registered user without loyalty
   # And TODO needs a new step to add a compatible Citi Card To Profile
#    Given I sign in with user having "new_prop_user" added in profile
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I add "top_tier" Loyalty Id Through "LookupByBCC" tab from Shipping And Payment page
    Then I should able to see added Loyalty Id on Shipping And Payment page
    Then I continue checkout until I reach the order review page as an "signed in" user
    Then I should see loyalty id on Order Review page
