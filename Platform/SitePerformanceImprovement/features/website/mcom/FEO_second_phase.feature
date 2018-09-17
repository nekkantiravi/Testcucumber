Feature: Verifying the existing functionalities on FEO pages in second phase


  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the search field and auto suggestions in FEO Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify search box is displayed
    When I enter "jea" keyword in search field
    Then I should see autocomplete suggestions list is populated
    Examples:
      |subcategory_name||category_name|
      |Boots||SHOES                   |
      #|Fossil||HANDBAGS|
      #|Coats ||WOMEN   |
      #|Sweaters||WOMEN |
      #|50-75% Off Clearance||SHOES|
      #|MICHAEL Michael Kors||HANDBAGS|
      #|Kitchen||HOME|
      #|Coats & Jackets||MEN|
      #|Tops||WOMEN|
      #|Booties||SHOES|
      #|Sweaters||MEN|
      #|40-70% Off Clearance||HANDBAGS|
      #|Pajamas, Robes & Loungewear||WOMEN|
      #|Activewear||WOMEN|
      #|All Fashion Jewelry||JEWELRY|
      #|Big Girls (7-16)||KIDS|
      #|Bed in a Bag||BED & BATH|
      #|Designer Handbags||HANDBAGS|
      #|Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify zero nav Header links in FEO Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify the TopNav elements are present in the UI for "guest" user in "Domestic" mode
    And I click on link HF links and browse back to verify page got optimized again
      |SIGN IN|
      |MY ACCOUNT       |
      |STORES           |
      |DEALS            |
      |LISTS            |
      |GIFTS            |
      |WEDDING REGISTRY |
    Examples:
      |subcategory_name||category_name|
      |Boots||SHOES                   |
      #|Fossil||HANDBAGS|
      #|Coats ||WOMEN   |
      #|Sweaters||WOMEN |
      #|50-75% Off Clearance||SHOES|
      #|MICHAEL Michael Kors||HANDBAGS|
      #|Kitchen||HOME|
      #|Coats & Jackets||MEN|
      #|Tops||WOMEN|
      #|Booties||SHOES|
      #|Sweaters||MEN|
      #|40-70% Off Clearance||HANDBAGS|
      #|Pajamas, Robes & Loungewear||WOMEN|
      #|Activewear||WOMEN|
      #|All Fashion Jewelry||JEWELRY|
      #|Big Girls (7-16)||KIDS|
      #|Bed in a Bag||BED & BATH|
      #|Designer Handbags||HANDBAGS|
      #|Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Quick bag functionality in FEO Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I hover over the quick bag
    Then I should see empty quickbag message
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
      |Fossil||HANDBAGS|
      #|Coats ||WOMEN   |
      #|Sweaters||WOMEN |
      #|50-75% Off Clearance||SHOES|
      #|MICHAEL Michael Kors||HANDBAGS|
      #|Kitchen||HOME|
      #|Coats & Jackets||MEN|
      #|Tops||WOMEN|
      #|Booties||SHOES|
      #|Sweaters||MEN|
      #|40-70% Off Clearance||HANDBAGS|
      #|Pajamas, Robes & Loungewear||WOMEN|
      #|Activewear||WOMEN|
      #|All Fashion Jewelry||JEWELRY|
      #|Big Girls (7-16)||KIDS|
      #|Bed in a Bag||BED & BATH|
      #|Designer Handbags||HANDBAGS|
      #|Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer links under customer services in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |CUSTOMER SERVICE|
      |order tracking|
      |shipping & delivery|
      |returns            |
      |contact us         |
      |para ayuda         |
      |change country     |
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
      |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer links under macy's credit card in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S CREDIT CARD |
      |pay bill           |
      |cardholder benefits|
      |learn more & apply |
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
      |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer links under our stores in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |OUR STORES         |
      |locations & hours  |
      |store events       |
      |catalogs           |
      |tell us what you think|
      |my stylist personal shopping|
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
      |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer links under macy's Inc in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S INC.                 |
      |macysJOBS                   |
      |press room                  |
      |investors                   |
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
      |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer links under stay connected in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |STAY CONNECTED              |
      |mobile app                  |
      |sign up for email           |
      |See how we're improving     |
      |WHAT'S HAPPENING AT MACY'S  |
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
      # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
      |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer social links in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on footer social media links and browse back to verify page got optimized again
      |facebook|
      |twitter |
      |pinterest|
      |youtube  |
      |macys m-blog|
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
      |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the Footer ad links in FEO wildcard Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I click on footer ad link and browse back to verify page got optimized again
      |Macy's Culinary Council|
      |green living|
      |the magic of giving|
      |macys video channel|
    Examples:
      |subcategory_name||category_name|
      #|Boots||SHOES                   |
      #|Fossil||HANDBAGS|
      #|Coats ||WOMEN   |
      #|Sweaters||WOMEN |
      #|50-75% Off Clearance||SHOES|
      #|MICHAEL Michael Kors||HANDBAGS|
      #|Kitchen||HOME|
     # |Coats & Jackets||MEN|
      #|Tops||WOMEN|
      |Booties||SHOES|
      #|Sweaters||MEN|
      #|40-70% Off Clearance||HANDBAGS|
      #|Pajamas, Robes & Loungewear||WOMEN|
      #|Activewear||WOMEN|
      #|All Fashion Jewelry||JEWELRY|
      #|Big Girls (7-16)||KIDS|
      #|Bed in a Bag||BED & BATH|
      #|Designer Handbags||HANDBAGS|
      #|Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the FOB flyouts in FEO Browse pages for second phase pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that flyout menu is displayed for FOBs
      |WOMEN |
      |MEN|
      |HOME|
      |BED & BATH|
      |SHOES|
      |HANDBAGS|
      |BEAUTY|
      |KIDS  |
      |JUNIORS |
      |JEWELRY|
      |WATCHES|
      |ACTIVE|
      |BRANDS|
    Examples:
      |subcategory_name||category_name|
      #|Boots||SHOES                   |
      #|Fossil||HANDBAGS|
      #|Coats ||WOMEN   |
      #|Sweaters||WOMEN |
      #|50-75% Off Clearance||SHOES|
      #|MICHAEL Michael Kors||HANDBAGS|
      #|Kitchen||HOME|
      #|Coats & Jackets||MEN|
      |Tops||WOMEN|
      #|Booties||SHOES|
      #|Sweaters||MEN|
      #|40-70% Off Clearance||HANDBAGS|
      #|Pajamas, Robes & Loungewear||WOMEN|
      #|Activewear||WOMEN|
      #|All Fashion Jewelry||JEWELRY|
      #|Big Girls (7-16)||KIDS|
      #|Bed in a Bag||BED & BATH|
      #|Designer Handbags||HANDBAGS|
      #|Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_SecondPhase
  Scenario Outline: Verify leftnav links navigating to respective Sub-Category pages from Optimized page
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I navigate to random sub categories from Left hand nav
    And I select browse 'back' button
    Then I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
      |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and product thumbnails are available and clickable
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that page is served from "FEO" tag
    Then I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      |subcategory_name||category_name|
    #  |Boots||SHOES                   |
    #  |Fossil||HANDBAGS|
    #  |Coats ||WOMEN   |
    #  |Sweaters||WOMEN |
    #  |50-75% Off Clearance||SHOES|
    #  |MICHAEL Michael Kors||HANDBAGS|
    #  |Kitchen||HOME|
    #  |Coats & Jackets||MEN|
    #  |Tops||WOMEN|
    #  |Booties||SHOES|
    #  |Sweaters||MEN|
    #  |40-70% Off Clearance||HANDBAGS|
    #  |Pajamas, Robes & Loungewear||WOMEN|
    #  |Activewear||WOMEN|
      |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and getting ensure top icon working as expected
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
      |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify filter products when we select any one random facet value
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    Then I verify that page is served from "FEO" tag
    When I click on clear all button
    Then I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
      |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify pagination using Next / Previous button in DOMESTIC mode
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that pagination is displayed
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    Then I verify that page is served from "FEO" tag
    When I click on previous pagination button
    Then I verify that navigated to page 1 on search result page
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
      |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify Sort By functionality
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that the Sort By displayed with all options
    When I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality
    Then I verify that page is served from "FEO" tag
    Then I select browse 'back' button
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
      |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify Top Banner
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    Then I verify top banner is displayed on "search result" browse page
    Examples:
      |subcategory_name||category_name|
    #  |Boots||SHOES                   |
    #  |Fossil||HANDBAGS|
    #  |Coats ||WOMEN   |
    #  |Sweaters||WOMEN |
    #  |50-75% Off Clearance||SHOES|
      |MICHAEL Michael Kors||HANDBAGS|
    #  |Kitchen||HOME|
    #  |Coats & Jackets||MEN|
    #  |Tops||WOMEN|
    #  |Booties||SHOES|
    #  |Sweaters||MEN|
    #  |40-70% Off Clearance||HANDBAGS|
    #  |Pajamas, Robes & Loungewear||WOMEN|
    #  |Activewear||WOMEN|
    #  |All Fashion Jewelry||JEWELRY|
    #  |Big Girls (7-16)||KIDS|
    #  |Bed in a Bag||BED & BATH|
    #  |Designer Handbags||HANDBAGS|
    #  |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and verify Quick View overlay
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
      |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization and product thumbnail has title, colors, price lable and quickview
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      |subcategory_name||category_name|
      |Boots||SHOES                   |
    #  |Fossil||HANDBAGS|
    #  |Coats ||WOMEN   |
    #  |Sweaters||WOMEN |
    #  |50-75% Off Clearance||SHOES|
    #  |MICHAEL Michael Kors||HANDBAGS|
    #  |Kitchen||HOME|
    #  |Coats & Jackets||MEN|
    #  |Tops||WOMEN|
    #  |Booties||SHOES|
    #  |Sweaters||MEN|
    #  |40-70% Off Clearance||HANDBAGS|
    #  |Pajamas, Robes & Loungewear||WOMEN|
    #  |Activewear||WOMEN|
    #  |All Fashion Jewelry||JEWELRY|
    #  |Big Girls (7-16)||KIDS|
    #  |Bed in a Bag||BED & BATH|
    #  |Designer Handbags||HANDBAGS|
    #  |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify Recently viewed panel on Optimized browse page
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I visit "4" random items
    Then I verify the display of recently viewed panel
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES    |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
      |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization on performing pagination and sortby
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that pagination is displayed
    When I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    And I verify that page is served from "FEO" tag
    And I verify that the Sort By displayed with all options
    When I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES    |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
      |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify the page is served from Feo optimization on performing faceting and sortby
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that page is served from "FEO" tag
    And I verify that the Sort By displayed with all options
    When I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
    #  |Boots||SHOES    |
    #  |Fossil||HANDBAGS|
    #  |Coats ||WOMEN   |
    #  |Sweaters||WOMEN |
    #  |50-75% Off Clearance||SHOES|
      |MICHAEL Michael Kors||HANDBAGS|
    #  |Kitchen||HOME|
    #  |Coats & Jackets||MEN|
    #  |Tops||WOMEN|
    #  |Booties||SHOES|
    #  |Sweaters||MEN|
    #  |40-70% Off Clearance||HANDBAGS|
    #  |Pajamas, Robes & Loungewear||WOMEN|
    #  |Activewear||WOMEN|
    #  |All Fashion Jewelry||JEWELRY|
    #  |Big Girls (7-16)||KIDS|
    #  |Bed in a Bag||BED & BATH|
    #  |Designer Handbags||HANDBAGS|
    #  |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify facet selection persistence with special characters facet for optimized page
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that the product count is displayed
    When I search for "Style & Co" keyword in brand facet section
    And I select "Style & Co" facet value from Brand facet section
    Then I verify that page is served from "FEO" tag
    And I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SubSplashPage on same product grid point
    And I verify that page is served from "FEO" tag
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES    |
      |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Activewear||WOMEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify that products are displayed based on the 2 selected colors on optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select the first two colors in the Color facet
    Then I verify that page is served from "FEO" tag
    And I verify that the product thumbnails are displayed with the selected colors
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Coats & Jackets||MEN|
      |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify unselecting of color facet from overlay for optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    And I verify that page is served from "FEO" tag
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
      |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify PDP for facet size selection on optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that "Size" facet is listed on left nav
    When I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    When I select "first" size value from Size facet section
    Then I verify that page is served from "FEO" tag
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    Then I verify that the "PDP" is showing the selected size
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
      |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify facet de-selection with check box under Size facet section on optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
 #Note : Select the first one from each of the multi select option under the size facet
    Then I verify that the product count is updated
    And I verify that page is served from "FEO" tag
    When I deselect the Size from the overlay
    Then I verify that the product count returns to original
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
      |Sweaters||MEN|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify products are filtered with custom price facet range for optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that page is served from "FEO" tag
    And I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
    Examples:
      |subcategory_name||category_name|
    #  |Boots||SHOES                   |
    #  |Fossil||HANDBAGS|
    #  |Coats ||WOMEN   |
      |Sweaters||WOMEN |
    #  |50-75% Off Clearance||SHOES|
    #  |MICHAEL Michael Kors||HANDBAGS|
    #  |Coats & Jackets||MEN|
    #  |Tops||WOMEN|
    #  |Booties||SHOES|
    #  |Sweaters||MEN|
    #  |40-70% Off Clearance||HANDBAGS|
    #  |Pajamas, Robes & Loungewear||WOMEN|
    #  |Activewear||WOMEN|
    #  |All Fashion Jewelry||JEWELRY|
    #  |Big Girls (7-16)||KIDS|
    #  |Bed in a Bag||BED & BATH|
    #  |Designer Handbags||HANDBAGS|
    #  |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify Edit button is displayed on RVI Panel in Optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I view 2 random products
    And I select browse 'back' button
    Then I verify the display of recently viewed panel
    And I verify that page is served from "FEO" tag
    And I should see edit option inside Recently Viewed panel
    When I click "edit" button in RVI panel
    Then I should see highlighted Remove button on each product and Edit button displayed as Done in RVI panel
    When I click on "Remove" button on any product
    Then I verify product is removed temporally
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |40-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
      |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify RVI panel not displayed after items removed on optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I view 3 random products
    And I select browse 'back' button
    Then I verify that page is served from "FEO" tag
    And I verify the display of recently viewed panel
    When I remove all Recently viewed items
    Then I should not see recently viewed items section
    And I verify that page is served from "FEO" tag
    Examples:
      |subcategory_name||category_name|
      #|Boots||SHOES                   |
      #|Fossil||HANDBAGS|
      #|Coats ||WOMEN   |
      #|Sweaters||WOMEN |
      #|50-75% Off Clearance||SHOES|
      #|MICHAEL Michael Kors||HANDBAGS|
      #|Kitchen||HOME|
      #|Coats & Jackets||MEN|
      #|Tops||WOMEN|
      #|Booties||SHOES|
      #|Sweaters||MEN|
      #|40-70% Off Clearance||HANDBAGS|
      #|Pajamas, Robes & Loungewear||WOMEN|
      |Activewear||WOMEN|
      #|All Fashion Jewelry||JEWELRY|
      #|Big Girls (7-16)||KIDS|
      #|Bed in a Bag||BED & BATH|
      #|Designer Handbags||HANDBAGS|
      #|Boots||MEN|
    #--------------------------------------------------------Validation for Optimized Search Result Page --------------------------------------------------------------------

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the FOB flyouts in FEO Search pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I verify that flyout menu is displayed for FOBs
      |WOMEN |
      |MEN|
      |HOME|
      |BED & BATH|
      |SHOES|
      |HANDBAGS|
      |BEAUTY|
      |KIDS  |
      |JUNIORS |
      |JEWELRY|
      |WATCHES|
      |ACTIVE|
      |BRANDS|
    Examples:
      |Search_cat|
    #  |Mugs|
    #  |Jeans  |
    #  |Knife  |
    #  |Electronics|
      |sandals    |
    #  |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the search field and auto suggestions in FEO Browse pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I verify search box is displayed
    When I enter "jea" keyword in search field
    Then I should see autocomplete suggestions list is populated
    Examples:
      |Search_cat|
    #  |Mugs|
      |Jeans  |
    #  |Knife  |
    #  |Electronics|
    #  |sandals    |
    #  |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the zero nav Header links for Feo Browse pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I verify the TopNav elements are present in the UI for "guest" user in "Domestic" mode
    And I click on link HF links and browse back to verify page got optimized again
      |SIGN IN|
      |MY ACCOUNT       |
      |STORES           |
      |DEALS            |
      |LISTS            |
      |GIFTS            |
      |WEDDING REGISTRY |
    Examples:
      |Search_cat|
      |Mugs|
     # |Jeans  |
     # |Knife  |
     # |Electronics|
     # |sandals    |
     # |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Quick bag functionality on FEO Browse pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    When I hover over the quick bag
    Then I should see empty quickbag message
    Examples:
      |Search_cat|
      |Mugs|
     # |Jeans  |
     # |Knife  |
     # |Electronics|
     # |sandals    |
     # |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Footer links under customer services in FEO wildcard search pages for second phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |CUSTOMER SERVICE|
      |order tracking|
      |shipping & delivery|
      |returns            |
      |contact us         |
      |para ayuda         |
      |change country     |
    Examples:
      |Search_cat|
     # |Mugs|
     # |Jeans  |
      |Knife  |
     # |Electronics|
     # |sandals    |
     # |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Footer links under macy's credit card in FEO wildcard search pages for second phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S CREDIT CARD |
      |pay bill           |
      |cardholder benefits|
      |learn more & apply |
    Examples:
      |Search_cat|
    #  |Mugs|
     # |Jeans  |
     # |Knife  |
     # |Electronics|
      |sandals    |
     # |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Footer links under our stores in FEO wildcard search pages for second phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |OUR STORES         |
      |locations & hours  |
      |store events       |
      |catalogs           |
      |tell us what you think|
      |my stylist personal shopping|
    Examples:
      |Search_cat|
     # |Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      |sandals    |
      #|lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Footer links under macy's Inc in FEO wildcard search pages for second phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |MACY'S INC.                 |
      |macysJOBS                   |
      |press room                  |
      |investors                   |
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      |sandals    |
      #|lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Footer links under stay connected in FEO wildcard search pages for second phase pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on link HF links and browse back to verify page got optimized again
      |STAY CONNECTED              |
      |mobile app                  |
      |sign up for email           |
      |See how we're improving     |
      |WHAT'S HAPPENING AT MACY'S  |
    Examples:
      |Search_cat|
     # |Mugs|
     # |Jeans  |
     # |Knife  |
     # |Electronics|
      |sandals    |
     # |lipsticks  |


  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the Footer social links for Feo second phase search pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on footer social media links and browse back to verify page got optimized again
      |facebook|
      |twitter |
      |pinterest|
      |youtube  |
      |macys m-blog|
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      |sandals    |
      #|lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimized configuration for Footer ad links for Feo second phase search pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I click on footer ad link and browse back to verify page got optimized again
      |Macy's Culinary Council|
      |green living|
      |the magic of giving|
      |macys video channel|
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify Recently viewed panel on Optimized browse page for wildcard search pages
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    When I visit "4" random items
    Then I verify the display of recently viewed panel
    And I verify that page is served from "FEO" tag
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |


  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and product thumbnail has title, colors, pricelable and quickview
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    When I select a product having color swatches
    Then I verify that the product title appears
    And I verify that the product image appears
    And I verify that the product price appears
    And I verify that the QuickView label appears on hovering the thumbnail
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and product thumbnails are available and clickable
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that page is served from "FEO" tag
    Then I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and getting ensure top icon working as expected
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and verify filter products when we select any one random facet value
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    Then I verify that page is served from "FEO" tag
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and verify pagination using Next / Previous button in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I verify that pagination is displayed
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    When I click on previous pagination button
    Then I verify that navigated to page 1 on search result page
    And I verify that page is served from "FEO" tag
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and verify Sort By functionality
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I verify that the Sort By displayed with all options
    When I select "Price: High to Low" in sort by drop down
    Then I verify that the Sort By "Price: High to Low" functionality
    Then I select browse 'back' button
    And I verify that page is served from "FEO" tag
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      |Electronics|
      #|sandals    |
      #|lipsticks  |

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and verify Top Banner
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    Then I verify top banner is displayed on "search result" browse page
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |


  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Search
  Scenario Outline: Verify the page is served from Feo optimization and verify Quick View overlay
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    Then I verify that page is served from "FEO" tag
    And I select "quick view" button for "member" product on page
    And I verify that quick peek is displayed
    Examples:
      |Search_cat|
      #|Mugs|
      #|Jeans  |
      #|Knife  |
      #|Electronics|
      #|sandals    |
      |lipsticks  |

    #---------------------------------------------Validation of Optimized sub pages-----------------------------------------------------------------------------
  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify quick view and quick bag validation on sub Optimized pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    And I verify that pagination is displayed
    And I click 2 pagination number
    Then I verify that navigated to page 2 on browse page
    When I filter the result set to show "120" items per page
    Then I verify that navigated to page 1 on search result page
    And I verify that page is served from "FEO" tag
    Then  I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    Examples:
      |subcategory_name||category_name|
      |Boots||SHOES    |
     # |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |25-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|

  @domain_Other @artifact_navapp @project_siteperformance @feature_Feo_Browse_secondPhase
  Scenario Outline: Verify quick view and quick bag on Optimized  sub pages
    Given I am on Browse Page for "<subcategory_name>" under "<category_name>"
    Then I verify that page is served from "FEO" tag
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I refresh current page
    Then I verify that page is served from "FEO" tag
    Then  I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    Examples:
      |subcategory_name||category_name|
     # |Boots||SHOES                   |
      |Fossil||HANDBAGS|
     # |Coats ||WOMEN   |
     # |Sweaters||WOMEN |
     # |50-75% Off Clearance||SHOES|
     # |MICHAEL Michael Kors||HANDBAGS|
     # |Kitchen||HOME|
     # |Coats & Jackets||MEN|
     # |Tops||WOMEN|
     # |Booties||SHOES|
     # |Sweaters||MEN|
     # |25-70% Off Clearance||HANDBAGS|
     # |Pajamas, Robes & Loungewear||WOMEN|
     # |Activewear||WOMEN|
     # |All Fashion Jewelry||JEWELRY|
     # |Big Girls (7-16)||KIDS|
     # |Bed in a Bag||BED & BATH|
     # |Designer Handbags||HANDBAGS|
     # |Boots||MEN|