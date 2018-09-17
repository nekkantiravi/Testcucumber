Feature: As a user, I have to update and maintain Profile Address Functionality

  @v1-b-02605 @domain_marketing @use_mew @15F @1508
  Scenario: verify "Use my profile address" is switched to off when a customer lands on Add credit card modal
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see "Use my profile address" is switched off

  @mew_regression @v1-b-02605 @domain_marketing @use_mew @15F @1508
  Scenario: verify tapping on "Use my profile address" populates the billing address fields on Add credit card modal
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see "Use my profile address" is switched off
    When I turn "Use my profile address" ON
    Then I should see my "billing address" fields populated from the profile address

  @v1-b-02605 @domain_marketing @use_mew @15F @1508 @manual
  Scenario: verify switching on of "Use my profile address" populates the default shipping address fields from customer's account
    # My Account not implemented in MEW 2.0 yet.. not able to edit the shipping address from MEW 2.0
    And the following are tested manually:
    """
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see Use my profile address is switched off
    When I turn Use my profile address ON
    Then I should see the default shipping address fields populated from the my account default shipping address
    """

  @v1-b-02605 @domain_marketing @use_mew @15F @1508 @manual
  Scenario: verify switching on of "Use my profile address" populates the default shipping address fields from profile address of customer's account if the customer does not have a default shipping address
    # My Account not implemented in MEW 2.0 yet
    And the following are tested manually:
    """
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see Use my profile address is switched off
    When I turn Use my profile address ON
    And I do not have a default shipping address in my account
    Then I should see the default shipping address fields populated from the profile address in the Account
    """

  @mew_regression @v1-b-02605 @domain_marketing @use_mew @15F @1508 @manual
  Scenario: verify the information contained in the billing address fields (if any) are overwritten by profile address when the switch is turned ON
    Given I am on wallet landing page
    And I add shipping addresss to the profile
    And I navigate to wallet page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see Use my profile address is switched off
    When I enter some information in the fields
    When I turn Use my profile address ON
    Then I should see the shipping address of the registered user in the billing address section
    #Then I should see the information is overwritten by profile address
    """

 @v1-b-02605 @domain_marketing @use_mew @15F @1508
  Scenario: verify the fields in billing address are set as blank if the corresponding fields are blank in profile address
    Given I visit the mobile web home page
    When I sign in as an existing user without phone number
    # sign in as an existing user with some of the fields in profile address as empty
    Then I navigate to my wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see "Use my profile address" is switched off
    When I turn "Use my profile address" ON
    Then I should see the corresponding billing address phone number field as blank

  @v1-b-02605 @domain_marketing @use_mew @15F @1508
  Scenario: verify the edit changes to the billing address are not saved when a customer edits a field after turning ON "Use my profile address" switch
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see "Use my profile address" is switched off
    When I turn "Use my profile address" ON
    And I enter a billing address details in the add credit card modal
    Then I should see Use profile address switch must be turned OFF automatically

  @mew_regression @v1-b-02605 @domain_marketing @use_mew @15F @1508
  Scenario: verify the customer is able to edit the fields of the billing address when he flips the switch to "off"
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the credit card cell
    Then I should be navigated to the "edit credit card" modal
    And I should see "Use my profile address" is switched off
    When I turn "Use my profile address" ON
    When I update the card details with 'Discover' in the edit credit card modal
    Then I should see Use profile address switch must be turned OFF automatically
    And I update billing address details in the edit credit card modal
    And I tap the 'apply' button in the edit credit card modal
    Then I should see wallet landing page
    When I tap on the credit card cell
    Then I should be navigated to the "edit credit card" modal
    Then I should see the credit card data displayed that I previously updated

  @v1-b-02605 @domain_marketing @use_mew @15F @1508 @manual
  Scenario: verify the placeholder text is displayed if the address fields are blank
    And the following are tested manually:
    """
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And I should see Use my profile address is switched off
    When I turn Use my profile address ON
    And I see blank fields in the billing address
    Then I should see the following place holder text for the corresponding fields
      | Credit card section: Card Type, Card Number, Expiration |
      | First name |
      | Last name  |
      | Address Line 1|
      | Address Line 2(optional)|
      | City |
      | State |
      | Zip Code|
      | Phone Number |
      | Email |

  """
