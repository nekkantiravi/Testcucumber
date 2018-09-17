#############################################################################################
# Author: MSH - Transactional Pilot QA
# Date Created: 10/06/2015
# Date Signed Off:
#VersionOne Story no: B-26606
#############################################################################################

Feature: Verify the bi directional replication for two DB2 instances for user profile

#############################################################################################
  @RTP @domain_customer @project_MSH
  Scenario: Verify the data replication of user profile creation in RTP data base
    Given I visit the REST API service to create the user profile
     Then I should verify the user_profile on RTP Data Center

  @RTP @domain_customer @project_MSH
  Scenario: Verify data replication for new profile creation from DAL to RTP
    Given I visit the web site as a guest user
     When I click on signIn link
      And I set dca cookie value to DAL
        And I create a new profile
         And I navigate to my profile page
           And I update profile details on my profile
            Then I should verify the user_profile on DAL Data Center
              And I should see the user data is same in RTP Data Center

