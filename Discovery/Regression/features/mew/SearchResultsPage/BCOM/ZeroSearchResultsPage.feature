Feature: Verify Search Zero Results Page contents in All modes

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ZSRPage - Verify the custom search messages in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the zero search results page with relevant message displayed
    And the featured labels should be displayed in the no results page
    Examples:
      | keyword                                                                  |
      | mvv                                                                      |
      | asdgd                                                                    |
      | asdfghjkiheryghlujikopiurtekahjashajdjhadhasbcjhbchjcgucheujcnjksandckas |
      | osnsh345eyy                                                              |
      | Shamballaa                                                               |
      | Mewastersonlyy                                                           |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ZSRPage - Verify the custom search messages in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the zero search results page with relevant message displayed
    And the featured labels should be displayed in the no results page
    Examples:
      | keyword                                                                  |
      | mvv                                                                      |
      | asdgd                                                                    |
      | asdfghjkiheryghlujikopiurtekahjashajdjhadhasbcjhbchjcgucheujcnjksandckas |
      | osnsh345eyy                                                              |
      | Shamballaa                                                               |
      | Mewastersonlyy                                                           |

  @wip @domain_mew_discovery @use_mew_regression
  Scenario Outline: ZSRPage - Verify the custom search messages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see a zero registry search results message on search landing page for the keyword "<keyword>"
    And I should see the registry links be displayed in the no results page
    Examples:
      | keyword                                                                  |
      | mvv                                                                      |
      | asdgd                                                                    |
      | asdfghjkiheryghlujikopiurtekahjashajdjhadhasbcjhbchjcgucheujcnjksandckas |
      | osnsh345eyy                                                              |
      | Shamballaa                                                               |
      | Mewastersonlyy                                                           |
