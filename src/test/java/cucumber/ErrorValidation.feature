@Tag
  Feature: Error Validation
    @ErrorValidation
    Scenario Outline: title of the test
      Given I landed on ecommerce  page
      When Logged in with username <email> and password <psw>
      Then "Incorrect email or password." message is displayed on login page
      Examples:
        |email|psw|
        |    eguarachi@yahoo.com | Paw0rd  |
