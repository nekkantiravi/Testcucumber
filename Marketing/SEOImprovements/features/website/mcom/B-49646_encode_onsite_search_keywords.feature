# Author: SEO Improvements 2016
# Date Created: 7/14/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::NavApp:Tech: Encode on-site search keyword according to SEO standards
# Versionone story numbers:: B-49646
# Pre-requisite: seoImprovements2016Enabled kill switch set to TRUE
#########################################################################################################################

Feature:  As a NavApp Developer, I want to modify on-site search keyword according to SEO standards(SEO business will provide the list of characters and their replacements)

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario Outline: : Verify that search keywords should be encoded as per SEO standards in site mode
    Given I visit the web site as a guest user
    When I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | high sierra              | /shop/featured/high-sierra                    |
      | red dress                | /shop/featured/red-dress                      |
      | Red Dress                | /shop/featured/red-dress                      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | Macy's Men's             | /shop/featured/macy%27s-men%27s               |
      | 25% off on jeans         | /shop/featured/25%25-off-on-jeans             |
      | Style & Co. Boots        | /shop/featured/style-%26-co.-boots            |
      | Tights, Socks, & Hosiery | /shop/featured/tights%2C-socks%2C-%26-hosiery |
      | Red*Dress                | /shop/featured/red*dress                      |
      | Red&Dress                | /shop/featured/red%26dress                    |
      | Red%Dress                | /shop/featured/red%25dress                    |
      | Red^Dress                | /shop/featured/red%5Edress                    |
      | Red$Dress                | /shop/featured/red%24dress                    |
      | Red#Dress                | /shop/featured/red%23dress                    |
      | Red@Dress                | /shop/featured/red%40dress                    |
      | Red!Dress                | /shop/featured/red%21dress                    |
      | Red--Dress               | /shop/featured/red~~~~dress                   |
      | blue         jeans       | /shop/featured/blue-jeans                     |
      | JEANS                    | /shop/featured/jeans                          |
      | {red dress}              | /shop/featured/%7Bred-dress%7D                |
      | [red dress]              | /shop/featured/%5Bred-dress%5D                |
      | (red dress)              | /shop/featured/%28red-dress%29                |
      | red~dress                | /shop/featured/red~dress                      |


  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario Outline: : Verify that search keywords should be encoded as per SEO standards in international mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "<keyword>"
    Then I should see search keywords encoded as "<URL>" in search base url
    Examples:
      | keyword                  | URL                                           |
      | high sierra              | /shop/featured/high-sierra                    |
      | red dress                | /shop/featured/red-dress                      |
      | Red Dress                | /shop/featured/red-dress                      |
      | tops - maternity         | /shop/featured/tops-~~-maternity              |
      | Red   --    Dress        | /shop/featured/red-~~~~-dress                 |
      | Macy's Men's             | /shop/featured/macy%27s-men%27s               |
      | 25% off on jeans         | /shop/featured/25%25-off-on-jeans             |
      | Style & Co. Boots        | /shop/featured/style-%26-co.-boots            |
      | Tights, Socks, & Hosiery | /shop/featured/tights%2C-socks%2C-%26-hosiery |
      | Red*Dress                | /shop/featured/red*dress                      |
      | Red&Dress                | /shop/featured/red%26dress                    |
      | Red%Dress                | /shop/featured/red%25dress                    |
      | Red^Dress                | /shop/featured/red%5Edress                    |
      | Red$Dress                | /shop/featured/red%24dress                    |
      | Red#Dress                | /shop/featured/red%23dress                    |
      | Red@Dress                | /shop/featured/red%40dress                    |
      | Red!Dress                | /shop/featured/red%21dress                    |
      | Red--Dress               | /shop/featured/red~~~~dress                   |
      | blue         jeans       | /shop/featured/blue-jeans                     |
      | JEANS                    | /shop/featured/jeans                          |
      | {red dress}              | /shop/featured/%7Bred-dress%7D                |
      | [red dress]              | /shop/featured/%5Bred-dress%5D                |
      | (red dress)              | /shop/featured/%28red-dress%29                |
      | red~dress                | /shop/featured/red~dress                      |

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario Outline: : Verify that search keywords should be encoded as per SEO standards when user paste the old urls
    Given I visit the web site as a guest user
    When I navigate to old search "<url>"
    # Bookmark url case
    Then I should have search keyword as "<keyword>"
    And I should see search keywords encoded as "<url>" in search base url

    Examples:

      | url                                                 | keyword                  |
      | /shop/search?keyword=high+sierra                    | high sierra              |
      | /shop/search?keyword=red++dress                     | red  dress               |
      | /shop/search?keyword=red+dress                      | red dress                |
      | /shop/search?keyword=tops+-+maternity               | tops - maternity         |
      | /shop/search?keyword=red+++--++++dress              | red   --    dress        |
      | /shop/search?keyword=macy%27s+men%27s               | macy's men's             |
     #| /shop/search?keyword=25%25+off+on+jeans             | 25% off on jeans         |
      | /shop/search?keyword=style+%26+co.+boots            | style & co. boots        |
      | /shop/search?keyword=tights%2C+socks%2C+%26+hosiery | tights, socks, & hosiery |
      | /shop/search?keyword=red*dress                      | red*dress                |
      | /shop/search?keyword=red%26dress                    | red&dress                |
     #| /shop/search?keyword=red%25dress                    | red%dress                |
      | /shop/search?keyword=red%5Edress                    | red^dress                |
      | /shop/search?keyword=red%24dress                    | red$dress                |
      | /shop/search?keyword=red%23dress                    | red#dress                |
      | /shop/search?keyword=red%40dress                    | red@dress                |
      | /shop/search?keyword=red%21dress                    | red!dress                |
      | /shop/search?keyword=red--dress                     | red--dress               |
      | /shop/search?keyword=jeans                          | jeans                    |
      | /shop/search?keyword=blue+++++++++jeans             | blue         jeans       |
      | /shop/search?keyword=tops-maternity                 | tops-maternity           |
      | /shop/search?keyword=jeans                          | jeans                    |

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario Outline: : Verify that search keywords should be encoded as per SEO standards
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to old search "<url>"
    # Bookmark url case
    Then I should have search keyword as "<keyword>"
    And I should see search keywords encoded as "<url>" in search base url
    Examples:

      | url                                                 | keyword                  |
      | /shop/search?keyword=high+sierra                    | high sierra              |
      | /shop/search?keyword=red++dress                     | red  dress               |
      | /shop/search?keyword=red+dress                      | red dress                |
      | /shop/search?keyword=tops+-+maternity               | tops - maternity         |
      | /shop/search?keyword=red+++--++++dress              | red   --    dress        |
      | /shop/search?keyword=macy%27s+men%27s               | macy's men's             |
     #| /shop/search?keyword=25%25+off+on+jeans             | 25% off on jeans         |
      | /shop/search?keyword=style+%26+co.+boots            | style & co. boots        |
      | /shop/search?keyword=tights%2C+socks%2C+%26+hosiery | tights, socks, & hosiery |
      | /shop/search?keyword=red*dress                      | red*dress                |
      | /shop/search?keyword=red%26dress                    | red&dress                |
     #| /shop/search?keyword=red%25dress                    | red%dress                |
      | /shop/search?keyword=red%5Edress                    | red^dress                |
      | /shop/search?keyword=red%24dress                    | red$dress                |
      | /shop/search?keyword=red%23dress                    | red#dress                |
      | /shop/search?keyword=red%40dress                    | red@dress                |
      | /shop/search?keyword=red%21dress                    | red!dress                |
      | /shop/search?keyword=red--dress                     | red--dress               |
      | /shop/search?keyword=jeans                          | jeans                    |
      | /shop/search?keyword=blue+++++++++jeans             | blue         jeans       |
      | /shop/search?keyword=tops-maternity                 | tops-maternity           |
      | /shop/search?keyword=jeans                          | jeans                    |