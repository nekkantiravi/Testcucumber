 ###########################################################################
           # Story: B-69861 ::BUS::UI::Desktop::MCOM::Credit Gateway::Display Single Full-Size Marketing Banner
           # Author: QE Team
           ############################################################################

 Feature: As a credit card customer, I would like to get the appropriate marketing banner displayed based on whether
   I am signed in and have cards added or not

   @use_manual @domain_customer @project_credit_re_platform @release_17E
   Scenario: Verify that guest user should see static-full size banner on credit gateway page
     Given I visit the web site as a guest user
     When I navigate to credit service gateway page
     Then I should see static full size banner on gateway page
     ##Note: If BANNER2 Astra pool is not populated, banner will not be displayed.

   @use_manual @domain_customer @project_credit_re_platform @release_17E
   Scenario Outline: Verify that signedin user should see dynamic-full size banner on credit gateway page
     Given I visit the web site as a registered user having <card_type> to the profile
     When I navigate to credit service gateway page
     Then I should see <banner_type> on gateway page
     Examples:
       | card_type    | banner_type              |
       | prop card    | dynamic full size banner |
       | cobrand card | dynamic full size banner |
     ##Note:1 If BANNER1_PROP Astra pool is not populated, banner will not be displayed.
     ##Note:2 If BANNER1_COBRAND Astra pool is not populated, banner will not be displayed.

   @use_manual @domain_customer @project_credit_re_platform @release_17E
   Scenario: Verify that signedin user with no card should see static-full size banner on credit gateway page
     Given I visit the web site as a registered user with no card added to the profile
     When I navigate to credit service gateway page
     Then I should see static full size banner on gateway page
        ##Note: If BANNER2 Astra pool is not populated, banner will not be displayed.












