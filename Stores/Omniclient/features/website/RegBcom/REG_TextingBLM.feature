# Author: Robert Vargas
# Story: Texting
# Date Created: 01/25/2018
# Date Signed Off:

Feature: As a BLM’s associate, I want to text my customers from different screens.

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the MY TASKS tab
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When I navigate to MY TASKS page
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the MY TASKS tab
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When I navigate to MY TASKS page
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I navigate to MY TASKS page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the MY CUSTOMERS tab
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When navigate to MY CUSTOMERS page
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the MY CUSTOMERS tab
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When navigate to MY CUSTOMERS page
    And I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then navigate to MY CUSTOMERS page
#
  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from Customer Profile Page
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When I click on the the clients name "TEST TEST ACCOUNT" from the MY CUSTOMERS List
    And I click on the BLM preferred method of contact
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox
#
  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from Customer Profile Page
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When I click on the the clients name "TEST TEST ACCOUNT" from the MY CUSTOMERS List
    And I click on the BLM preferred method of contact
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I should see the Customer Profile website page

      @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the BLM Dashboard
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click the text message send button
    Then I should see "Hello" in the text message textbox

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the BLM Dashboard
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    And I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    Then I will see the associates HOMEPAGE
    When I click on the text message icon
    And I type "Hello" in the texting Message textbox
    And I click on the X icon on the texting screen
    Then I will see the associates HOMEPAGE
