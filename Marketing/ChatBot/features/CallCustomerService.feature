Feature: Call Customer Service through Messenger
# Pre-requisites if any

  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can start the chat with Macy's bot
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page


  @ThirdParty @chat-bot @manual
  Scenario: Verify that Call Them button is available
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "Customer Service" button
    Then I see "Call them" button
    Then I see "Send Message" button


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can call 1800buymacy when user types "help"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    When I type "help" in the chat
    And I click on "Customer Service" button
    Then I see "Call them" button
    Then I see "Send Message" button


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can call 1800buymacy from initial chatbot tutorial
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I click on "Customer Service" button
    And I click on "Call them" button
    And I see "No Thanks" button
    And I click on "Call Now" button
    Then I should see that call is initiated from device


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can call 1800buymacy from the menu on lower left screen of chatbot
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I click on "Menu Button" button
    And I click on "CusSer Menu" button
    And I click on "Call them" button
    And I see "No Thanks" button
    And I click on "Call Now" button
    Then I should see that call is initiated from device


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can call 1800buymacy when user types "menu"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "menu" in the chat
    And I click on "Customer Service" button
    And I click on "Call them" button
    And I see "No Thanks" button
    And I click on "Call Now" button
    Then I should see that call is initiated from device


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can call 1800buymacy when user types "help"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "help" in the chat
    And I click on "Customer Service" button
    And I click on "Call them" button
    And I see "No Thanks" button
    And I click on "Call Now" button
    Then I should see that call is initiated from device


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user can call 1800buymacy when user types "Customer Service"/"customer service"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "customer service" in the chat
    And I click on "Call them" button
    And I see "No Thanks" button
    And I click on "Call Now" button
    Then I should see that call is initiated from device


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user comes back to macy's bot when call is terminated
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I click on "Customer Service" button
    And I click on "Call them" button
    And I see "No Thanks" button
    And I click on "Call Now" button
    And I terminate the call
    Then I see messenger signed-in page

