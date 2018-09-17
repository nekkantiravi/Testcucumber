 ##########################################################################
 # Credit service gateway page scenarios
 # Author: QE Team
 # ########################################################################

Feature: As a signed in customer on the credit gateway page, I need to see information about Other Ways to Pay, Activation,
  and Apply, so I can take the necessary actions.

  # Story: B-69964 ::BUS::UI::BCOM::Add Edit/Remove Card link to QUICK TOOLS on Credit Gateway

  @domain_customer @project_credit_re_platform @credit_edit_remove @release_17G @da_credit_enhancements
  Scenario: verify edit or remove link on gateway page for primary/seconday card holder
  Given I sign in with user having "primary_account" added in profile
   #Note:: As part of above step will login with user having primary/seconday card in profile
  When I navigate to the "credit services" page from footer
  Then I should see Edit or remove a card link
  And I select 'Edit or remove a card' link
    Then I should redirect to "citi_fusion" page
    And I should see "manageaccount" process indicator

  @domain_customer @project_credit_re_platform @credit_edit_remove @release_17G @da_credit_enhancements
  Scenario: verify edit or remove link on gateway page for authorized card holder
    Given I sign in with user having "authorized_account" added in profile
   #Note:: As part of above step will login with user having autorized card in profile
    When I navigate to the "credit services" page from footer
    Then I should see Edit or remove a card link
    And I select 'Edit or remove a card' link
    Then I should redirect to "citi_fusion" page
    And I should see "manageaccount" process indicator
  #Note:: CITI Manage Account page url "https://citiretailservices.citibankonline.com/RSnextgen/svc/launch/sso.action?siteId=PLCN_BLOOMINGDALES&processIndicator= manageaccount"

  @use_manual @domain_customer @project_credit_re_platform @release_17G
  Scenario: verify edit or remove link on gateway page when kill switch is false
    Given I visit website a registered user with card in profile
    When I navigate to credit gateway page
    Then I should not see Edit or remove a card link