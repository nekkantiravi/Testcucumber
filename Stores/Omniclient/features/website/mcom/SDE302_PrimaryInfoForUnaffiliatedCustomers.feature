# Author: Claudiu Chirila
# Story: SDE-302 - Only Display Primary Information for Unaffiliated Customers
# Date Created:
# Date Signed Off:

Feature: As a system administrator, I only want associates to see Primary customer information when viewing the profile
  of a customer they are not affiliated with so that I can protect the privacy of sensitive data

  @domain_stores @omniclient @story_SDE-302 @website @mcom
  Scenario: Associates can view only the Primary Info section for Unaffiliated customers
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    # we will search for an unaffiliated client/customer
    And I enter first name "PRIMARY" in the input field
    And I enter last name "CLIENT" in the input field
    And I enter zip code "44053" in the input field
    And I click on the omniclient search button
    When I click on the searched client "PRIMARY CLIENT"
    And I navigate to Manage Client tab
    Then the following information should be displayed in Primary Information section:
      | Name                 |
      | Primary address      |
      | Primary phone number |
    And Preferred Information section is not displayed
    And Additional Information section is not displayed