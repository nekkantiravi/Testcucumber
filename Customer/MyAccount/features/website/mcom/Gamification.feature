 # Feature file to test top reviewer badge feature

 Feature: Gamification for profile

      #  Top Reviewer Badge in top header
   @gamify @14K @use_regression @domain_customer  @run_this @migrated_to_sdt
   Scenario: As a customer, I should see the top reviewer badge in the header
     Given I visit the web site as a registered user
     When I navigate to my account page
     Then I see the top reviewer badge
     When I mouse hover on the gamification Top Reviewer badge
     Then I verify the message for TopReviewer badge