Feature: Initiate chat with Macy's bot and read FAQs under Customer Service feature
# Pre-requisites if any

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Macy's.com FAQs from Customer Service menu
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I see "Shipping FAQ" button
    And I see "Returns & Exchanges FAQ" button
    And I see "Orders FAQ" button

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Macy's.com FAQs upon typing "help"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "help" in the chat
    And I click on "Customer Service" button
    And I click on "FAQ" button
    And I see "Shipping FAQ" button
    And I see "Returns & Exchanges FAQ" button
    And I see "Orders FAQ" button

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Macy's.com FAQs upon typing "menu"
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "menu" in the chat
    And I click on "Customer Service" button
    And I click on "FAQ" button
    And I see "Shipping FAQ" button
    And I see "Returns & Exchanges FAQ" button
    And I see "Orders FAQ" button

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Macy's.com FAQs from menu on lower left screen of chatbot
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I click on "Menu Button" button
    And I click on "CusSer Menu" button
    And I click on "FAQ" button
    And I see "Shipping FAQ" button
    And I see "Returns & Exchanges FAQ" button
    And I see "Orders FAQ" button

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Free Shipping FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Shipping FAQ" button
    And I click on "Free Shipping" button
    Then I see "Free Shipping FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Shipping Times FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Shipping FAQ" button
    And I click on "Shipping Times" button
    Then I see "Shipping Times FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Shipping Refund FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Shipping FAQ" button
    And I click on "Shipping Refund" button
    Then I see "Shipping Refund FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Return Policy FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Returns & Exchanges" button
    And I click on "Return Policy" button
    Then I see "Return Policy FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Return Online FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Returns & Exchanges" button
    And I click on "Return Online" button
    Then I see "Return Online FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Return In Store FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Returns & Exchanges" button
    And I click on "Return In Store" button
    Then I see "Return In Store FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Exchanges FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Returns & Exchanges" button
    And I click on "Exchanges" button
    Then I see "Exchanges FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Cancel Order FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Orders" button
    And I click on "Cancel Order" button
    Then I see "Cancel Order FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Didn't Receive Order FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Orders" button
    And I click on "Didn’t Receive Order" button
    Then I see "Didn’t Receive Order FAQs" text

  @ThirdParty @chat-bot @manual
  Scenario: Verify user is able to see Pick Up In Store FAQs
    Given I visit the messenger web site
    When I sign-in using my credentials
    And I see messenger signed-in page
    And I type "Customer Service" in the chat
    And I click on "FAQ" button
    And I click on "Orders" button
    And I click on "Pick Up In Store" button
    Then I see "Pick Up In Store FAQs" text


