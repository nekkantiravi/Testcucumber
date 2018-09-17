# Author: SEO Improvements 2016
# Date Created: 10/06/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:UI:: Redirect to old search url pattern for some speacial characters
# Versionone story numbers:: B-61756
#########################################################################################################################

Feature: As an SEO Business Manager, I would like to see search re-direct to old search pattern for some special characters

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16S @priority_high @use_regression
  Scenario Outline: Verify that search keywords with some special characters should re-direct to old url
    Given I visit the web site as a guest user
    When I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | green+coats              | /shop/search?keyword=green%2Bcoats            |
      | Jackets / Petites        | /shop/search?keyword=jackets+%2F+petites      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | guess?                   | /shop/featured/guess%3F                       |
      | Jackets/Petites          | /shop/search?keyword=jackets%2Fpetites        |
      | Jackets \ Petites        | /shop/featured/jackets-%5C-petites            |
      #| Jackets\Petites          | /shop/featured/jackets%5Cpetites             |


  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16S @priority_high @use_regression
  Scenario Outline: Verify that search keywords with some special characters should re-direct to old url in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | green+coats              | /shop/search?keyword=green%2Bcoats            |
      | Jackets / Petites        | /shop/search?keyword=jackets+%2F+petites      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | guess?                   | /shop/featured/guess%3F                       |
#      | Jackets/Petites          | /shop/search?keyword=jackets%2Fpetites        |
      | Jackets \ Petites        | /shop/featured/jackets-%5C-petites            |
      #| Jackets\Petites          | /shop/featured/jackets%5Cpetites             |

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16S @priority_high @use_regression
  Scenario Outline: Verify that search keywords are retained in the search box as per SEO standards when user paste the old urls
    Given I visit the web site as a guest user
    When I navigate to old search "<url>"
    #Then I should see search keywords encoded as "<URL>" in search base url
    Then I should have search keyword as "<keyword>"
    Examples:

      | url                                                 | keyword                  |
      | /shop/search?keyword=green%2Bcoats                  | green+coats              |
      | /shop/search?keyword=jackets+%2F+petites            | jackets / petites        |
      | /shop/search?keyword=jackets+%5C+petites            | jackets \ petites        |
      | /shop/search?keyword=tops+-+maternity               | tops - maternity         |
      | /shop/search?keyword=red+++--++++dress              | red   --    dress        |
      | /shop/search?keyword=guess%3F                       | guess?                   |

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16S @priority_high @use_regression
  Scenario Outline: Verify that search keywords are retained in the search box as per SEO standards when user paste the old urls in Iship mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to old search "<url>"
    #Then I should see search keywords encoded as "<URL>" in search base url
    Then I should have search keyword as "<keyword>"
    Examples:

      | url                                                 | keyword                  |
      | /shop/search?keyword=green%2Bcoats                  | green+coats              |
      | /shop/search?keyword=jackets+%2F+petites            | jackets / petites        |
      | /shop/search?keyword=jackets+%5C+petites            | jackets \ petites        |
      | /shop/search?keyword=tops+-+maternity               | tops - maternity         |
      | /shop/search?keyword=red+++--++++dress              | red   --    dress        |
      | /shop/search?keyword=guess%3F                       | guess?                   |


######################Shop App Pages##################################

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16S @use_regression
  Scenario Outline: Verify that search keywords with some special characters should re-direct to old url from sign in page
    Given I visit the web site as a guest user
    When I navigate to sign in page
    And I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | green+coats              | /shop/search?keyword=green%2Bcoats            |
      | Jackets / Petites        | /shop/search?keyword=jackets+%2F+petites      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | guess?                   | /shop/featured/guess%3F                       |
      | Jackets/Petites          | /shop/search?keyword=jackets%2Fpetites        |
      | Jackets \ Petites        | /shop/featured/jackets-%5C-petites            |
      | Jackets\Petites          | /shop/featured/jackets%5Cpetites              |

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16S @use_regression
  Scenario Outline: Verify that search keywords with some special characters should re-direct to old url from My Account page in Site mode
    Given I visit the web site as a signed in user
    And I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | green+coats              | /shop/search?keyword=green%2Bcoats            |
      | Jackets / Petites        | /shop/search?keyword=jackets+%2F+petites      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | guess?                   | /shop/featured/guess%3F                       |
      | Jackets/Petites          | /shop/search?keyword=jackets%2Fpetites        |
      | Jackets \ Petites        | /shop/featured/jackets-%5C-petites            |

  @domain_marketing @artifact_navapp @project_SEO_improvements @priority_high @release_16S @use_regression
  Scenario Outline: Verify that search keywords with some special characters should re-direct to old url from My Wallet page in Site mode
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | green+coats              | /shop/search?keyword=green%2Bcoats            |
      | Jackets / Petites        | /shop/search?keyword=jackets+%2F+petites      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | guess?                   | /shop/featured/guess%3F                       |
      | Jackets/Petites          | /shop/search?keyword=jackets%2Fpetites        |
      | Jackets \ Petites        | /shop/featured/jackets-%5C-petites            |







