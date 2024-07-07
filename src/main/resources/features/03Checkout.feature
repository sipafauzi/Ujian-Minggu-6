Feature: Checkout Product

  Scenario: Successfully checkout a product
    Given I logged in to the application
    When I add new product to the cart
    And I proceed to checkout
    And I provide valid checkout information
    And I finish the checkout process
    Then I should see the order confirmation page

  Scenario: Failed firstname a product
    Given I click back home
    When I add new product to the cart
    And I proceed to checkout
    And I provide invalid firstname checkout information
    Then I should see an error message firstname

  Scenario: Failed lastname checkout a product
    And I provide invalid lastname checkout information
    Then I should see an error message lastname

  Scenario: Failed zip checkout a product
    And I provide invalid postalcode checkout information
    Then I should see an error message zip
