#######################################################################################
# B-91689 :: BUS :: BCOM :: UI :: Create login interface for Registry Sign-In Flow
# B-96502 :: BUS/QE :: BCOM :: UI :: Create login interface for Registry Flows
# B-96571 :: UI:BCOM:Signed In user should be redirected to specific target registry page
# based on registry request URL
# Author :: QE Team
########################################################################################

Feature:Verifying the registry login interface flow

 #Pre and Post login functionality through registry links.
#  Background:
#    Given I set the unifiedLoginEnabled header

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through create registry option on registry homepage
    Given I visit the web site as a registered user
    And I sign out from my current profile
    And I navigate to registry home page
    When I select "Create Your Registry"
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "create_registry_button" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "new_create_registry" page
    And I should verify "post_signin" url for "new_create_registry" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through create registry under getting started header
    Given I visit the web site as a registered user
    And I sign out from my current profile
    And I navigate to registry home page
    When I hover on getting started link
    And I select "create_registry_through_getting_started" in dropdown
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "create_registry_through_getting_started_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "new_create_registry" page
    And I should verify "post_signin" url for "new_create_registry" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through registry manager under getting started header
    Given I visit the web site as a registered user
    And I get the registry user detail
    And I sign out from my current profile
    And I navigate to registry home page
    When I hover on getting started link
    And I select "manage_registry" in dropdown
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "registry_manager" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through view my items under getting started header
    Given I visit the web site as a registered user
    And I get the registry user detail
    And I sign out from my current profile
    And I navigate to registry home page
    When I hover on getting started link
    And I select "view_my_items" in dropdown
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "view_registry" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign-in functionality through add to registry button on PDP page for the product that is registrable.
    Given I visit the web site as a registered user
    And I get the registry user detail
    And I sign out from my current profile
    When I navigate to "registrable and orderable" product PDP page
    And I select "add_to_registry" on standard PDP
    Then I should be navigated to the registry sign in page
    And I should verify "pre_signin" url for "add_to_registry_sign_in" link
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "registry_manager" link

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify sign-in functionality through benefits and perks page under getting started header
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I hover on getting started link
    And I select "benefits_perks" in dropdown
    And I click on "<options>" in "my_perks" page
    Then I should be navigated to the registry sign in page
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "<target>" page

    Examples:
      | options             | target  |
      | my_perks_sign_in    | signin  |
      | my_perks_create_reg | profile |

  ## Negative scenario.
  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the error message if user try to create a new registry with an existing account
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I select "Create Your Registry"
    Then I should be navigated to the registry sign in page
    When I enter an existing email id
    Then I should see "This email is associated with a bloomingdales.com account." validation message on "registry_sign_in" page

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the error message if user try to login throuhg manage registry with an existing account
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I hover on getting started link
    And I select "manage_registry" in dropdown
    Then I should be navigated to the registry sign in page
    When I enter an existing email id
    Then I should see "<error>" validation message on "registry_sign_in" page
    | There is no registry associated to this profile. Please create a registry or claim your in-store created registry from the Registry Homepage. |

  ## Registry sign in page UI validation.

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify UI elements on create registry sign in page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    Then I should verify below fields on "registry_sign_in" page
      | existing_user_email    |
      | existing_user_password |
      | reg_sign_in_button     |
      | pwd_show_button        |
      | learn_more             |
      | get_started            |
      | forgot_password_link   |

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the forgot password and learn more link function on registry sign in page.
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    When I click on "<input>" in "registry_sign_in" page
    Then I should be redirected to "<target>" page

    Examples:
      | input                | target           |
      | forgot_password_link | forgot_password  |
      | learn_more           | customer_service |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the show or hide button in password field on registry sign in page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    Then I should see typed password using show button in "registry_sign_in" page
    And I should see hide button on "registry_sign_in" page

  ## Registry sign in page error validation

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the validation message when invalid credentials is entered
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I navigate to registry sign in page
    And I enter invalid "<email>" and "<password>"
    Then I should see "<expected error>" validation message on "registry_sign_in" page

    Examples:
      | email              | password  | expected error                                                                                                   |
      | testmail           | test12345 | Your email address must be entered in this format: jane@company.com                                              |
      | testmail@gmail.com | test      | Your password must be between 5-16 characters, and cannot include . , - \| \\ / = _ or spaces. Please try again. |
      | testmail@gmail.com | test12345 | That email address/password combination is not in our records. Forgot Your Password? Reset it now                |
      |                    | test12345 | Please enter your email address                                                                                  |
      | jane@bcom.com      |           | Please enter your password                                                                                       |

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

  # Homepage to registry sign-in page navigation through The Registry FOB.

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior for registry through the options under the registry FOB
    Given I visit the web site as a guest user
    And I navigate to registry home page
    When I click on "<options>" in "home" page
    Then I should be navigated to registry "sign in" page
    When I sign in with existing credentials from "<options>" page
    When I sign in with existing credentials from "new_registry_sign_in" page
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | options                | target              |
      | create_registry_button | new_create_registry |
      | view_registry          | registry_manager    |
      | registry_manager       | registry_manager    |

  ######################### URL redirection flow ##############################################
  # 1) user should be signed in i.e signedIn cookie value = 1
  # 2) cookie secure_user_toke is present
  # 3) SMISCGS cookie is present with key value regld for registry user

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify registry url is navigating to appropriate url.
    Given I visit the web site as a registered user
    When I navigate to registry home page
    And I select "Create Your Registry"
    And I create a new registry for user with existing bcom profile
    Then I should see the registry created successfully
    When I navigate to the url "<request_page>" as signed-in user
    Then I should be navigated to "<target_page>" url

    Examples:
      | request_page                                 | target_page                      |
      | registry/wedding/registrycaptureemail        | registry/wedding/registrymanager |
      | registry/wedding/registrysignin              | registry/wedding/registrymanager |
      | registry/wedding/registrysignin?fromView=BVR | registry/wedding/registrymanager |
