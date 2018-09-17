# Author: SEO Regression
# Date Created: 23/10/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM/BCOM : SEO : QE Automation Regression
# Version One story numbers::  B-95220
#########################################################################################################################

Feature: This is a QE only Automation story where we will address all the SEO functionality Automation back log. Will add the confluence page here which will have the SEO functionality that is Automated and yest to be Automated.

###################################################### Catsplash Page  ########################################################################################

  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17O @priority_high
  Scenario: Verify the Page title, Page Header, Page Description, Canonical Tag, Alternate Tag on Catsplash Pages
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I should see page title matches with Stella page title
    And I should see page header is matched the with "WOMEN" name
    And I verify the title for "WOMEN" category page
    And I verify the "canonical" tag in HTML view source code
    #And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And I should see page description as expected

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the link module functionality in Catsplash page in DOMESTIC mode
    Given I am on CatSplash Page for "women" on "domestic" mode
    Then I should see the tag cloud
    And  I should see tag cloud title font family is Helvetica Neue LT Std
    And  I should see tag cloud title font size is forteen pixels
    And  I should see tag cloud title font weight is Bold
    And  I should see keywords in the seo tag cloud section one after the another
    And  I should see ten links in the tag cloud
    And  I should see all the keywords are underlined
    And  I should see tag cloud links font family is Helvetica Neue LT Std
    And  I should see tag cloud links font size is 14px
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in Catsplash page in DOMESTIC mode
    Given I am on CatSplash Page for "women" on "domestic" mode
    And I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop

      #################################### Catsplash page - Iship Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the tags(title,header,canonical,alternate) and page description in Catsplash page in International mode
    Given I am on CatSplash Page for "WOMEN" on "iship" mode
    Then I should see page title matches with Stella page title
    And I should see page header is matched the with "WOMEN" name
    And I verify the title for "WOMEN" category page
    And I verify the "canonical" tag in HTML view source code
    #And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And I should see page description as expected


  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in Catsplash page in International mode
    Given I am on CatSplash Page for "women" on "iship" mode
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop

     #################################### Browse page - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the page title and facet threshold functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY" category
    Then  I should see page title matches with Stella page title
    And   I should see facets listed on left nav
    Then I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see only one facet value in title tag according to alpha order
    When  I deselect all facet values in facet panel
    Then  I should see page title matches with Stella page title
    Then I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see page title matches with Stella page title
    Then I should not see non crawble facet values in title tag
    When  I deselect all facet values in facet panel
    Then I select 3 facet values in facet panel with "crawlable" facets
   Then I should see only 2 facet values based on facet types alphanumeric order in title tag

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the page header functionality and alternate tag in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY" category
    Then I should see page header is matching with Category Name
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in header tag
    When  I deselect all facet values in facet panel
    Then I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see only one facet value in header tag according to alpha order
    When  I deselect all facet values in facet panel
    Then  I should see page header is matching with Category Name
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see page header is matching with Category Name
    When I select 3 facet values in facet panel with "crawlable" facets
    Then I should see only 2 facet values based on facet types alphanumeric order in page header

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the canonical tag functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY" category
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When I select 2 facet values in facet panel with "crawlable" facets
    Then  I should see selected facet values based on facet value alphanumeric order in canonical tag


  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY" category
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the link module functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY" category
    Then I should see the tag cloud
    And  I should see tag cloud title font family is Helvetica Neue LT Std
    And  I should see tag cloud title font size is forteen pixels
    And  I should see tag cloud title font weight is Bold
    And  I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And  I should see all the keywords are underlined
    And  I should see tag cloud links font family is Helvetica Neue LT Std
    And  I should see tag cloud links font size is 14px
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the meta description functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "BROWSE_CATEGORY_WITH_METADESCRIPTION" category
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should not see selected facet value in metadescription
    When I deselect all facet values in facet panel

 #################################### Browse page - Iship Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the page title and facet threshold functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "BROWSE_CATEGORY" category
    Then  I should see page title matches with Stella page title
    And   I should see facets listed on left nav
    Then I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see only one facet value in title tag according to alpha order
    When  I deselect all facet values in facet panel
    Then  I should see page title matches with Stella page title
    Then I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see page title matches with Stella page title
    Then I should not see non crawble facet values in title tag
    When  I deselect all facet values in facet panel
    Then I select 3 facet values in facet panel with "crawlable" facets
    Then I should see only 2 facet values based on facet types alphanumeric order in title tag

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the page header functionality and alternate tag in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "BROWSE_CATEGORY" category
    Then I should see page header is matching with Category Name
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in header tag
    When  I deselect all facet values in facet panel
    Then I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see only one facet value in header tag according to alpha order
    When  I deselect all facet values in facet panel
    Then  I should see page header is matching with Category Name
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see page header is matching with Category Name
    When I select 3 facet values in facet panel with "crawlable" facets
    Then I should see only 2 facet values based on facet types alphanumeric order in page header
