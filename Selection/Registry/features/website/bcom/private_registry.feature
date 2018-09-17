#BCOM Registry Lean Lab
#Author: Mirna Silva
#V-1 Story: B-95718

Feature: Verify registry privacy setting scenarios

  @B-95718 @registry @use_regression @domain_selection
  Scenario: Verify "Make Registry Public" checkbox on Event Detail Card in Create Registry form is checked by default
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to registry create page from registry home page
    And I fill in Create Form up to Event Details page
    Then I verify that the "Make Registry Public" checkbox is checked
    And I click on tooltip icon
    And I should see the tooltip overlay

  @B-95718 @registry @use_regression @domain_selection
  Scenario Outline: Verify user can create private Registry using "Make Registry Public" checkbox on Event Detail Card in Create Registry form
    Given I visit the web site as a <type> user
    And I navigate to registry home page
    And I navigate to Event Details card of Create Registry form as a <type> user
    Then I uncheck the "Make Registry Public" checkbox on Event Detail Card
    And I continue to fill in create form until I reach Registry Manager page
    Then I verify that "Make Registry Public" banner is displayed on the BVR page with the following text:
      """
      Your registry is currently set to Private and can't be viewed by your guests. Update to Public View to allow guests to shop your registry.
      """
     And I verify that registry is private by making a REST call
    Examples:
      | type        |
      | guest       |
      | registered  |

  @B-95718 @registry @use_regression @domain_selection
  Scenario: Verify that on "Make Registry Public" banner, Make it Public link takes user to Edit Profile
    Given I visit the website as a bvr user with private registry using rest services
    And I navigate to bvr page
    When I click on "Make It Public" link on "Make Registry Public" banner
    Then I should land on Edit Registry Profile page
    And I verify that the "Make Registry Public" checkbox is not checked

  @wip
  Scenario: Verify registry user with private registry can update settings to public from Edit Registry Profile page
    Given I visit the website as a bvr user with private registry using rest services
    When I navigate to Edit Registry Page
    Then I verify that the "Make Registry Public" checkbox is not checked
    When I check the "Make Registry Public" checkbox on Event Detail Card
    And I click on "Save Changes" on the Edit Registry Profile
    Then I verify that registry is public by making a REST call

  @wip
  Scenario: Verify registry user with public registry can update settings to public from Edit Registry Profile page
    Given I visit the website as a bvr user with public registry using rest services
    When I navigate to Edit Registry Page
    Then I verify that the "Make Registry Public" checkbox is checked
    When I uncheck the "Make Registry Public" checkbox on Event Detail Card
    And I click on "Save Changes" on the Edit Registry Profile
    Then I verify that registry is private by making a REST call

  @wip
  Scenario: Verify that guest user cannot find private registry using either Find Registry form or direct url
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to find registry page
    When I search for the existing couple's registry
    Then I click view registry in GVR page

    When I search for private registry using Find Registry Form
    Then I verify that registry is not found
    And I navigate to gvr url directly
    And I verify that page is 404