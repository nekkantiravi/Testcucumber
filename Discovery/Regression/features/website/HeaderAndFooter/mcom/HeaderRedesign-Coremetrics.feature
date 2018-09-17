#Author: Discovery QE
#Date Created: 01/08/2017

Feature: Treatment A and Treatment B: Clean Exposed Navigation/Radical Navigation - Coremetrics Tagging

#Treatment - A

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline: Header - Domestic - Verify link click tags for Link Rail elements in Site mode
    Given I visit the web site as a guest user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements |
      | SIGN IN  |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline: Header - Registry - Verify 'SIGN IN' link click tags for Link Rail elements
    Given I visit the web site as a registered user in "registry" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements |
      | SIGN IN  |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline: Header - Domestic - Verify link click tags for HnF Clean view Link Rail elements
    Given I visit the web site as a registered user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements              |
      | SIGN OUT              |
      | MY ACCOUNT            |
      | MY MACY'S CREDIT CARD |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY LISTS              |
      | MY PLENTI             |
      | STORES                |
      | WEDDING REGISTRY      |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline: Header - Registry - Verify link click tags for HnF Clean view Link Rail elements
    Given I visit the web site as a registered user in "registry" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements              |
      | SIGN OUT              |
      | MY ACCOUNT            |
      | MY MACY'S CREDIT CARD |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY LISTS              |
      | MY PLENTI             |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:  Header - Domestic|Registry|Iship - Verify link click tags for clicking on any category splash page from FOB
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I navigate to random category splash page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:   Header - Domestic|Registry|Iship - Verify link click tags for clicking on any category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I navigate to random category browse page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:  Header - Verify link click tags on clicking macys logo in Site/Registry and Iship modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    And I verify that Macys logo takes to Home Page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:  Header - Verify link click tags on clicking shopping bag in Site/Registry and Iship modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on the new bag icon
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  Scenario Outline: Header - Verify link click tags for Link Rail elements in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements    |
      | SHIPPING TO |

  Scenario Outline: Verify link click tag is fired when a user clicks on any element on link rail and search rail in registry mode
    Given I visit the web site as a registry user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<element>" link in Link rail for registry
    Examples:
      | element          |
      | REGISTRY MANAGER |
      | VIEW REGISTRY    |

  Scenario Outline: Verify link click tag is fired when a user clicks on any element on link rail and search rail in registry mode
    Given I visit the web site as a guest user in "registry" mode
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I click on "<element>" link in Link rail for registry
    Examples:
      | element         |
      | FIND REGISTRY   |
      | CREATE REGISTRY |
      | BENEFITS        |
      | HELP            |

    #Treatment - B

  Scenario Outline: Header - Verify link click tags for Link Rail elements in Site mode
    Given I visit the web site as a guest user
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements |
      | SIGN IN  |

  Scenario Outline: Header - Verify 'SIGN IN ' link click tags for Link Rail elements in Registry mode
    Given I visit the web site as a registered user in "registry" mode
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements |
      | SIGN IN  |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline: Header - Verify link click tags for Link Rail elements in Site mode
    Given I visit the web site as a registered user
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<elements>" link in Link rail
    Examples:
      | elements              |
      | SIGN OUT              |
      | MY ACCOUNT            |
      | MY MACY'S CREDIT CARD |
      | MY ORDER HISTORY      |
      | MY PROFILE            |
      | MY WALLET             |
      | MY LISTS              |
      | MY PLENTI             |
      | STORES                |
      | Wedding Registry      |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:  Header - Verify link click tags on clicking any category from FOB in Site/Registry and Iship modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I navigate to random category splash page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:   Header - Verify link click tags on clicking any sub category in Site/Registry and Iship modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I navigate to random category browse page
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign_coremetrics
  Scenario Outline:  Header - Verify link click tags on clicking shopping bag
    Given I visit the web site as a guest user in "<mode_name>" mode
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on the new bag icon
    Examples:
      | mode_name |
      | domestic  |
      | registry  |
      | iship     |

  Scenario Outline: Verify link click tag is fired when a user clicks on any element on link rail and search rail in registry mode
    Given I visit the web site as a registry user
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<element>" link in Link rail for registry
    Examples:
      | element          |
      | REGISTRY MANAGER |
      | VIEW REGISTRY    |

  Scenario Outline: Verify link click tag is fired when a user clicks on any element on link rail and search rail in registry mode
    Given I visit the web site as a guest user in "registry" mode
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<element>" link in Link rail for registry
    Examples:
      | element         |
      | FIND REGISTRY   |
      | CREATE REGISTRY |
      | BENEFITS        |
      | HELP            |

  Scenario Outline: Verify link click tag is fired when a user clicks on any element on link rail and search rail in registry mode
    Given I visit the web site as a registry user
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<element>" link in Link rail for registry
    Examples:
      | element          |
      | REGISTRY MANAGER |
      | VIEW REGISTRY    |

  Scenario Outline: Verify link click tag is fired when a user clicks on any element on link rail and search rail in domestic mode
    Given I visit the web site as a guest user in "registry" mode
    And Navigate to "HnF Radical" viewtype in new header footer experience
    When I click on "<element>" link in Link rail for registry
    Examples:
      | element         |
      | FIND REGISTRY   |
      | CREATE REGISTRY |
      | BENEFITS        |
      | HELP            |