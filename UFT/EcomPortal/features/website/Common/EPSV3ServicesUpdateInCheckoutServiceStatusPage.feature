Feature: As a member of the support team, I would like to have the MASS feature in MASS Portal,
  "Checkout Service status" updated to have the EPS V3 services included.

  @sst
  Scenario: Verify EPS V3 services when we update them to real/batch
    Given I login into mass portal as a valid user
    When I select "order_CellA" from SDP URL
    And I navigate to the "Checkout Services Status" page under External Service Utility section
    And I update "EPS V3 Services" to "BATCH_MODE" mode
    Then I should see following services are updated to "BATCH_MODE" in External Service Status page:
      | EPSAuthorizeV3Adapter                |
      | EPSAuthorizeVoidV3Adapter            |
      | EPSBalanceInquiryV3Adapter           |
      | EPSVoidV3Adapter                     |
      | EPSPayPalAuthorizeV3Adapter          |
      | EPSPayPalAuthorizeVoidV3Adapter      |
      | EPSRedemptionCommitV3Adapter         |
      | EPSRedemptionInquiryV3Adapter        |
      | EPSRedemptionCommitVoidV3Adapter     |
      | EPSTenderLookUpV3Adapter             |
      | EPSAuthorizeUSLAdapter               |
      | EPSAuthorizeUSLVoidAdapter           |
      | EPSThreeDSecureLookUpV3Adapter       |
      | EPSThreeDSecureAuthenticateV3Adapter |
      | EPSPayPalLookUpV3Adapter             |
      | EPSPayPalAuthenticateV3Adapter       |
      | EPSPayPalOrderV3Adapter              |
    And I verify that the eps services status is updated to "BATCH_MODE" in DB
    When I navigate to Checkout Services Status page
    And I update "EPS V3 Services" to "REAL_TIME_MODE" mode
    Then I should see following services are updated to "REAL_TIME_MODE" in External Service Status page:
      | EPSAuthorizeV3Adapter                |
      | EPSAuthorizeVoidV3Adapter            |
      | EPSBalanceInquiryV3Adapter           |
      | EPSVoidV3Adapter                     |
      | EPSPayPalAuthorizeV3Adapter          |
      | EPSPayPalAuthorizeVoidV3Adapter      |
      | EPSRedemptionCommitV3Adapter         |
      | EPSRedemptionInquiryV3Adapter        |
      | EPSRedemptionCommitVoidV3Adapter     |
      | EPSTenderLookUpV3Adapter             |
      | EPSAuthorizeUSLAdapter               |
      | EPSAuthorizeUSLVoidAdapter           |
      | EPSThreeDSecureLookUpV3Adapter       |
      | EPSThreeDSecureAuthenticateV3Adapter |
      | EPSPayPalLookUpV3Adapter             |
      | EPSPayPalAuthenticateV3Adapter       |
      | EPSPayPalOrderV3Adapter              |
    And I verify that the eps services status is updated to "REAL_TIME_MODE" in DB