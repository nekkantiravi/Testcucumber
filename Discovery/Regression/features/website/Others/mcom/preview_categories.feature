Feature: Verify Top Banner, Main Ads and featured categories on Category pages.

  #Top banner is not displayed for active & Brand category page
  @preview_desktop
  Scenario Outline: Verify Top Banner is displayed on category pages in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<category>" category page
    Then I verify top banner is displayed on "<category>" page

    Examples:
      | category   |
      | women      |
      | men        |
      | home       |
      | bed & bath |
      | shoes      |
      | handbags   |
      | beauty     |
      | kids       |
      | juniors    |
      | jewelry    |
      | watches    |
      #| gifts     |
      #| brands     |

  @preview_desktop @wip
  Scenario Outline: Verify main ad is displayed on category pages in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "<category>" category page
    Then I verify that Main Ads of "<category>" should be displayed on cat splash page

    Examples:
      | category   |
      | WOMEN      |
      | MEN        |
      | HOME       |
      | BED & BATH |
      | SHOES      |
      | HANDBAGS   |
      | BEAUTY     |
      | KIDS       |
      | JUNIORS    |
      | JEWELRY    |
      | WATCHES    |
     # | ACTIVE     |
      #| BRANDS     |


    #Top banner is currently displayed only in jewelry,watches & brands category pages in Iship mode.
  @preview_desktop
  Scenario: Verify Top Banner is displayed on category pages in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to all category pages and I verify top banner is displayed on category page:
      #| women                  |
      #| men                    |
      #| home                   |
      #| bed & bath             |
      #| shoes                  |
      #| handbags & accessories |
      #| kids                   |
      #| juniors                |
      #| jewelry |
      | watches |
      #| brands  |

  @preview_desktop
  Scenario Outline: Verify main ad is displayed on category pages in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "<category>" category page
    Then I verify that Main Ads of "<category>" should be displayed on cat splash page
    Examples:
      | category               |
      | women                  |
      | men                    |
      | home                   |
      | bed & bath             |
      | shoes                  |
      | handbags & accessories |
      | kids                   |
      | juniors                |
      | jewelry                |
      | watches                |
     # | brands                 |

  @preview_desktop
  Scenario: Verify Top Banner is displayed on category pages in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to all category pages and I verify top banner is displayed on registry category page:
      | dining                |
      | kitchen               |
      | bed & bath            |
      | home decor            |
      | luggage               |
      | cleaning & organizing |
      | wedding day           |

  @preview_desktop
  Scenario Outline: Verify main ad is displayed on category pages in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "<category>" category page
    Then I verify that Main Ads of "<category>" should be displayed on cat splash page
    Examples:
      | category              |
      | dining                |
      | kitchen               |
      | bed & bath            |
      | home decor            |
      | luggage               |
      | cleaning & organizing |
      | wedding day           |


# JEWELRY: For Dimond feature category browse back button is not working so we are skiiping JEWLERY category.Updated to the team about this defect
  @preview_desktop
  Scenario Outline: FeaturedCategories: Verify featured categories for category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "<Cat_name>" category page
    And I verify featured category header
  #And I click on thumbnail "Suits and Suit Separates" on featured categories
    And I navigate to all features categories link and verified
    Examples:
      | Cat_name   |
      | HOME       |
      | BED & BATH |
     # |KIDS|
     # |HANDBAGS|
     # |BEAUTY|
    #  |SHOES|
     # |MEN|
     # |WOMEN|
     # |JUNIORS|
     #|JEWELRY|
     # |WATCHES|

  @preview_desktop
  Scenario: FeaturedCategories: Verify essentials feature category for Home category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "HOME" category page and I navigate to essentials category page and verified the featured categories for following categories
      | Bed & Bath            |
      | Dining & Entertaining |
      | Furniture             |
      | Kitchen               |
      | Luggage & Backpacks   |
      | Mattresses            |

  @preview_desktop
  Scenario: FeaturedCategories: Verify essentials feature category for Women category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "WOMEN" category page and I navigate to essentials category page and verified the featured categories for following categories
      | All Plus Sizes       |
      | Lingerie & Shapewear |
      | All Petites          |

  @preview_desktop
  Scenario: FeaturedCategories: Verify essentials feature category for Men category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "MEN" category page and I navigate to essentials category page and verified the featured categories for following categories
      | All Men's Shoes |

  @preview_desktop
  Scenario: Verify Footer Section on Home Page
    Given I visit the web site as a guest user
    Then I should see footer elements section

  @preview_desktop
  Scenario: Verify Footer Section on all category page
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify footer section is displayed
      | WOMEN      |
      | MEN        |
      | HOME       |
      | BED & BATH |
      | SHOES      |
      | HANDBAGS   |
      | BEAUTY     |
      | KIDS       |
      | JUNIORS    |
      | JEWELRY    |
      | WATCHES    |
      | BRANDS     |


  @preview_desktop @wip
  Scenario: CategorySplash: Verify Left navigation Red link
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify left nav red link is clickable
      | WOMEN      |
      | MEN        |
      | HOME       |
      | BED & BATH |
      | SHOES      |
      | HANDBAGS   |
      | BEAUTY     |
      | KIDS       |
      | JUNIORS    |
      | JEWELRY    |
      | WATCHES    |
      | BRANDS     |


  @preview_desktop
  Scenario: CategoriesBrowsePages: Verify red lna links for following Women category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "WOMEN" category page and and I verify left nav red link is clickable for following subacategories:
      | All Plus Sizes       |
      | Lingerie & Shapewear |
      | All Petites          |

  @preview_desktop
  Scenario: CategoriesBrowsePages: Verify red lna links for following HOME category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "HOME" category page and and I verify left nav red link is clickable for following subacategories:
      | Bed & Bath            |
      | Dining & Entertaining |
      | Furniture             |
      | Kitchen               |
      | Luggage & Backpacks   |
      | Mattresses            |

  @preview_desktop
  Scenario: CategoriesBrowsePages: Verify red lna links for following MEN category page
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to "MEN" category page and and I verify left nav red link is clickable for following subacategories:
      | All Men's Shoes |

  @preview_desktop
  Scenario: CategoriesBrowsePages: Verify red lna links for Registry for following category page
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to following category pages and I verify left nav red link is clickable for registry page
      | Dining                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |
      | WEDDING DAY           |


  @preview_desktop
  Scenario Outline: CategorySplashPage - Verify Sub Ads validation for each categories REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    When I navigate to "<category>" category page
    Then I verify that Sub Ads of "<category>" should be displayed on cat splash page
    Examples:
      | category              |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | CLEANING & ORGANIZING |

  @preview_desktop
  Scenario: Verify pop ups in Kids Category - Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "Kids" category page
    Then I verify the pop up links for "Cat" Page

  @preview_desktop
  Scenario: Verify pop up in Home Page  - Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify the pop up links for "Home" Page