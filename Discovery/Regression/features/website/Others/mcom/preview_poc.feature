Feature: Preview Automation POC

  @preview_desktop_content
  Scenario: Verify Top Banner popups on Home Page - Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify "Top Banner" is displayed on Home Page
    Then I verify exclusion & details links for "Top Banner-Pop Up"
    And I verify "Top Banner-Pop Up" details as in below table:
      | Blank Popup     |
      | Popup ID        |
      | Macys logo      |
      | Offer details   |
      | Promo Code name |
      | End date        |
      | Close button    |
    Then I verify the "Top Banner" image with the saved images


    #Alomst 50 instance of Popup verification on Site
    #Site wide promo code

  @preview_desktop_content
  Scenario: Verify Main Ad popups on Home Page - Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify "Main Ad" is displayed on Home Page
    Then I verify exclusion & details links for "Main Ad-Pop Up"
    And I verify "Main Ad-Pop Up" details as in below table:
      | Blank Popup     |
      | Popup ID        |
      | Macys logo      |
      | Offer details   |
      | Promo Code name |
      | End date        |
      | Close button    |
    Then I verify the "Main Ad" image with the saved images

  @preview_desktop_content
  Scenario: Verify main ad links on Home pages in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify "Main Ad" is displayed on Home Page
    Then I verify the "Main Ad" image links with the sheet
    Then I verify the "Main Ad" image with the saved images

  @preview_desktop_content
  Scenario: Verify Sub Ads links on Home pages in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify "Sub Ad-1" is displayed on Home Page
    Then I verify the "Sub Ad-1" image links with the sheet
    Then I verify the "Sub Ad-1" image with the saved images

  @preview_desktop_content
  Scenario: Verify top banner ad and PopUp on Women category pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "WOMEN" category page
    Then I verify "Category" is displayed on Home Page
    Then I verify exclusion & details links for "Category-Pop Up"
    And I verify "Category-Pop Up" details as in below table:
      | Blank Popup     |
      | Popup ID        |
      | Macys logo      |
      | Offer details   |
      | Promo Code name |
      | End date        |
      | Close button    |
    Then I verify the "Category" image with the saved images

  Scenario Outline: Verify top banner ad and PopUp on category pages
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<category>" category page
    Then I verify "Category" is displayed on Home Page
    Then I verify exclusion & details links for "Category-Pop Up"
    And I verify "Category-Pop Up" details as in below table:
      | Blank Popup     |
      | Popup ID        |
      | Macys logo      |
      | Offer details   |
      | Promo Code name |
      | End date        |
      | Close button    |
    Then I verify the "Category" image with the saved images
    Examples:
      | category   |
      | MEN        |
   #   | home       |
   #   | bed & bath |
      | shoes      |
      | handbags   |
      #| beauty     |
      | kids       |
      | juniors    |
      #| jewelry    |
      #| watches    |



