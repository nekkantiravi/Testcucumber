# Author: Stores Domain Checkout Team
# Story: SDU-399 - Change to grid padding
# Date Created: 5/11/2017
# Date Signed Off:

Feature: Our current padding on our grid is 0.9375 rem or 15px on each column.
          As an associate, I'm proposing we cut that down to 0.375rem ( 6px ) that would scale with
          the base pixels of each screen size.

  Scenario: Verifying all of the below scenarios using gaven tool.
    Given I launch the macy's store domain CSG
    Then I should see the grid padding examples on the page as "0.375rem"