Feature: To verify the Header banner section on registry home page.
  @dsv_desktop_sev2 @domain_marketing @use_regression @Marketing_CBT
Scenario: As a guest user, I should see the Header banner section on registry home page
Given I visit the web site as a guest user
When I navigate to registry home page
Then I should see header banner with all the elements on Home Page