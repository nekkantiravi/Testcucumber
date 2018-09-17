Feature: Verifying ForwardPage key cookie in Search & browse pages

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I am on SearchResultsPage for "<search>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "<facet>" facet section
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |search|facet|
      | Domestic      |dress |size |
      | Registry      |Electrics|Special Offers|

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_search_results_page
  Scenario Outline: SearchResultsPage - Iship - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I am on SearchResultsPage for "pink boot" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Size" facet section
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select 'customer service' link from header
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Iship         |

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I am on SearchResultsPage for "<search>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "<facet>" facet section
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    And I navigate to random category splash page
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |search|facet|
      | Domestic      |dress |size |
      | Registry      |Electrics|Special Offers|
      |iship          |dress |size |

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_Browse_page
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    And I am on Browse Page for "<subcategory_name>" under "<category_name>"
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "<facet>" facet section
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    And I navigate to random category splash page
    And I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      |shopping_mode|subcategory_name|category_name|facet|
      |Domestic|Boots|SHOES|size      |
      |Registry|Electrics|KITCHEN|Special Offers|
      |iship|Coats |WOMEN   |size |

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_MyAccount_page @WIP
  Scenario Outline:Verify the user is redirected to the page as per forwardpage_key cookie after login from header
    Given I visit the web site as a guest user
    When I click on "<input>" link in "home" page
    Then I should be on signin page
    And I verify the forwardpage key cookie is generated
    When I sign in with valid credentials
    Then I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie

    Examples:
      | input                |
      | goto_my_account_link |
      #| goto_sign_in_link    |

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_MyAccount_page
  Scenario Outline: Verify the user is redirected to corresponding page after signing in through My Account dropdown wallet and Plenti
    Given I visit the web site as a guest user
    When I select "<option>" link from my account dropdown
    Then I should be on signin page
    And I verify the forwardpage key cookie is generated
    When I sign in with valid credentials
    Then I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie

    Examples:
      | option|
      | goto_mywallet   |
      | goto_myplenti   |


  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_MyAccount_page
  Scenario Outline: Verify the user is redirected to corresponding page after signing in through My Account dropdown
    Given I visit the web site as a guest user
    When I select "<option>" link from my account dropdown
    Then I should be redirected to "<target>" page
    When I click on "<Sign-in_link>" link in "<target>" page
    Then I should be on signin page
    And I verify the forwardpage key cookie is generated
    When I sign in with valid credentials
    Then I should redirect to the "<target>" page corresponding to the value stored in FORWARDPAGE_KEY cookie

    Examples:
      | option                  | Sign-in_link                 | target                      |
      | goto_order_history      | goto_order_history_sign_in   | order_status                |
      | goto_macys_credit_card  | sign_in_button               | credit_service_gateway_guest|
      | goto_mylist             | goto_signin_link             | wish_list                   |

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_MyAccount_page
  Scenario: Verify the user navigates to my account page for registered user
    Given I visit the web site as a guest user
    Then I verify the forwardpage key cookie is generated
    When I visit the web site as a "registered" user in "site" mode
    Then I should be navigated back to my account page

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_MyAccount_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on selecting facet refinements
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select 1 facet value(s) from 'any' facet sections
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |

  @domain_Stability_Performance @use_regression @feature_ForwardPage_key_cookie_in_MyAccount_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the "FORWARDPAGE_KEY" page cookie on deselecting facet refinements
    Given I am on SearchResultsPage for "pants" in <shopping_mode> mode
    When I select 4 facet value(s) from 'any' facet sections
    And I deselect 2 facet value(s)
    Then I verify that selected facet value in FORWARDPAGE_KEY cookie
    When I select Sign In link from header and sign in from the current page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that selected facet value in FORWARDPAGE_KEY cookie
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |