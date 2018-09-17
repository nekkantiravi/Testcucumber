#######################################################################################
# B-91626 :: BUS :: MCOM :: UI :: Create login interface for Registry Sign-In Flow
# B-96572 :: UI:MCOM:Signed In user should be redirected to specific target registry page based on registry request URL
# Author :: QE Team
#######################################################################################


Feature:Verify the registry login interface flow

 #Pre and Post login functionality through registry links.
  Background:
    Given I set the unifiedLoginEnabled header

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through create registry link from header on registry home page as a registered user.
    Given I visit the web site as a registered user
    And I sign out from my current profile
    And I navigate to registry home page
    When I select "create_reg_header" from "reg_home_header"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "new_create_registry" page
    And I should verify "post_signin" url for "create_registry" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through create registry link from header on registry home page as a registry user.
   Given I visit the web site as a registry user
   And I sign out from my current profile
   And I navigate to registry home page
   When I select "create_reg_header" from "reg_home_header"
   Then I should be navigated to the registry sign in page
   And I should verify "pre_signin" url for "registry_sign_in" link
   When I sign in with existing credentials from "new_registry_sign_in" page
   Then I should be redirected to "registry_manager" page
   And I should verify "post_signin" url for "registry_manager" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through create registry link from wedding registry dropdown on registry home page.
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    When I select "create_reg_from_wed_reg_dropdown" from "wedding_reg_dropdown"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through create registry box on registry home page.
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "goto_create_registry" from "page_area"
    Then I should be redirected to "new_create_registry" page
    When I create a new registry
    Then I should be redirected to "registry_welcome" page

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through manage registry box on registry home page
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    And I select "manage_registry" from "page_area"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "manage_registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through manage registry link from manage dropdown on registry home.
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    And I select "manage_reg_from_dropdown" from "manage_dropdown"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "manage_registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through manage registry link from wedding registry dropdown on registry home page.
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    And I select "manage_reg_from_wed_reg_header" from "manage_header"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "manage_registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through view registry link from manage dropdown on registry home.
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    And I select "view_registry" from "manage_dropdown"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "view_registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "view_registry" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through The Registry guide page
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    When I select "the_registry_guide" from "wedding_reg_dropdown"
    Then I should be redirected to "registry_guide" page
    When I click on "create_a_registry" in "registry_guide" page
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through Dream Fund page
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    When I select "dream_fund" from "wedding_reg_dropdown"
    Then I should be redirected to "registry_dream_fund" page
    When I click on "access_your_registry" in "registry_dream_fund" page
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "manage_registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify sign-in functionality through benefits page
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    When I select "registry_benefits" from "wedding_reg_dropdown"
    Then I should be redirected to "registry_benefits" page
    When I click on "<input_link>" in "registry_dream_fund" page
    Then I should be redirected to "<target_sign_in>" page
    When I sign in with existing credentials from "<target_sign_in>" page
    Then I should be redirected to "<post_sign_in>" page

    Examples:
      | input_link           | target_sign_in       | post_sign_in     |
      | create_a_registry    | new_create_registry  | registry_manager |
      | access_your_registry | new_registry_sign_in | registry_manager |

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through wedding registry star reward programs page
  by clicking on create registry button.

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through wedding registry star reward programs page
  by clicking on enroll button.

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through wedding registry star reward programs page
  by clicking View Registry in header section.

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through wedding registry star reward programs page
  by clicking on Registry Checklist in header section.

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through wedding registry star reward programs page
  by clicking on Registry Mananger in header section.

 ## Negative scenario.
  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the error message if user try to create a new registry with an existing account
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "Create Your Registry"
    Then I should be redirected to "new_create_registry" page
    When I enter user detail for existing account and click continue
    Then I should see "This email is associated with a macys.com account. " validation message on "new_create_registry" page
    When I click on "error_sign_in" link on "new_create_registry" page
    Then I should be redirected to "new_registry_sign_in" page

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the error message if user try to login throuhg manage registry with an existing account
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I select "manage_registry" from "page_area"
    Then I should be navigated to the registry sign in page
    When I enter existing credential and hit sign in button
    Then I should see "<error>" validation message on "new_registry_sign_in" page

    Examples:
      | error                                                                                                                                          |
      | There is no registry associated with this profile. Please create a registry or link your in-store created registry from the Registry Homepage. |

  ## Registry sign in page UI validation.

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify UI elements on new registry sign in page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "create_reg_header" from "reg_home_header"
    Then I should verify below fields on "registry_sign_in" page
      | existing_user_email            |
      | existing_user_password         |
      | reg_sign_in_button             |
      | pwd_show_button                |
      | forgot_password_link           |
      | create_new_registry_button     |
      | access_instore_registry_button |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the functionality of links on registry sign in page.
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "create_reg_header" from "reg_home_header"
    And I click on "<input>" in "registry_sign_in" page
    Then I should be redirected to "<target>" page
    Examples:
      | input                          | target                 |
      | create_new_registry_button     | new_create_registry    |
      | forgot_password_link           | forgot_password        |
      | access_instore_registry_button | registry_claim         |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the show or hide button in password field on registry sign in page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "create_reg_header" from "reg_home_header"
    And I should see typed password using show button in "registry_sign_in" page
    Then I should see hide button on "registry_sign_in" page

  ## Registry sign in page error validation

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the validation message when invalid credentials is entered
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "create_reg_header" from "reg_home_header"
    And I enter invalid "<email>" and "<password>"
    Then I should see "<expected error>" validation message on "new_registry_sign_in" page

    Examples:
      | email              | password  | expected error                                                                                                |
      | testmail           | test12345 | Your email address must be entered in this format: jane@company.com                                           |
      | testmail@gmail.com | test      | Your password must be between 7-16 characters, and cannot include . , - \\ / = _ or spaces. Please try again. |
      | testmail@gmail.com | test12345 | That email address/password combination is not in our records. Forgot Your Password? Reset it now             |
      |                    | test12345 | Please enter your email address                                                                               |
      | jane@bcom.com      |           | Please enter your password                                                                                    |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message if authweb otp expires
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    And I wait enough to get otp expire
    And I try to login to my account
    Then I should verify the below error message
      | Your session has expired. Please refresh the page and sign in again. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message on sign in page due to technical issues
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    And I try to sign in to my account
    Then I should see the error messaes as below
      | Hmm...looks like we're having some technical issues. Please contact Customer Service at 1-800-BUY-MACY. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message after user account locks down
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    And I try to login with invalid credential for more then allowed attempts
    Then I should see "<expected error>" validation message on "sign_in" page
      | We're sorry. Your account has been suspended. For more details on why your account was suspended, please contact Customer Service at 1-800-BUY-MACY(1-800-289-6229) |

  ######################### URL redirection flow ##############################################
  # 1) user should be signed in i.e signedIn cookie value = 1
  # 2) cookie secure_user_toke is present
  # 3) SMISCGS cookie is present with key value regld

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify registry url is navigating to appropriate url.
    Given I visit the web site as a registered user
    When I navigate to registry home page
    And I select "Create Your Registry"
    And I create a new registry for user with existing bcom profile
    Then I should see the registry created successfully
    When I navigate to the url "<request_page>" as signed-in user
    Then I should be navigated to "<target_page>" url

    Examples:
      | request_page                   | target_page                      |
      | registry/wedding/regsignin     | registry/wedding/registrymanager |
      | wgl/registry/registrant/signin | wgl/registry/registrant          |
      | wgl/registry/signin            | registry/wedding/registrymanager |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through add to registry button on PDP page for the product that is registrable.
    Given I visit the web site as a registry user
    And I sign out from my current profile
    When I navigate to "registrable and orderable" product PDP page
    And I select "add_to_registry" on standard PDP
    Then I should be navigated to the registry sign in page
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "view_registry" link