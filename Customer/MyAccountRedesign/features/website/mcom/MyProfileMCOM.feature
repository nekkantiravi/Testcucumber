# Author: Vamshi Koormachalam
# Date Created: February 24th, 2013
# Date Signed Off: TBD

Feature: To verify create profile scenarios

 #Mingle Story: http://mingle/projects/market/cards/96

  @priority_high @domain_customer @dsv_sev2  @dsv_desktop_sev2 @use_regression @akamai_waf
  Scenario:Verify Update Profile functionality on My Profile Page
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I navigate to my profile
    And I update profile details on my profile
    Then I verify my profile is updated

  #MCOM-83782
  @upi_96_mcom @artifact_shopapp @priority_medium @myaccount_4 @s4a_stable @domain_customer
  Scenario:Verify Legal Terms on Create Profile Page
    Given I visit the web site as a guest user
    When I navigate to create profile page
    Then I should see message "By selecting \"create account\" you agree that you are subject to Macy's Notice of Privacy Practices and Legal Notice." in the create_profile page

 #MCOM-69989
  @please_automate_backlog @domain_customer
  Scenario: Verifying profile creation functionality when creating a registry
    Given I visit the web site as a registry user
    When I leave the site and return later
    Then I should be able to sign in and view my profile
  #Notes:
  #Data specified by the user while creating the registry should match the data saved in the My Profile section in the My account page

  #MCOM-91989
  @use_dsv @myaccount_7
  Scenario: Create profile legal notice message
    Given I visit the web site as a guest user
    When I navigate to create profile page
    Then I verify the display of legal notice message
  # Notes:
  # legal notice message: By clicking "create profile," you are acknowledging you are subject to Macy's Notice of Privacy Practices and Legal Notice. If you have selected "Yes! send me Macy's emails," you are now agreeing to receive email from Macy's.
  # Description: Create profile page
  # Steps:
  #  1. Navigate to macys.com
  #  2. Navigate to create profile page
  #
  # Expected Results:
  #  1. Macys.com home page should display
  #  2. Bottom of the page should display message "By clicking "create profile," you are acknowledging you are subject to Macy's Notice of Privacy Practices and Legal Notice.If you have selected "Yes! send me Macy's emails," You are now agreeing to receive email from Macy's."

  #MCOM-57235
  @please_automate_backlog @domain_customer
  Scenario: Verify the functionality of updating the Profile Page.
    Given I visit the web site as a guest user
    When I create a new profile
    And I update "first_name and last_name" on my profile
    Then I verify my profile
    And I verify the breadcrumb of My Profile page
    # Notes
    # Input
    # 1. Navigate to profile page and update
    # 2. Observe the breadcrumb
    # Expected Results
    # 1. Data is updated and displayed correctly.
    # 2. Verify the breadcrumb is correct.Correct depthpath is  My Account --> My Profile

  ######################################### Manual Scenario ##########################################################

  #MCOM-77026
  @manual @domain_customer
  Scenario: Verify SUPC in Welcome email for create profile
    Given I visit the web site as a guest user
    When I add a random product to my bag
    And I checkout until I reach the "PAYMENT_PAGE" page as a "guest" user
    And I create a new profile during checkout
    And I place an order
    Then I should navigate to "Order_Confirmation" page
    And I should receive profile creation email
    And I verify basic attributes of the profile creation email
    #Notes:
    #basic attributes of the profile creation email:
    #in the received welcome  Email, following SUPC should display instead of "null" value:
    #"Thanks for Registering! $15 off your $100 purchase. To redeem your promotion at Checkout , Just copy and paste code:XXXXXXXXXX"
