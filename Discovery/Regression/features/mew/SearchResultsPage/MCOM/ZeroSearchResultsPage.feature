Feature: Verify Search Zero Results Page contents in All modes

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ZSRPage - Verify the custom search messages in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on zero search results page
    Then I should see a related search message on search landing page for the keyword "<keyword>"
    And the category icons should be available in the no results page
    Examples:
      | keyword                                                                  |
      | 12a                                                                      |
      | asdgd                                                                    |
      | asdfghjkiheryghlujikopiurtekahjashajdjhadhasbcjhbchjcgucheujcnjksandckas |
      | Obeyy                                                                    |
      | Shamballaa                                                               |
      | Mewastersonlyy                                                           |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ZSRPage - Verify the custom search messages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on zero search results page
    Then I should see a related search message on search landing page for the keyword "<keyword>"
    And the category icons should be available in the no results page
    Examples:
      | keyword                                                                  |
      | 12a                                                                      |
      | asdgd                                                                    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ZSRPage - Verify the custom search messages in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on zero search results page
    Then I should see a related search message on search landing page for the keyword "<keyword>"
    And the category icons should be available in the no results page
    Examples:
      | keyword                                                                  |
      | 12a                                                                      |
      | asdgd                                                                    |