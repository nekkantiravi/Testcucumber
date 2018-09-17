# Author: QE Team
# Date Created: 1/30/2018
# Story B-99259

Feature: Verifying the KS for Banner Machine on different modes

  ###Catsplash###
  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Catsplash page on registry mode
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on Catsplash page on registry mode
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Catsplash page on registry mode
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media banner machine supports main banner on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify banner machine supports main banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Catsplash page on registry mode
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify banner machine supports main banner
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Catsplash page on registry mode
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify banner machine supports banner link functionality
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on Catsplash page on registry mode
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | category |
      | DINING   |

    ### Subsplash page ###
  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media banner machine supports main banner on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify banner machine supports main banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
    #No sub splash pages in registry mode

    ### Search Page ###
  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media banner machine supports main banner on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify banner machine supports main banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

    ### Browse page ###
  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media banner machine supports main banner on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner machine supports main banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media banner machine supports main banner on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner machine supports main banner
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode |
      | registry      |

    #### SLP page ####
  @SNBC_Phase4
  Scenario: Verify Banner Machine media is displaying on SLP page on domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify banner machine is displayed in any row sequence or 101 row

  @SNBC_Phase4
  Scenario: Verify Banner Machine media supports different font types on SLP page on domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify banner machine supports different font types coming from Astra

  @SNBC_Phase4
  Scenario: Verify Banner Machine supports different background color on SLP page on domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify banner machine supports different background color coming from Astra

  @SNBC_Phase4
  Scenario: Verify Banner Machine media banner machine supports main banner on SLP page on domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify banner machine supports main banner

  @SNBC_Phase4
  Scenario: Verify Banner Machine media supports banner link functionality on SLP page on domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify banner machine supports banner link functionality

  @SNBC_Phase4
  Scenario: Verify banner image is displayed on right of left side of the banner on SLP page on domestic mode
    Given I am on SLP for "112746" category id in Domestic mode
    Then I verify banner image is displayed on right or left side of the banner

    ### DLP page###
  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on "<brand>" brand under brands section
    Then I verify banner machine is displayed in any row sequence or 101 row
    Examples:
      | shopping_mode | brand  |
      | domestic      | Anolon |
      | iship         | Anolon |
      | registry      | Anolon |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports different font types on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on "<brand>" brand under brands section
    Then I verify banner machine supports different font types coming from Astra
    Examples:
      | shopping_mode | brand  |
      | domestic      | Anolon |
      | iship         | Anolon |
      | registry      | Anolon |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine supports different background color on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on "<brand>" brand under brands section
    Then I verify banner machine supports different background color coming from Astra
    Examples:
      | shopping_mode | brand  |
      | domestic      | Anolon |
      | iship         | Anolon |
      | registry      | Anolon |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media banner machine supports main banner on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on "<brand>" brand under brands section
    Then I verify banner machine supports main banner
    Examples:
      | shopping_mode | brand  |
      | domestic      | Anolon |
      | iship         | Anolon |
      | registry      | Anolon |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media supports banner link functionality on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on "<brand>" brand under brands section
    Then I verify banner machine supports banner link functionality
    Examples:
      | shopping_mode | brand  |
      | domestic      | Anolon |
      | iship         | Anolon |
      | registry      | Anolon |

  @SNBC_Phase4
  Scenario Outline: Verify banner image is displayed on right of left side of the banner on DLP page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click on "<brand>" brand under brands section
    Then I verify banner image is displayed on right or left side of the banner
    Examples:
      | shopping_mode | brand  |
      | domestic      | Anolon |
      | iship         | Anolon |
      | registry      | Anolon |