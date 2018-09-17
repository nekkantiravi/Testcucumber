#############################################################################################
# Author: MSH - Transactional Pilot QA
# Date Created: 10/06/2015
# Date Signed Off:
#VersionOne Story no: B-26606
#############################################################################################

Feature: Verify the bi directional replication for two DB2 instances for user profile

#############################################################################################

  @wip @domain_customer @project_MSH
   Scenario: Verify the data replication of user details in DAL data base
    Given I have visited home page
     Then I should see the user data is same in both the data bases