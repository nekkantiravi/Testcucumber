Feature: To Verify Recent Searches functionality in Domestic and Iship modes

  @tag
  Scenario: Verify Recent Searches text in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    Then I should see "Recent Searches" text in recent search panel

  @tag
  Scenario: Verify last 5 recently searched keywords with search symbol in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    Then I should see recent search panel with max 5 last searches keywords

    @tag
  Scenario: Verify last 5 recently searched keywords with search symbol in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    Then I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent search functionality after clicking on recent search keyword in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I click on any random recent search keyword
    Then I should be in Search Landing page

  @tag
  Scenario: Verify recent search functionality when one character is entered in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I type one character in search box
    Then I should see recent search panel with max 5 last searches keywords

  @tag
  Scenario: Verify recent searches are not displayed when two characters are entered in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I type two characters "dr" in search box
    Then I should not see recent searches panel
    And I should see search keyword autocomplete suggestions

  @tag
  Scenario: Verify duplicate recent searches are not displayed in recent search panel
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Category Keyword  |Jeans    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    Then I should not see duplicate keywords in recent searches panel

  @tag
  Scenario: Verify recent searches functionality from category splash page
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Category Keyword  |Jeans    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I navigate to random category splash page
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent searches functionality from category browse page
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Category Keyword  |Jeans    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I navigate to the "Dresses" browse page under "JUNIORS"
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent searches functionality from PDP page
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Category Keyword  |Jeans    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I navigate to a random product
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent searches functionality from myaccount page
    #Adding sign-in page navigation two times because of the www1 and www & http and https issue
    Given I visit the web site as a guest user
    And I navigate to the sign-in page
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
    And I navigate to the sign-in page
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent searches functionality from shopping bag page
    Given I visit the web site as a guest user
#    Because of www1 and www issue adding shopping bag navigation here
    And I navigate to shopping bag page
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
    And I navigate to shopping bag page
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent searches functionality from all brands page
    Given I visit the web site as a guest user
    When I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
      |Category Keyword  |shirts    |
      |Category Keyword  |Jeans    |
      |Facet Keyword  |red dress  |
      |Facet Keyword  |Clearance shoes|
    And I navigate to brand index page in "domestic" mode
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

  @tag
  Scenario: Verify recent searches functionality from order history page
    Given I visit the web site as a guest user
    And I navigate to the sign-in page
    And I enter search keyword
      |Search_Type    |Keyword  |
      |Category Keyword  |Jeans    |
    And I navigate to order history page
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword

    #Note:: Need to update Iship mode scenarios