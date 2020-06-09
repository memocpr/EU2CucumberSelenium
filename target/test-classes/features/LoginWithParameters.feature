Feature: Login as different users


  Scenario: login as a driver user
    Given the user is on the login page
    When the user logs in using "user1" and "UserUser123"
    Then the user should be able to login
    And the title contains "Dashboard"


    #Homework
  Scenario: login with different user type
    Given the user logged in as "store manager"
    Then the user should be able to login
    And the title contains "Dashboard"
    #driver,sales manager,store manager