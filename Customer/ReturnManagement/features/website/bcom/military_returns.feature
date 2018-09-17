Feature: As a MILITARY customer,
  I would like to be able to print the necessary custom form, I would like to be able to complete a FREE RETURN,
  and I would like clear shipping instructions
  so I don't have to search for a URL on my own, I don't have to spend time searching for FAQs  and I don't have to pay for a label.

  @returns_regression @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Military Print customer forms redirects properly for a Signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "Military-FPO" order as a "signed in" user
    Then I should see print_customs_button on return confirmation page
    When I select print_customs_button on confirmation page
    Then I should be navigate to below url:
      | url | https://cns.usps.com/cfo/org.apache.struts2.dispatcher.StrutsRequestWrapper@315ae103/cfo/ShippingInformationAction_input|

  @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Military copy of return instructions display correctly below the header as a Guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "Military-FPO" order as a "guest" user
    Then I should see the below mailing label instructions under confirmation page header
      | MCOM |Mailing instructions for military returns PRINT your return label & customs document. PACK the item(s) you'd like to return & attach your return label to the package (use the original packaging if you have it). Make sure that there are no other tracking labels attached to the package. SHIP the package from your military post office. Please note: This label is only valid at military post offices.|
      | BCOM |Instructions for Mailing Your Return: 1. Print your confirmation page, shipping label and customs form. 2. Pack your items, slip in your confirmation page and affix the shipping label to the outside. Ensure that there are no other labels attached. 3. Fold the printed label at the solid lines. Place the label in a UPS Shipping Pouch, or affix it using clear plastic shipping tape over the entire label. Take care not to cover any seams or closures. 4. Bring the package and completed customs form to your Military Post Office. Please note: This label is only valid at military post offices. We will e-mail you a link to reprint a copy of your label at <email> Please retain that email for your record.|
    Then I should see reference number one as reservation number on confirmation page
    And I should see reference number two contains refund method

  @returns_regression @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Military copy of return instructions display correctly below the header as a Signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "Military-FPO" order as a "signed in" user
    Then I should see the below confirmation_instruction at bottom of confirmation page
      |MCOM | Mailing instructions for military returns PRINT your return label & customs document. PACK the item(s) you'd like to return & attach your return label to the package (use the original packaging if you have it). Make sure that there are no other tracking labels attached to the package. SHIP the package from your military post office. Please note: This label is only valid at military post offices.                                                                                                                                                                                                |
      |BCOM | Instructions for Mailing Your Return 1. Print your confirmation page, shipping label and customs form. 2. Pack your items, slip in your confirmation page and affix the shipping label to the outside. Ensure that there are no other labels attached. 3. Fold the printed label at the solid lines. Place the label in a UPS Shipping Pouch, or affix it using clear plastic shipping tape over the entire label. Take care not to cover any seams or closures. 4. Bring the package and completed customs form to your Military Post Office. Please note: This label is only valid at military post offices.|

  @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @use_domain_qual @migrated_to_sdt
  Scenario: Military print customs form button works correctly on order confirmation page for a guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "Military-FPO" order as a "guest" user
    And I navigate to order details page as a "guest" user
    And I select print mailing label on order details page
    Then I should see print_customs_button on return confirmation page on clicking print mailing label on order details page
    When I select print_customs_button on confirmation page on clicking print mailing label on order details page
    Then I should be navigate to below url from order confirmation page:
      | url | https://cns.usps.com/cfo/org.apache.struts2.dispatcher.StrutsRequestWrapper@315ae103/cfo/ShippingInformationAction_input|

  @returns_regression @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Military print customs form button works correctly on order confirmation page for a signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "Military-FPO" order as a "signed in" user
    And I navigate to order details page as a "signed" user
    And I select print mailing label on order details page
    Then I should see print_customs_button on return confirmation page on clicking print mailing label on order details page
    When I select print_customs_button on confirmation page on clicking print mailing label on order details page
    Then I should be navigate to below url:
      | url | https://cns.usps.com/cfo/org.apache.struts2.dispatcher.StrutsRequestWrapper@315ae103/cfo/ShippingInformationAction_input|

  @returns_regression @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Military copy of return instructions display correctly below the header through order details page as a Guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "Military-FPO" order as a "guest" user
    And I navigate to order details page as a "guest" user
    And I select print mailing label on order details page
    Then I should see the below mailing_label_instructions under confirmation page header on clicking print mailing label on order details page
      | BCOM |Instructions for Mailing Your Return: 1. Print your confirmation page, shipping label and customs form. 2. Pack your items, slip in your confirmation page and affix the shipping label to the outside. Ensure that there are no other labels attached. 3. Fold the printed label at the solid lines. Place the label in a UPS Shipping Pouch, or affix it using clear plastic shipping tape over the entire label. Take care not to cover any seams or closures. 4. Bring the package and completed customs form to your Military Post Office. Please note: This label is only valid at military post offices. We will e-mail you a link to reprint a copy of your label at <email> Please retain that email for your record.|
    Then I should see reference number one as reservation number on confirmation page on clicking print mailing label on order details page
    And I should see reference number two contains refund method on clicking print mailing label on order details page

  @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Military copy of return instructions display correctly below the header through order details page as a Signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "Military-FPO" order as a "signed in" user
    And I navigate to order details page as a "signed" user
    And I select print mailing label on order details page
    Then I should see the below confirmation_instruction at bottom of confirmation page on clicking print mailing label on order details page
      | MCOM  | Mailing instructions for military returns PRINT your return label & customs document. PACK the item(s) you'd like to return & attach your return label to the package (use the original packaging if you have it). Make sure that there are no other tracking labels attached to the package. SHIP the package from your military post office. Please note: This label is only valid at military post offices.|
      | BCOM  | Instructions for Mailing Your Return 1. Print your confirmation page, shipping label and customs form. 2. Pack your items, slip in your confirmation page and affix the shipping label to the outside. Ensure that there are no other labels attached. 3. Fold the printed label at the solid lines. Place the label in a UPS Shipping Pouch, or affix it using clear plastic shipping tape over the entire label. Take care not to cover any seams or closures. 4. Bring the package and completed customs form to your Military Post Office. Please note: This label is only valid at military post offices.|

  @returns_regression @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @migrated_to_sdt
  Scenario: Non-Military Print Customs form works correctly on the Order Confirmation page as a Guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    Then I should not see any print_customs_button on confirmation page

  @returns_regression @release_15H @domain_customer_management @project_return_management @priority_high @mode_domestic @artifact_shopapp @feature_military_returns @use_regression @returns_mgt_6 @use_domain_qual @migrated_to_sdt
  Scenario: Default location displays correctly for Military orders on the Order Details page
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "Military-APO" order as a "guest" user
    Then I should see print_customs_button on return confirmation page
    And I navigate to order details page as a "guest" user
    Then I should see return address as mentioned below
      |MCOM | PORTLAND         |
      |BCOM | CHESHIRE         |