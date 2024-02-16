@tag
  Feature: Purchase the order from ecommerce website

Background:
  Given I landed on ecommerce  page
    @Regression
    Scenario Outline: Positive test of submitting order
      Given Logged in with username <email> and password <psw>
      When i add product <prodName> to cart
      And checkOut <prodName> and submit the order
      Then "Thankyou for the order." is displayed on confirmation page

      Examples:
      |email|psw| prodName|
      |    eguarachi@yahoo.com | Pa$$w0rd  |  ZARA COAT 3|
