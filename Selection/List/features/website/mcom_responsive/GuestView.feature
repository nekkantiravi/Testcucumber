#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Aug.11,2017
#---------------------------------------------------

Feature: Responsive List Page for Guest View

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, I should see one product in my list on the responsive list page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I navigate directly to responsive list page for "Guest" user
    And I verify the basic components on the page for "Guest" user
      # Guest List title
      # Not signed in yet? message
      # Sign in button
      # Create account button
      # Sort by
      # Filter by
      # List/Grid views
      # Print screen button
      # See all Lists? Landing page link
      # Possible PROS panel
    And I get the response from xapi
    And I should see "1" products in the responsive list page

  @responsive_list @domain_selection @project_mcom
  Scenario: As a guest user, I should see empty guest view on the responsive list page
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I navigate directly to responsive list page with listguid
    And I verify the basic components on the page for "Guest" user
      # Guest List title
      # Not signed in yet? message
      # Sign in button
      # Create account button
      # Sort by
      # Filter by
      # List/Grid views
      # Print screen button
      # See all Lists? Landing page link
      # Possible PROS panel
    And I should see "0" products in the responsive list page

  @responsive_list @domain_selection @project_mcom @wip
  Scenario: As a guest user, I should see one promotion product
    Given I visit the web site as a guest user
    And I set cookie for SSC to see responsive experience
    When I navigate directly to member PDP and add a product to my list with a promotion
    When I navigate directly to the responsive list page
    And I verify basic components of responsive list page as a guest user
      # Guest List title
      # Not signed in yet? message
      # Sign in button
      # Create account button
      # Sort by
      # Filter by
      # List/Grid views
      # Print screen button
      # See all Lists? Landing page link
      # Possible PROS panel
    And I should see products in the responsive list page
    And I should see promo badge product in the responsive list page

  @responsive_list @domain_selection @project_mcom @wip
  Scenario: As a guest user, I should see one PID level product
    Given I visit the web site as a guest user
    When I add PID level product to my list through services
    When I navigate directly to the responsive list page
    And I verify basic components of responsive list page as a guest user
      # Guest List title
      # Not signed in yet? message
      # Sign in button
      # Create account button
      # Sort by
      # Filter by
      # List/Grid views
      # Print screen button
      # See all Lists? Landing page link
      # Possible PROS panel
    And I should see products in the responsive list page
    And I should see PID level product in the responsive list page

  @responsive_list @domain_selection @project_mcom @wip
  Scenario: As a guest user, I should see price drop product
    Given I visit the web site as a guest user
    When I navigate directly to member PDP and add a product to my list
    When I navigate directly to the responsive list page
    And I verify basic components of responsive list page as a guest user
      # Guest List title
      # Not signed in yet? message
      # Sign in button
      # Create account button
      # Sort by
      # Filter by
      # List/Grid views
      # Print screen button
      # See all Lists? Landing page link
      # Possible PROS panel
    And I should see products in the responsive list page
    And I should see product in the responsive list page with price drop