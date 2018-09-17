  # Enrollments
    # IDs are 1 time use. A successful enrollment at Plenti changes the enrollment state
  @use_manual
  @plenti_e2e
Feature: Plenti E2E

  Scenario: Plenti - Guest Enrollment
    Given I visit the web site as a Plenti user

  Scenario: Plenti - Finalize Anonymous Enrollment from Shopping Bag Page
    Given I visit the web site as a guest user
   # When I add an "available and orderable" radical product to my bag
    When I add an "available" product to my bag
    When I lookup the USL ID manually specified in this step:
      | usl_id           | activation_code | phone_number |
      | 3104170655075919 | 5687            ||
    Then I finalize enrollment for the Plenti ID

  Scenario: Plenti - Finalize Anonymous Enrollment from My Account Page
    Given I visit the web site as a registered user
    When I navigate to my account page
    When I lookup the USL ID manually specified in this step:
      | usl_id           | activation_code | phone_number |
      | 3104170655075877 | 7257            ||
    Then I finalize enrollment for the Plenti ID

  Scenario: Plenti - Finalize PreEnrolled Enrollment from My Account Page
    Given I visit the web site as a registered user
    When I navigate to my account page
    When I lookup the USL ID manually specified in this step:
      | usl_id           | activation_code | phone_number |
      | 3104170655075893 | 5917            | 440-698-4842 |
    Then I finalize enrollment for the PreEnrolled Plenti ID
