# Author: Robert Vargas
# Story: Texting
# Date Created: 01/25/2018
# Date Signed Off:

Feature: As a Macy’s associate, I want to text my clients from different screens.

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the ALL TO DOS tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I navigate to ALL TO DOS page
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the ALL TO DOS tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I navigate to ALL TO DOS page
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I should see the MY TO DOS page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the MY CLIENTS tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I click on My Clients from top navigation bar
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the MY CLIENTS tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I click on My Clients from top navigation bar
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I should see the Macys My Clients page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the HOMEPAGE tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the HOMEPAGE
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I should be on the Macy's homepage

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the Client profile tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I click on the the clients name "KATE BROWNS" from the MY CLIENTS List
    Then I should see the Client Profile website page
    When I click on the preferred method of contact
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the Client profile tab
    Given I launch the macy's omniclient page
    When I enter "10000053" in username field of Omniclient login page
    And I enter "Temp$Pass3" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I should be on the Macy's homepage
    When I click on the the clients name "KATE BROWNS" from the MY CLIENTS List
    Then I should see the Client Profile website page
    When I click on the preferred method of contact
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I should see the Client Profile website page

