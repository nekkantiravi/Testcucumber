Feature: Beauty Box Feature File

  @tag @cloud
  Scenario:As a guest user i should be able to verify header and footer
    Given I visit the web site as a guest user
    And sample test: I click the logo
    And sample test: I navigate to sample page

  @wip
  Scenario Outline:As a customer I want to view the status on My subscription card
    Given I visit the web site as a guest user
    Then I view the Subscription Status "<status>"as below

    Examples:
    |status|
    |SUBSCRIBED|


  @memory
  Scenario Outline:As a user I should be able to validate Memory Profile
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I should be able to iterate "<iterations>" steps

    Examples:
    |iterations|
    |1        |
    |4        |

  @wip  @cloud
  Scenario Outline:As a customer I want to view the
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I connect to DB and insert records to see the subscribe status
    Then I view the Subscription Status "<status>"as below

    Examples:
  | segment_value |status|
  | 22805         |SUBSCRIBED|

  @wip @cloud
  Scenario Outline:As a customer I should be able to view the shipping address pre selected
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    #And I navigate to given "<URL>" url
    Then I should verify the shipping address as below

    Examples:
    |URL|
    |http://172.17.3.24:8081/checkout/appLayout?renderMode=client|