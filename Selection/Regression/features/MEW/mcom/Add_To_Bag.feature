Feature: Add to bag flows
  @done
  Scenario: Verify 'Add to Bag' flow for member product
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    And I select a random member product using mobile website
    Then I should see Add to Bag button on PDP page
    When I select available color and size
    And I select quantity of product items
    And I store product information
    And I tap on add to bag button
    Then I should see Add To Bag modal
    And I should see correct values on add to bag modal
    And I should see subtotal price on modal

  @done
  Scenario: Verify 'Continue Shopping' flow for member product
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    And I select a random member product using mobile website
    Then I should see Add to Bag button on PDP page
    When I select available color and size
    And I select quantity of product items
    And I store product information
    And I tap on add to bag button
    Then I should see Add To Bag modal
    When I tap "Continue Shopping" button on the Add to Bag modal
    And I should navigate back to PDP page

  @done
  Scenario: Verify pdp page against pdp service and fcc service
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    And I select a random member product using mobile website
    And I store product information
    Then I call pdp service and verify product info

  @done
  Scenario: Verify pdp page against pdp service and fcc service availability
    Given I visit mobile pdp page with "2809262" product
    And I store product information
    Then I verify pdp service for "2809262" rug product

  @wip @gene
  Scenario: Verify 'Add to Bag' flow for master product
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
       | Shop                   |
       | Women                  |
       | All Women's Clothing   |
       | Pajamas & Robes        |
    And select master product using mobile website
    #wip
    And I select available color and size for that member
    And I select quantity of product items for that member
    #And I store member product information
    #Then I should see Add to Brown Bag button for that member
    #When I tap on add to bag button for the member product
    #Then I should see Add To Bag modal
    #Then I should see bag count is updated with selected quantity on modal
    #And I should see correct values on add to bag modal:
    #  |product name     |
    #  |product price    |
    #  |product color    |
    #  |product size     |
    #And I should see subtotal price on modal
    #When I tap 'checkout' button on the Add to Bag modal
    #Then I should navigate to shopping bag page


  @p1 @component_test @add_to_bag @mingle-20084 @mingle-11165 @domain_selection @mew2
  Scenario: Verify 'Add to Bag' flow for search results page
    Given I visit the mobile web home page
    When I entered "shorts" in global search field and search
    And I select random regular price product on search results page
    Then I should see Add to Bag button on PDP page
    When I select available color and size
    And I select quantity of product items
    And I store product information
    And I store product current price value
    And I tap on add to bag button
    Then I should see Add To Bag modal
    Then I should see bag count is updated with selected quantity on modal
    And I should see correct values on add to bag modal:
      |product name     |
      |product price    |
      |product color    |
      |product size     |
    And I should see subtotal price on modal
    When I tap 'Continue Shopping' button on the Add to Bag modal
    And I should navigate back to PDP page


  @p1 @component_test @add_to_bag @mingle-20084 @mingle-11165 @domain_selection @mew2
  Scenario: Verify 'Add to Bag' flow for browse page
    Given I visit the mobile web home page
    And I navigate to category browse page that has boots products
    And I select random regular price product on category browse page
    Then I should see Add to Bag button on PDP page
    When I select available color and size
    And I select quantity of product items
    And I store product information
    And I store product current price value
    And I tap on add to bag button
    Then I should see Add To Bag modal
    Then I should see bag count is updated with selected quantity on modal
    And I should see correct values on add to bag modal:
      |product name     |
      |product price    |
      |product color    |
      |product size     |
    And I should see subtotal price on modal
    When I tap 'Continue Shopping' button on the Add to Bag modal
    And I should navigate back to PDP page


  @p2 @component_test @add_to_bag @mingle-11165 @domain_selection @mew2
  Scenario: Verify display of colorized image on confirmation overlay
    Given I visit the mobile web home page
    When I navigate to member PDP page that has "add to bag eligible product with colors and sizes and colorized images"
    And I select available color and size
    And I store colorized image name
    And I tap on add to bag button
    Then I should see selected colorized image on Add to Bag modal


  @p2 @component_test @add_to_bag @mingle-11165 @m616128 @mew1 @domain_selection @mew2
  Scenario: Verify confirmation message on add to bag overlay when user is 'authenticated'
    Given I visit the mobile web home page
    And I sign in through services
    And I navigate to member PDP page that has "add to bag eligible product with colors and sizes and regular price"
    And I select available color and size
    And I select quantity of product items
    When I tap on add to bag button
    Then I should see message in modal header: [number] item(s) added to [username] bag










