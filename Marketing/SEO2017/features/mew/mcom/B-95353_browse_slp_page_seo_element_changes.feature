# Author: SEO Improvements 2017
# Date Created: 10/31/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:MEW:Browse and SLP Page Source Elements with seo
# Versionone story numbers:: B-95353
#########################################################################################################################

Feature: As an SEO Manager, I would like to analyze all the references of "seo" in the DOM elements (Page Source code) for all Browse, CAT_Splash, Sub_Splash, SLP & PDP pages.
################################## WSSG - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_wssg @use_manual
  Scenario: Verify the updated seo page elements on Browse page on MEW (WSSG)
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then  I should see updated seo page elements as below
      | new_element_copy_block_container     |
      | tag_cloud                            |
      | tag_cloud_links                      |
      | new_element_styles_toggle            |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_wssg @use_manual
  Scenario: Verify the updated seo page elements on Sub splash page on MEW (WSSG)
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      |Shop|
      |Men|
      |Activewear   |
    Then  I should see updated seo page elements as below
      | new_element_copy_block_container     |
      | tag_cloud                            |
      | tag_cloud_links                      |
      | new_element_styles_toggle            |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_wssg @use_manual
  Scenario: Verify the updated seo page elements on Catsplash page on MEW (WSSG)
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    Then I should see updated seo page elements as below
      | new_element_mkt_reverse_position_parent  |
      | tag_cloud          |
      | tag_cloud_links    |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_wssg @use_manual
  Scenario: Verify the updated seo page elements on SLP page on MEW (WSSG)
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see updated seo page elements as below
      | new_element_mkt_reverse_position_parent  |
      | tag_cloud                  |
      | tag_cloud_links            |
      | new_element_styles_toggle  |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_wssg
  Scenario: Verify the updated seo page elements on PDP page on MEW (WSSG)
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then I should see updated seo page elements as below
      | new_element_product_mkt_data |
      | tag_cloud                    |
      | tag_cloud_links              |

    ################################## XAPI - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi
  Scenario: Verify the updated seo page elements on Catsplash page on MeW (XAPI)
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Kids & Baby   |
    Then I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi
  Scenario: Verify the updated seo page elements on Browse page on MeW (XAPI)
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi
  Scenario: Verify the updated seo page elements on Subsplash  page on MeW (XAPI)
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      |Shop|
      |Men|
      |Activewear   |
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi
  Scenario: Verify the updated seo page elements on SLP page on MeW (XAPI)
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |

  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi @use_manual
  Scenario: Verify the updated seo page elements on PDP page on MeW (XAPI)
    Given I visit the mobile web site as a guest user
    And   I navigate to "master_product and link_module" product PDP page
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |
