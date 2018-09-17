Feature: Verify the functionality of NGF(Not Go Forward) product on Bride View Registry (BVR)

  @B-102578 @BVR @registry @domain_selection @use_regression @core_registry
  Scenario: Verify that NGF message is displayed when NGF_Date of an item is less than 30 days after event date on BVR page
    Given I visit the web site as a registry user
    And I navigate to "registrable and future_ngf_with_in_thirty_days" product PDP page
    And I add registry product to BVR page from standard PDP
    Then I should see NGF message on BVR
      |This item will be discontinued soon.|
      |See Similar Items|
    When I click on similar items link from NGF message
    Then I should be navigated to sub category page in registry mode


  @B-102578 @BVR @registry @domain_selection @use_regression @core_registry
  Scenario: Verify that NGF message is displayed when NGF_Date of an item is equal to 30 days after event date on BVR page
    Given I visit the web site as a registry user
    And I navigate to "registrable and future_ngf_on_thirth_day" product PDP page
    And I add registry product to BVR page from standard PDP
    Then I should see NGF message on BVR
      |This item will be discontinued soon.|
      |See Similar Items|
    When I click on similar items link from NGF message
    Then I should be navigated to sub category page in registry mode

  @B-102578 @BVR @registry @domain_selection @use_regression @core_registry
  Scenario: Verify that no NGF message is displayed if NGF_Date of an item grater than 30 days after event date on BVR page
    Given I visit the web site as a registry user
    And I navigate to "registrable and future_ngf_after_thirty_days" product PDP page
    And I add registry product to BVR page from standard PDP
    Then I should not see NGF message on BVR
      |This item will be discontinued soon.|
      |See Similar Items|

  @B-102578 @BVR @registry @domain_selection @use_regression @core_registry
  Scenario: Verify that NGF message is displayed when NGF_date of an item is before event date on BVR page
    Given I visit the web site as a registry user
    And I navigate to "registrable and pre_event_ngf_date" product PDP page
    And I add registry pre event ngf date product to BVR page from standard PDP
    Then I should see NGF message on BVR
      |This item will be discontinued soon.|
      |See Similar Items|
    When I click on similar items link from NGF message
    Then I should be navigated to sub category page in registry mode

  @B-102578 @BVR @registry @domain_selection @use_regression @core_registry
  Scenario: Verify that NGF message is displaying only for if NGF_Date of an item is with in 30 days after event date on BVR page
    Given I visit the web site as a registry user
    And I navigate to "registrable and future_ngf_after_thirty_days" product PDP page
    And I add registry product to BVR page from standard PDP
    And I navigate to "registrable and future_ngf_with_in_thirty_days" product PDP page
    And I add registry product to BVR page from standard PDP
    Then I should see NGF message on BVR
      |This item will be discontinued soon.|
      |See Similar Items|
    When I click on similar items link from NGF message
    Then I should be navigated to sub category page in registry mode
