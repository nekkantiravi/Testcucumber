Feature: As a customer, I want to be able to update my profile on the site to keep it current


 @p1 @1621 @v1-b-49251 @v1-b-62116 @customer_migration @domain_mew_foundation @domain_customer_management @use_regression @migrated_to_sdt
 Scenario: Verify User should see the following input fields with labels
  Given I visit the mobile web site as a registered user
  Then I navigate to the MyAccount Profile page
  Then I should see the following fields on the Profile page:
    | First name              | text   | |
    | Last name               | text   | |
    | Mailing address line 1  | text   | |
    | Mailing address line 2  | text   | |
    | City                    | text   | |
    | State                   | select | |
    | Zip Code                | number   | |
    | Gender                  | select | |
    | Month                   | select | Birth date |
    | Date                    | select | Birth date |
    | Year                    | select | Birth date |
    | Email                   | email   | |
    | Verify Email            | email   | |
    | Password                | password | |
    | Verify password         | password | |
    | Security Question       | select | |
    | Answer                  | password | |

   @p1 @1621 @v1-b-49251 @v1-b-62116 @customer_migration @domain_mew_foundation @domain_customer_management @use_regression @migrated_to_sdt
   Scenario: Verify User should be able to view a link to change preferences
    Given I visit the mobile web site as a registered user
    Then I navigate to the MyAccount Profile page
    Then I should see an Update preferences link

  @p1 @1621 @v1-b-49251 @v1-b-62116 @customer_migration @domain_mew_foundation @domain_customer_management @use_regression @migrated_to_sdt
  Scenario: Verify User should be able to see the disclaimer text under "update profile" button with links to Privacy Practices and Legal Notice:
    Given I visit the mobile web site as a registered user
    Then I navigate to the MyAccount Profile page
    Then I should see the disclaimer text under the update profile button
      |FAQs                      |
      |About Security            |
      |About Privacy             |
      |About Credit Card Privacy |
    And I should see the legal notice links in the disclaimer
      | FAQs                      | url   | https://customerservice.macys.com/app/answers/list/c/5  |
      | About Security            | url   | https://customerservice.macys.com/app/answers/detail/a_id/41/theme  |
      | About Privacy             | url   | https://customerservice.macys.com/app/answers/detail/a_id/40/theme  |
      | About Credit Card Privacy | path  | /service/credit/popups/privacy.jsp |

  @p1 @1621 @v1-b-49251 @v1-b-62116 @customer_migration @domain_mew_foundation @domain_customer_management @use_regression @migrated_to_sdt
  Scenario: Verify User should be able to enter the happy path data in the fields and submit the form with entered data by clicking on "update profile" button
    Given I visit the mobile web site as a registered user
    Then I navigate to the MyAccount Profile page
    When I enter the following profile data
      | First name              | text    | Doctor   |
      | Last name               | text    | Strange   |
      | Mailing address line 1  | text    | The Dark Dimension   |
      | Mailing address line 2  | text    | Infinity   |
      | City                    | text    | Nowhere   |
      | State                   | select  | New York  |
      | Zip Code                | number  | 99999   |
      | Gender                  | select  | Male |
      | Month                   | select  | November |
      | Date                    | select  | 11 |
      | Year                    | select  | 1930 |
      | Password                | password| IAmTheWalrus |
      | Verify password         | password| IAmTheWalrus |
      | Email                   | email   | drstrange@blackhole.macys.com   |
      | Verify Email            | email   | drstrange@blackhole.macys.com   |
      | Security Question       | select  | 1|
      | Answer                  | password| Testing |
    And I click the update preferences button
    And I refresh the screen
    Then I should see the data is updated
    And I should be able to login with the new email address and password


  @p1 @1621 @v1-b-49251 @v1-b-62116 @customer_migration @domain_mew_foundation @domain_customer_management @v1-d-46368 @use_regression  @migrated_to_sdt
  Scenario: Verify defect D-46368 first of month date error is resolved
    Given I visit the mobile web site as a registered user
    Then I navigate to the MyAccount Profile page
    When I enter the following profile data
      | Mailing address line 1  | text    | The Dark Dimension   |
      | City                    | text    | Nowhere   |
      | State                   | select  | New York  |
      | Zip Code                | number  | 99999     |
      | Month                   | select  | November  |
      | Date                    | select  | 1         |
      | Year                    | select  | 1930      |
      | Security Question       | select  | 1         |
      | Answer                  | password| Testing   |
    And I click the update preferences button
    And I refresh the screen
    Then I should see the following data is updated
      | Month |
      | Date  |
      | Year  |

  @p1 @1625 @v1-b-68540 @customer_migration @domain_mew_foundation @domain_customer_management @v1-d-46368 @use_regression @migrated_to_sdt
  Scenario: Verify user should not see "Update Billing Information button" when macys credit card is not associated to his profile
    Given I visit the mobile web site as a registered user
    Then I navigate to the MyAccount Profile page
    Then I verify Update Profile Billing Information button is not visible

      # Credit card account needs to be set up for specific environment
  @manual @p1 @1625 @v1-b-68540 @domain_mew_foundation @domain_customer_management @v1-d-46368 @migrated_to_sdt
  Scenario: Verify user can see the Update profile button when macys credit card is associated to user's profile
    Given I visit the mobile web home page
    And I am on the footer secure-m sign in page
    And I sign in using profile which has macys credit card associated
    When I visit the mobile web home page
    When I go to My Account on the nav bar
    Then I click "Profile" header on My Account Page
    Then I should see Update Billing Information button on the page




