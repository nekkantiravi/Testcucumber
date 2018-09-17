Feature: Verify Polaris CSG components

  @Stability_Performance @artifact_polaris_CSG_Component @feature_Modal_overlay
    Scenario Outline: verify CSG component modal_overlay in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Modal Overlay" component
    And I should be able to scroll the page
    When I click on "Open Modal Overlay" button
    Then I should see component "Modal overlay" displayed
    And I should not able to scroll the page
    Then I should not see "Modal Overlay" once I click on close button
    And I should be able to scroll the page
    Examples:
    |device|
    |desktop    |
    |mobile     |
    |tablet     |

  @Stability_Performance @artifact_polaris_CSG_Component @feature_Accordion
  Scenario Outline: verify CSG component Accordion in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Accordion" component
    And I should see one row is "collapsed"
    And I should see one row is "expanded"
    When I click on accordion "plus" button
    Then I "should" see body text present
    When I click on accordion "minus" button
    Then I "should't" see body text present
    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |

  @Stability_Performance @artifact_polaris_CSG_Component @feature_Tabs
  Scenario Outline: verify CSG component Tabs in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Tabs" component
    And I validate "Tab1" selected and body text "hello1" is displaying correctly
    And I validate "Tab2" selected and body text "hello2" is displaying correctly
    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |


  @Stability_Performance @artifact_polaris_CSG_Component @feature_Drawer
  Scenario Outline: verify CSG component Drawer in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Drawer" component
    And I should be able to scroll the page
    When I click on "Show Drawer" button
    Then I should see component "Drawer" displayed
    And I should not able to scroll the page
    Then I should not see "Drawer" once I click on close button
    And I should be able to scroll the page
    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |

  @Stability_Performance @artifact_polaris_CSG_Component @feature_loading_indicator
  Scenario Outline: verify CSG component loading indicator in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "loading indicator" component
    When I click on "ADD LOADER" button
    Then I should see component "Loading Indicator" displayed
    Then I should not see Loading Indicator once I click on "REMOVE LOADER" button
    When I click on "ADD LOADER" button
    Then I should see component "Loading Indicator" displayed
    Then I should not see Loading Indicator once I click on "HIDE LOADER" button
    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |

  @Stability_Performance @artifact_polaris_CSG_Component @feature_form_validation
  Scenario Outline: verify CSG component form validation with empty input in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Form Validation" component
    And I click into "Username" field and focus should be in the field
    And I click into "Email Address" field and focus should be in the field
    Then I "should" see "email" validation message "Please enter a valid email address." displaying
    And I "should" see a "error" icon displayed in the field
    And I click into "Password" field and focus should be in the field
    Then I "should" see "password" validation message "Please enter a valid password." displaying
    And I "should" see a "error" icon displayed in the field
    And I click into "Confirm Password" field and focus should be in the field
    And I click on "Cancel" button
    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |

  @Stability_Performance @artifact_polaris_CSG_Component @feature_form_validation
  Scenario Outline: verify CSG component form validation with special character input in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Form Validation" component
    And I enter special character in "Username" form text field
    And I enter special character in "email" form text field
    Then I "should" see "email" validation message "Please enter a valid email address." displaying
    And I "should" see a "error" icon displayed in the field
    And I enter special character in "password" form text field
    Then I "should" see "password" validation message "Please enter a valid password." displaying
    And I "should" see a "error" icon displayed in the field
    And I enter special character in "Confirm Password" form text field
    When I click on "Cancel" button
    Then I "should not" see "email" validation message "Please enter a valid email address." displaying
    Then I "should not" see "password" validation message "Please enter a valid password." displaying
    And I "should not" see a "error" icon displayed in the field

    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |

  @Stability_Performance @artifact_polaris_CSG_Component @feature_form_validation
  Scenario Outline: verify CSG component form validation with valid input in Polaris app page
    Given I visit the polaris app page
    And I resize the window to "<device>" type
    Then I should see "Form Validation" component
    And I enter valid input in "Username" form text field
    And I enter valid input in "email" form text field
    Then I "should not" see "email" validation message "Please enter a valid email address." displaying
    And I "should" see a "validation" icon displayed in the field
    And I enter valid input in "password" form text field
    Then I "should not" see "password" validation message "Please enter a valid password." displaying
    And I "should" see a "validation" icon displayed in the field
    And I enter valid input in "Confirm Password" form text field
    When I click on "Cancel" button
    Then I "should not" see a "validation" icon displayed in the field
    Examples:
      |device|
      |desktop    |
      |mobile     |
      |tablet     |

