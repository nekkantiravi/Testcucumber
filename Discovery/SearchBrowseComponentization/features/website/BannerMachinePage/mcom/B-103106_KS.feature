# Author: QE Team
# Date Created: 1/28/2018
# Story B-101306

Feature: Verifying the KS for Banner Machine on different modes

#  KS ON scenarios
  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Catsplash page on registry modes
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4
  Scenario Outline: Verify Banner Machine media is displaying on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

#  KS OFF scenarios
  @SNBC_Phase4 @KS_Off
  Scenario Outline: Verify Banner Machine media is not displaying on Catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Dining & Entertaining" browse page under "HOME"
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4 @KS_Off
  Scenario Outline: Verify Banner Machine media is not displaying on Catsplash page on registry modes
    Given I am on CatSplash Page for "<category>" on "registry" mode
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | category |
      | DINING   |

  @SNBC_Phase4 @KS_Off
  Scenario Outline: Verify Banner Machine media is not displaying on Browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Cleaning & Organization" browse page under "HOME"
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4 @KS_Off
  Scenario Outline: Verify Banner Machine media is not displaying on Browse page on registry mode
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Clocks" browse page under "HOME DECOR"
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | registry      |

  @SNBC_Phase4 @KS_Off
  Scenario Outline: Verify Banner Machine media is not displaying on Subsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to the "Activewear" browse page under "MEN"
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

  @SNBC_Phase4 @KS_Off
  Scenario Outline: Verify Banner Machine media is not displaying on Search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I search for "shirts"
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |