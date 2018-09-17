Feature: Initiate chat with Macy's bot and check order status
# Pre-requisites if any

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup order when user types "order status"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Order Status" in the chat
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Closed" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify that user is able to lookup order from the menu on lower left screen of chatbot
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I click on "Menu Button" button
    And I click on "OS Menu" button
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Closed" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup order when user types "help"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "help" in the chat
    And I click on "Order Status" button
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Closed" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup order when user types "menu"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "menu" in the chat
    And I click on "Order Status" button
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Closed" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup order when user types "check order"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "check order 1316459043 for passionladydi@gmail.com" in the chat
    And I click on "OS Response Yes" button
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Closed" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup In-Process order
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Order Status" in the chat
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS InProcess" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup Confirmed order
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Order Status" in the chat
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Confirmed" text


  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to lookup Canceled order
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Order Status" in the chat
    And I click on "OS Response Yes" button
    And I type "1316459043" in the chat
    And I type "passionladydi@gmail.com" in the chat
    Then I see "OS Canceled" text

