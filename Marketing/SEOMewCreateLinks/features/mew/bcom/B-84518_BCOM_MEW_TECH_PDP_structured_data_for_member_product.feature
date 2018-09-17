# Author: SEO Improvements 2017
# Date Created: 7/20/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM: MEW: TECH: PDP structured data for member product
# Versionone Story#B-84518
#########################################################################################################################

Feature: As an SEO Architect, I want MEW PDP Structure Data for all the product attributes that are visible to Googlebot User Agent.

  ############################################### Member PDP pages -Mew##################################################################
  #Pre-requisite:- KS(SEO_PDP_STRUCTURED_DATA)= ON
  @domain_marketing @artifact_MeW2.0 @project_SEO_Link_Module_MeW @release_17P @priority_high
  Scenario: Verify the PDP structured data for member product navigated through the category browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women   |
      | Dresses |
    And I select a random product
    And I should see container for the structured data should be inside of the <head> element in DOM
      | category    |
      | productID   |
      | offers      |
      | image       |
      | @type       |
      | name        |
      | description |
      | @context    |
      | brand       |
      | url         |
      |aggregateRating|
