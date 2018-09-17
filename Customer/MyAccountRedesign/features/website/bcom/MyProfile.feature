# Author: Vamshi Koormachalam
# Date Created: February 24th, 2013
# Date Signed Off: TBD

Feature: To verify create profile scenarios

################################### Create profile - Error message Validation ##########################################

  # Mingle Story: http://mingle/projects/market/cards/108
  #Test Case ID: BLCOM-80180
  @upi_108_bcom @artifact_shopapp @priority_medium @use_dsv @14H @s4a_stable @domain_marketing @use_regression @run_this
  Scenario:Verify error messages when Create Profile mandatory fields are left blank
    Given I visit the web site as a guest user
    Then I verify error messages while creating profile with missing fields
      | field_name        | error_message                                                                                       |
      | first_name        | Please enter first name.                                                                            |
      | last_name         | Please enter last name.                                                                             |
#      | address_line_1    | Please enter an address.                                                                            |
#      | address_city      | Please enter a city.                                                                                |
#      | address_state     | Please select a state.                                                                              |
#      | address_zip_code  | Please enter a zip code.                                                                            |
#      | security_question | Please select a security question from the drop down box before proceeding.                         |
#      | security_answer   | The answer to the selected security question must be between 2 and 20 characters. Please try again. |

  @upi_96_mcom  @upi_108_bcom @artifact_shopapp @priority_medium @14H @s4a_stable @domain_marketing
  Scenario:Verify error messages for Create Profile mandatory fields are invalid data
    Given I visit the web site as a guest user
    Then I verify error message while creating profile with invalid data
      | field_name       | value | error_message                         |
      | first_name       | !@#$  | Please remove any special characters. |
      | last_name        | !@#$  | Please remove any special characters. |
      | address_line_1   | !@#$  | Please remove any special characters. |
      | address_line_2   | !@#$  | Please remove any special characters. |
      | address_city     | !@#$  | Please remove any special characters. |
      | address_zip_code | !@#$  | Please enter a 5-digit zip code.      |

################################### Update Profile ##############################################################

  #BLCOM-73322
  @upi_108_bcom @artifact_shopapp @priority_low @s4a_stable @domain_marketing
  Scenario:Verify update of phone subscription on my profile page
    Given I visit my account page as a signed user
    When I navigate to my profile
    And I add mobile number in Text Message Preference section
    And I navigate to my profile
    Then I verify the mobile number in Text Message Preferences section
    When I uncheck the subscribe check box
    And I navigate to my profile
    Then I should not see mobile number is auto filled

  #BLCOM-56750
  @sstbacklog @use_regression @myaccount_4 @artifact_shopapp @domain_marketing
  Scenario: As a Registered user should be able to log in to his account and should be able to edit his profile.
    Given I visit the web site as a guest user
    And I navigate to signin page
    Then I verify the navigated page is secured
    When I visit my account page as a signed user
    Then I verify the navigated page is secured
    When I navigate to the My Profile Page
    Then I verify the navigated page is secured
    When I update "first_name and last_name" on my profile page
    Then I verify the profile is updated

  #BLCOM-66914
  @artifact_shopapp @priority_medium @use_regression @myaccount_1 @domain_marketing @use_e2e
  Scenario: Verify that user should be able to create profile , update profile.
    Given I visit the web site as a guest user
    And I create a new profile
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    When I navigate to the My Profile Page
    And I update "first_name and last_name" on my profile page
    Then I verify the profile is updated
    #  Notes:
    #  Expected Result:
    #  1. Verify that bloomingdales home page should be displayed.
    #  2. a. Verify that user should be able to create a profile.
    #  b. Verify that secure user token, last access token should be generated.
    #  c. Verify that signed in cookie value should be 1.
    #  d. Verify that macys online uid should be user id.
    #  e. Verify that user should receive a welcome email.
    #  3. Verify that user should be able to update the user profile details.


  ################################### Create Profile ##############################################################

  @upi_108_bcom @artifact_shopapp @priority_low @s4a_stable @domain_marketing
  Scenario:Verify user create profile with Text Message Preference section selected
    Given I visit the web site as a guest user
    When I create a profile by enabling the text me radio with valid phone number
    And I navigate to my profile
    Then I should see text me option is selected on my profile page


  @use_regression @artifact_shopapp @artifact_shopapp_2 @myaccount_1 @prod_myaccount @use_dsv @mustpass @s4a_stable @domain_marketing
  Scenario:My Profile - Rendering Create Profile Page for a guest user
    Given I visit the web site as a guest user
    When I navigate to create profile page
    Then I verify the basic attributes of Create Profile Page
    # Notes:
    # verify all 4 sections - contact information, email preferences, text message preferences,
    # add your bloomingdales card to profile
    # verify default check box selections in email preferences and add your bloomingdales card to profile sections

  #BLCOM-66916
  @sstbacklog @use_regression @myaccount_1 @domain_marketing @priority_high
  Scenario: To verify secure user token & last access token generated during create a profile/signin.
    Given I visit the web site as a guest user
    And I create a new profile
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    When Navigate to The Registry Page
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    When I navigate to My Account page
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    When I navigate to the "my profile" link in My Account page
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    And I click on the browser back button
    When I navigate to the "My Address Book" link in My Account page
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    And I click on the browser back button
    When I navigate to the "My Order Status & History" link in My Account page
    Then I verify secure user token and cookie values after sign in
    And I verify bloomingdales_online_uid should be replaced with user id
    # Notes:
    # Input
    # 1. Sign in from shop app sign in page or registry or create a profile from shopapp/registry/quick registration.
    # 2. Navigate across apps and verify the profile details.
    # Expected Result:
    # 1. In cookie list verfiy that,
    #  a) 'secure_user_token' cookie is generated.
    #  b) 'SignedIn' cookie value should be 1.
    #  c) 'bloomingdales_online_uid' should be user id.
    # 2. Verify that user profile details should be maintained consistently across apps. (Legacy , Shop app).

  #BLCOM-70507
  @sstbacklog @use_regression @myaccount_3 @artifact_shopapp @domain_marketing
  Scenario: Verify create profile page displays security Q&A options displayed.
    Given I visit the web site as a guest user
    When I navigate to Create Profile page
    Then I verify security questions and security answer fields on the create profile page
    When I create profile along with security Q&A established to the profile
    And I update the details in my profile
    Then I should see the profile is successfully updated
    #Notes:
    # Input:
    # 1. Navigate to sign in page and Click on create button.
    # 2. Enter valid information including security question & Answer and click on "CREATE PROFILE" button.
    # Expected Result:
    # 1. User will be navigated to the create profile page.
    # 1.a) Verify the security question drop-down menu with 10 questions on the profile creation form.
    #  1. What was the first concert you attended?
    #  2. What was the name of your favorite cartoon series as a child?
    #  3. What is the name of a college you applied to but didn't attend?
    #  4. What is the name of the place your wedding reception was held?
    #  5. What was your oldest sibling's nickname as a child?
    #  6. What street did you live on in third grade?
    #  7. What was the name of your first pet?
    #  8. In what city or town did your mother and father meet?
    #  9. What is your maternal grandmother's nickname?
    # 10. What was the name of your first supervisor?
    # 1.b) A text field is presented to input a corresponding answer for the security question
    # 2. Upon profile creation Q&A should be stored with the users profile.


  @akamai_waf @upi_97_mcom @upi_110_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer @use_browser @dsv_sev2_dryrun
  Scenario:Verify Update Profile functionality on My Profile Page
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I navigate to my profile
    And I update profile details on my profile
    Then I verify my profile is updated