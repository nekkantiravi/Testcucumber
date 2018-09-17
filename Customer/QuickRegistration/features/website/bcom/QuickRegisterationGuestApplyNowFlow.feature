#####################################################################################
# Author : Quick Register of Card Online::QE Team
#####################################################################################

Feature: Verify local parameters in apply now flow for guest user with quick registeration changes

  @use_manual @domain_customer @artifact_shopapp @project_credit_quick_registeration @release_17ZA
  Scenario : Verify the local storage parameters when user selects addcardwallet & quickregistration in approval flow for Guest user
    Given Guest user navigates to Gateway page
    When user clicks on Apply Now button
    And fill apply now form with valid data
    Then user should display with approval page along with addcardwallet and quickregistration checkboxes
    When user clicks on Return to Macys button after selection of both checkboxes
    Then it should land on Gateway page with local storage parameters as below
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"aquisition","addToWallet":"Y","quickRegister":"Y","referenceNumber":"123456"}|
    And it should pass same parameters to responsive gateway controller from local storage
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"aquisition","addToWallet":"Y","quickRegister":"Y","referenceNumber":"123456"}|

  @use_manual @domain_customer @artifact_shopapp @project_credit_quick_registeration @release_17ZA
  Scenario : Verify the local storage parameters when user selects only addcardwallet in approval flow for Guest user
    Given Guest user navigates to Gateway page
    When user clicks on Apply Now button
    And fill apply now form with valid data
    Then user should display with approval page along with addcardwallet and quickregistration checkboxes
    When user clicks on Return to Macys button after selection of addcardwallet checkbox
    Then it should land on Gateway page with local storage parameters as below
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"aquisition","addToWallet":"Y","quickRegister":"","referenceNumber":"123456"}|
    And it should pass same parameters to responsive gateway controller from local storage
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"aquisition","addToWallet":"Y","quickRegister":"","referenceNumber":"123456"}|

  @use_manual @domain_customer @artifact_shopapp @project_credit_quick_registeration @release_17ZA
  Scenario : Verify the local storage parameters when user selects only quickregistration in approval flow for Guest user
    Given Guest user navigates to Gateway page
    When user clicks on Apply Now button
    And fill apply now form with valid data
    Then user should display with approval page along with addcardwallet and quickregistration checkboxes
    When user clicks on Return to Macys button after selection of quickregistration checkbox
    Then it should land on Gateway page with local storage parameters as below
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"aquisition","addToWallet":"","quickRegister":"Y","referenceNumber":"123456"}|
    And it should pass same parameters to responsive gateway controller from local storage
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"aquisition","addToWallet":"","quickRegister":"Y","referenceNumber":"123456"}|

  @use_manual @domain_customer @artifact_shopapp @project_credit_quick_registeration @release_17ZA
  Scenario : Verify the local storage parameters when user not selects addcardwallet & quickregistration in approval flow for Guest user
    Given Guest user navigates to Gateway page
    When user clicks on Apply Now button
    And fill apply now form with valid data
    Then user should display with approval page along with addcardwallet and quickregistration checkboxes
    When user clicks on Return to Macys button without any selection of checkboxes
    Then it should land on Gateway page with local storage parameters as below
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"activation","addToWallet":"","quickRegister":"","referenceNumber":"123456"}|
    And it should pass same parameters to responsive gateway controller from local storage
      |Key| Value|
      |quickRegisterQueryParams|{"processIndicator":"activation","addToWallet":"","quickRegister":"","referenceNumber":"123456"}|