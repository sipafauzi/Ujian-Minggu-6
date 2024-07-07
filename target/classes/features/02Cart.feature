Feature: Add to Cart

  Scenario: Add a product to the cart successfully
    Given I am logged in to the application
    When I add a product to the cart
    Then I should see total product on icon cart
    And button Add to Cart changed to Remove
    And I am on the cart page
    And the product should be in the cart