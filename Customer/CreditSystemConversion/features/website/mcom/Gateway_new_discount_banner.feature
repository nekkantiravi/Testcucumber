 ###########################################################################
  # Story: B-57976 ::T-D::BUS::UI::Desktop::MCOM:: Gateway:: Update New Account Discount Banner
  # Author: QE Team
  # ###########################################################################

   Feature: As a customer, I would like to see current information about the new account discount

      # Pre-Requisite:: kill switch: creditCvvSuppressionEnabled=true

     @domain_customer @project_credit_re_platform @release_17E
     Scenario: Verify that user should see information links on credit gateway page as a guest user
       Given I visit the web site as a guest user
       When I navigate to the "credit services" page from footer
       Then I should see below fields on new account discount banner:
       | apply_now_button            |
       | goto_learn_more_link        |
       | exclusions_and_details_link |

     @domain_customer @project_credit_re_platform @release_17E
     Scenario Outline: Verify that user should get more information about new account discount as a guest
       Given I visit the web site as a guest user
       When I navigate to the "credit services" page from footer
       And I select "<information_links>" on new account discount banner
       Then I should reach "<target>" page
       Examples:
         | information_links           | target                   |
         | apply_now_button            | fusion_apply_card        |
         | goto_learn_more_link        | learn_more_and_apply_now |
         | exclusions_and_details_link | exclusions_and_details   |

     @domain_customer @project_credit_re_platform @release_17E
     Scenario: Verify that user should see information links on credit gateway page as a registered user
       Given I visit the web site as a registered user
       When I navigate to the "credit services" page from footer
       Then I should see below fields on new account discount banner:
         | apply_now_button            |
         | goto_learn_more_link        |
         | exclusions_and_details_link |

     @domain_customer @project_credit_re_platform @release_17E
     Scenario Outline: Verify that user should get more information about new account discount as a registered user
       Given I visit the web site as a registered user
       When I navigate to the "credit services" page from footer
       And I select "<information_links>" on new account discount banner
       Then I should reach "<target>" page
       Examples:
         | information_links           | target                   |
         | apply_now_button            | fusion_apply_card         |
         | goto_learn_more_link        | learn_more_and_apply_now |
         | exclusions_and_details_link | exclusions_and_details   |