Feature: Shipping and billing address validation through Pitney Bowes Spectrum

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, verify the error message displayed on shipping page when Apt/Floor suite is missing in shipping address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with missing apt address
    Then I should see following error message:
      | We are unable to validate your shipping address. Please enter your apartment or suite number to continue with checkout. |

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, verify the error message displayed on payment page when Apt/Floor suite is missing in billing address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with missing apt address
    Then I should see following error message:
      | We are unable to validate your shipping address. Please enter your apartment or suite number to continue with checkout. |

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, Verify the auto address correction in shipping section with invalid city
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with invalid "city"
    Then I should see the corrected city for shipping address

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, Verify the auto address correction in shipping section with invalid state
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with invalid "state"
    Then I should see the corrected state for shipping address

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, Verify the auto address correction in shipping section with invalid zip code
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with invalid "zip code"
    Then I should see the corrected zip code for shipping address
    And I should see following warning message:
      | We noticed your shipping address Zip code was incorrect so we ﬁxed it. Please verify your address below. |

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, Verify the auto address correction in shipping section with zip code have zero prefix
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with auto correction address having zero prefix in zip code
    Then I should see the corrected zip code for shipping address
    And I should see following warning message:
      | We noticed your shipping address Zip code was incorrect so we ﬁxed it. Please verify your address below. |

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the auto address correction in payment section with invalid city
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with invalid "city"
    Then I should see the corrected city for billing address

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the auto address correction in payment section with invalid state
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with invalid "state"
    Then I should see the corrected state for billing address

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the auto address correction in payment section with invalid zip code
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with invalid "zip code"
    Then I should see the corrected zip code for billing address

  @project_async_checkout @domain_purchase_and_delivery @bcom_oor
  Scenario: As a guest user, Verify the auto address correction in payment section with zip code have zero prefix
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with auto correction address having zero prefix in zip code
    Then I should see the corrected zip code for billing address

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the shipping address validation for DPV code "U" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with DPV code "U" address
    Then I should see DPV code "U" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the shipping address validation for DPV code "N" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with DPV code "N" address
    Then I should see DPV code "N" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the shipping address validation for DPV code "M" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with DPV code "M" address
    Then I should see DPV code "M" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the shipping address validation for DPV code "D" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with DPV code "D" address
    Then I should see DPV code "D" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the billing address validation for DPV code "S" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with DPV code "S" address
    Then I should see DPV code "S" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the payment address validation for DPV code "Y" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with DPV code "Y" address
    Then I should see DPV code "Y" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the payment address validation for DPV code "U" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with DPV code "U" address
    Then I should see DPV code "U" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the payment address validation for DPV code "N" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with DPV code "N" address
    Then I should see DPV code "N" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the payment address validation for DPV code "M" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with DPV code "M" address
    Then I should see DPV code "M" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the payment address validation for DPV code "D" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I submit the payment section with DPV code "D" address
    Then I should see DPV code "D" from address response

  @project_async_checkout @domain_purchase_and_delivery @run
  Scenario: As a guest user, Verify the shipping address validation for DPV code "S" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with DPV code "S" address
    Then I should see DPV code "S" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a guest user, Verify the shipping address validation for DPV code "Y" Address
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I submit the shipping section with DPV code "Y" address
    Then I should see DPV code "Y" from address response

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a signed in user, Verify the auto address correction in shipping section with invalid city
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I add address with Invalid "city" on my address book page
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "registered" user
    Then I should see the corrected city for shipping address

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a signed in user, Verify the auto address correction in shipping section with invalid state
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I add address with Invalid "state" on my address book page
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "registered" user
    Then I should see the corrected state for shipping address

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a signed in user, Verify the auto address correction in shipping section with invalid zipcode
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I add address with Invalid "zip code" on my address book page
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "registered" user
    Then I should see the corrected zip code for shipping address

  @project_async_checkout @domain_purchase_and_delivery
  Scenario: As a signed in user, Verify the auto address correction in shipping section with zip code have zero prefix
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I add address with address having zero prefix in zip code on my address book
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "registered" user
    Then I should see the corrected zip code for shipping address

  @domain_purchase_and_delivery @bcom_oor
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select continue button on guest shipping page
    And I add a credit card during checkout
    And I select continue button on guest payment page

  @domain_purchase_and_delivery @bcom_oor
  Scenario: Place order by selecting Use my shipping address checkbox on Payment page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I enter payment details on payment page
    And I select use my shipping address checkbox on payment page
    And I enter contact details on payment page
    And I select continue button on guest payment page

  @domain_purchase_and_delivery @bcom_oor
  Scenario: Verifying the Merge bag scenario
    Given I visit the web site as a registered user
    When I add an "orderable" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I visit the web site as a guest user
    And I add an "available and orderable" product to my bag
    And I sign in during checkout
    Then I verify the functionality of merge bag