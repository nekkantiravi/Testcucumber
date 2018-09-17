# Author: Vamshi Koormachalam
# Date Created: February 24th, 2013
# Date Signed Off: TBD

Feature: To verify create profile scenarios

 #Mingle Story: http://mingle/projects/market/cards/96

  @artifact_shopapp @domain_customer @release_16K @project_UFT @mode_domestic @use_regression @migrated_to_sdt
  Scenario: Verify flyouts from FOBs are expanded in my profile page
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I navigate to my profile
    When I mouse hover on any category
    Then I should see flyout menu

  ############################ Error Message Validation when create profile #######################################

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify error messaging when mandatory fields are blank on Create Profile
    Given I visit the web site as a guest user
    Then I verify error messages while creating profile with missing fields
      | first_name | Please enter your first name. |
      | last_name  | Please enter your last name.  |
      | dob_month  | Please enter date of birth.   |
      | dob_day    | Please enter date of birth.   |
      | dob_year   | Please enter date of birth.   |

  @upi_96_mcom  @upi_108_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify error messaging when mandatory fields are invalid on Create Profile
    Given I visit the web site as a guest user
    Then I verify error message while creating profile with invalid data
      | field_name | value | error_message                                                                           |
      | first_name | !@#$  | Sorry, first name may only contain letters and hyphens and cannot exceed 20 characters. |
      | last_name  | !@#$  | Sorry, last name may only contain letters and hyphens and cannot exceed 30 characters.  |

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Receive Emails & Text Me values in DB on Create Profile with default data
    Given I visit the web site as a guest user
    When I create a new profile
    Then I verify my preference receive emails "Thisit Email Newsletter" value should be displayed as "Y" in DB
    And I verify my preference text me "Mobile Phone Marketing Preference" value should be displayed as "Y" in DB
    When I navigate to My Preferences page from My Account page
    Then I verify send me macys emails preference as "Enabled" and text me preference as "Enabled" in Notification Preferences section

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_customer  @migrated_to_sdt
  Scenario:Verify Receive Emails & Text Me values in DB on Create Profile when Receive Emails unselected & Text Me options selected
    Given I visit the web site as a guest user
    When I create a profile by deselecting receive me emails and selecting text me options
    Then I verify my preference receive emails "Thisit Email Newsletter" value should be displayed as "N" in DB
    And I verify my preference text me "Mobile Phone Marketing Preference" value should be displayed as "Y" in DB
    When I navigate to My Preferences page from My Account page
    Then I verify send me macys emails preference as "Disabled" and text me preference as "Enabled" in Notification Preferences section

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression  @myaccount_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Receive Emails & Text Me values in DB on Create Profile when Receive Emails & Text Me options are deselected
    Given I visit the web site as a guest user
    When I create a profile by de-selecting receive me emails and text me options
    Then I verify my preference receive emails "Thisit Email Newsletter" value should be displayed as "N" in DB
    And I verify my preference text me "Mobile Phone Marketing Preference" value should be displayed as "N" in DB
    When I navigate to My Preferences page from My Account page
    Then I verify send me macys emails preference as "Disabled" and text me preference as "Disabled" in Notification Preferences section

#MCOM-69987
  @sstbacklog @use_regression @myaccount_6 @domain_customer @saucelab_M @saucelab_M_F1 @migrated_to_sdt
  Scenario: Verify Database when user Create Profile While Creating Registry
    Given I visit the web site as a registry user
    And I verify create profile details in DB

  #MCOM-83782
  @sstbacklog @use_regression @myaccount_4 @domain_customer  @dsv_desktop_sev2 @migrated_to_sdt
  Scenario: Verify updated legal terms in Create profile page
    Given I visit the web site as a guest user
    When I navigate to create profile page
    Then I should see the below message in the create_profile page:
    """
    By selecting "create account" you agree that you are subject to Macy's Notice of Privacy Practices and Legal Notice.
    """
  #Notes:
  #Legal Terms Message:
  #"*Offer is sent via email once account is created. By clicking "create profile," you are acknowledging you are subject to Macy's Notice of Privacy Practices and Legal Notice. If you have selected "Yes! send me Macy's emails," you are now agreeing to receive email from Macy's

   ###################################To verify update profile scenarios ##################################################

###############Mingle Story: http://mingle/projects/market/cards/97

  @upi_97_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify the Profile is not saved when No is selected on Save Updates Overlay in My Profile page
    Given I visit the web site as a guest user
    When I create a new profile
    And I update the first_name and last_name in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "No" in Save Updates Overlay on my profile page
    Then I should be redirected to "my_address_book" page
    And I should see the profile is not updated

  @upi_97_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify the Profile is saved when Yes is selected on Save Updates Overlay in My Profile page
    Given I visit the web site as a guest user
    When I create a new profile
    And I update the first_name and last_name in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "Yes" in Save Updates Overlay on my profile page
    And I verify my profile is updated

  @upi_97_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Save Updates Overlay can be closed on My Profile page
    Given I visit the web site as a guest user
    When I create a new profile
    And I update the first_name and last_name in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "Close" in Save Updates Overlay on my profile page
    Then I should be redirected to "my_address_book" page
    And I should see the profile is not updated

  @use_regression @artifact_shopapp @artifact_shopapp_2 @myaccount_6 @prod_myaccount @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:My Profile - User edits profile but does not submit - Save updates overlay
    Given I visit the web site as a registered user
    When I navigate to my profile page
    And I edit "first_name" field in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "No" in Save Updates Overlay on my profile page
    Then I should be redirected to "my_address_book" page

  # @14D
  @priority_medium @prod @artifact_shopapp @uft @use_regression @artifact_shopapp_2 @myaccount_6  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify new copy of updated text, manage credit account link, mailing address labels on my profile page
    Given I visit the web site as a registered user
    When I navigate to my profile page
    Then I should see text "Keep your mailing address below up to date to receive exclusive Macy's offers & promotions by mail." on my profile page
    And I should see label Mailing Address line 1 on my profile page
    And I should see label Mailing Address line 2 on my profile page

  ###################################### Update Billing Address Popup ###########################################
# @14D
  @priority_medium @prod @artifact_shopapp @uft @use_regression @artifact_shopapp_2 @myaccount_6  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify update billing address pop up window is not displayed when no prop/cobrand card added to profile
    Given I visit the web site as a guest user
    When I create a new profile
    And I update "address_city" on my profile
    Then I should not see update billing address pop up window
    And I verify my profile

# @14D
  @priority_medium @prod @artifact_shopapp @uft @use_regression @artifact_shopapp_2 @myaccount_6  @s4a_stable @domain_customer @use_browser @migrated_to_sdt
  Scenario:Verify update billing address pop up window is not displayed when user updates info other than state, zipcode, city, mailing address and no prop/amex attached to profile
    Given I visit the web site as a guest user
    When I create a new profile
    And I update "first_name" on my profile
    Then I should not see update billing address pop up window
    And I verify my profile

