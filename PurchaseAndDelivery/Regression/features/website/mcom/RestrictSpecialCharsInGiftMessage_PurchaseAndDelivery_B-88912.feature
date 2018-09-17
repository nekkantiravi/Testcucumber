#Author: UFT team
#Date Created: 08/29/2017
#Date Signed Off:
#Version One Card: B-88912

Feature: As a product owner, I would like to ensure that when customers send gifts and utilize the gift messaging option that their order
  can process through restricting the use of special characters in the UI. Only allow alphanumeric and "!" "."

  @release_17Q @artifact_shopapp @medium @domain_purchase_and_delivery @project_UFT
  Scenario: Verify only alphanumerics and special chars ! and . are allowd in gift message on checkout page in guest flow
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I enter following message in respective gift message field
      | field_name          | gift_message         |
      | gift_message_field1 | "#$%&'(Happy...)*+,  |
      | gift_message_field2 | -/:;Birth  day<=>?@  |
      | gift_message_field3 | [\]^_To You!!!`{\|}~ |
    Then I should see given messages in respective fields
      | field_name          | gift_message |
      | gift_message_field1 | Happy...     |
      | gift_message_field2 | Birth  day   |
      | gift_message_field3 | To You!!!    |

  @release_17Q @artifact_shopapp @medium @domain_purchase_and_delivery @project_UFT
  Scenario: Verify only alphanumerics and special chars ! and . are allowd in gift message on checkout page in sign in flow
    Given I visit the web site as a signed in user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I expand gift options section
    And I select gift options on shipping page
    And I enter following message in respective gift message field
      | field_name          | gift_message         |
      | gift_message_field1 | "#$%&'(Happy...)*+,  |
      | gift_message_field2 | -/:;Birth  day<=>?@  |
      | gift_message_field3 | [\]^_To You!!!`{\|}~ |
    Then I should see given messages in respective fields
      | field_name          | gift_message |
      | gift_message_field1 | Happy...     |
      | gift_message_field2 | Birth  day   |
      | gift_message_field3 | To You!!!    |