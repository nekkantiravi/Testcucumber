# Author: DISCOVERY QE

Feature: Verify Browse Pages - Left Navigation

  @snbc_comp @use_dsv @use_regression @domain_discovery @priority_high @mode_domestic @dsv_sev2_dryrun @dsv_desktop_sev2
  Scenario Outline: BrowsePage - Verify BrowsePage Left Hand Nav - in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I clear existing class variables to avoid data issues
    Then I am on Browse Page for "<category>" under "<FOB>"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page
    Then I verify Left Nav section match with production
    Then I verify each link from Left Nav lands correctly on subcategory page

    Examples:
    |   category            |       FOB             |
    |   Michael Kors        |     HANDBAGS          |
    |   AQUA                |       WOMEN           |
    |   kate spade new york | JEWELRY & ACCESSORIES |
    |   Vince               |       MEN             |
    |   Ralph Lauren        |       KIDS            |
    |   Michael Aram        |       HOME            |
    |   Tory Burch          |      SHOES            |

  @snbc_comp @use_dsv @use_regression @domain_discovery @priority_high @mode_iship @dsv_sev2_dryrun @dsv_desktop_sev2
  Scenario Outline: BrowsePage - Verify BrowsePage Left Hand Nav - in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I clear existing class variables to avoid data issues
    Then I am on Browse Page for "<category>" under "<FOB>"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page
    Then I verify Left Nav section match with production
    Examples:
      |   category            |       FOB             |
      |   Michael Kors        |     HANDBAGS          |
      |   AQUA                |       WOMEN           |
      |   kate spade new york | JEWELRY & ACCESSORIES |
      |   Vince               |       MEN             |
      |   Ralph Lauren        |       KIDS            |
      |   Michael Aram        |       HOME            |
      |   Tory Burch          |      SHOES            |