#    And   I select 'single' facet value from multiple 'Crawlable' facet section
#    Then  I should see only 2 facet values based on facet types alphanumeric order in header tag

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the canonical tag functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "BROWSE_CATEGORY" category
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When I select 2 facet values in facet panel with "crawlable" facets
    Then  I should see selected facet values based on facet value alphanumeric order in canonical tag


  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "BROWSE_CATEGORY" category
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the link module functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "BROWSE_CATEGORY" category
    Then I should see the tag cloud
    And  I should see tag cloud title font family is Helvetica Neue LT Std
    And  I should see tag cloud title font size is forteen pixels
    And  I should see tag cloud title font weight is Bold
    And  I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And  I should see all the keywords are underlined
    And  I should see tag cloud links font family is Helvetica Neue LT Std
    And  I should see tag cloud links font size is 14px
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the meta description functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "BROWSE_CATEGORY_WITH_METADESCRIPTION" category
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should not see selected facet value in metadescription
    When I deselect all facet values in facet panel

     #################################### SLP page - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the page title and facet threshold functionality in SLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then  I should see page title matches with Stella page title
    And   I should see facets listed on left nav
    Then I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see only one facet value in title tag according to alpha order
    When  I deselect all facet values in facet panel
    Then  I should see page title matches with Stella page title
    Then I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see page title matches with Stella page title
    Then I should not see non crawble facet values in title tag
    When  I deselect all facet values in facet panel
    Then I select 3 facet values in facet panel with "crawlable" facets
    Then I should see only 2 facet values based on facet types alphanumeric order in title tag

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the alternate and canonical tag functionality in SLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When I select 2 facet values in facet panel with "crawlable" facets
    Then  I should see selected facet values based on facet value alphanumeric order in canonical tag


  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in SLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the link module functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the tag cloud
    And  I should see tag cloud title font family is Helvetica Neue LT Std
    And  I should see tag cloud title font size is forteen pixels
    And  I should see tag cloud title font weight is Bold
    And  I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And  I should see all the keywords are underlined
    And  I should see tag cloud links font family is Helvetica Neue LT Std
    And  I should see tag cloud links font size is 14px
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the meta description functionality in Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should not see selected facet value in metadescription
    When I deselect all facet values in facet panel

    #################################### SLP page - Iship Mode ###################################################

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the page title and facet threshold functionality in SLP page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then  I should see page title matches with Stella page title
    And   I should see facets listed on left nav
    Then I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see only one facet value in title tag according to alpha order
    When  I deselect all facet values in facet panel
    Then  I should see page title matches with Stella page title
    Then I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see page title matches with Stella page title
    Then I should not see non crawble facet values in title tag
    When  I deselect all facet values in facet panel
    Then I select 3 facet values in facet panel with "crawlable" facets
    Then I should see only 2 facet values based on facet types alphanumeric order in title tag

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the alternate and canonical tag functionality in SLP page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When  I deselect all facet values in facet panel
    Then  I should not see selected facet value in canonical tag
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should see selected facet value in canonical tag url
    When I select 2 facet values in facet panel with "crawlable" facets
    Then  I should see selected facet values based on facet value alphanumeric order in canonical tag

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in SLP page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the link module functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the tag cloud
    And  I should see tag cloud title font family is Helvetica Neue LT Std
    And  I should see tag cloud title font size is forteen pixels
    And  I should see tag cloud title font weight is Bold
    And  I should see keywords in the seo tag cloud section one after the another
    And  I should see six links in the tag cloud
    And  I should see all the keywords are underlined
    And  I should see tag cloud links font family is Helvetica Neue LT Std
    And  I should see tag cloud links font size is 14px
    When I click on any keyword in the link module
    Then I should see the relevant page is displayed

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the meta description functionality in Browse page in INTL mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 2 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in metadescription
    When I deselect all facet values in facet panel
    When I select 1 facet values in facet panel from single "non_crawlable" facet
    Then  I should not see selected facet value in metadescription
    When I deselect all facet values in facet panel

    #################################### Search page - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the Alternate, Robots and Canonical tag in Onsite search page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "august hats"
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url


  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in Onsite search page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "august hats"
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

    #################################### Search page - Iship Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the tags(canonical,alternate,robots) in Onsite search page in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "august hats"
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in Onsite search page in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "august hats"
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

    #################################### DLP page - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the tags(canonical,alternate,robots) in DLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigates DLP page through Brand
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in DLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigates DLP page through Brand
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
    And I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

     #################################### DLP page - Iship Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the tags(canonical,alternate,robots) in DLP page in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigates DLP page through Brand
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source
#    And I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    And   I should see facets listed on left nav
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then  I should see selected facet value in canonical tag url

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17T
  Scenario: Verify the copy block functionality in DLP page in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigates DLP page through Brand
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I should see copy blocks displayed higher up in the page view source in desktop
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source in desktop
    When I select 1 facet values in facet panel from single "crawlable" facet
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I click second page in pagination
    Then I should see Copy Block "removed"
    When I click first page in pagination
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "Price: High to Low" in sort by drop down
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "4" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select "3" Column Grid icon
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    And  I select "60" item count option
    Then I should see "COPY_BLOCK" on the page in "101,0" rows