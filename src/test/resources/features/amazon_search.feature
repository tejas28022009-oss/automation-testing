Feature: Amazon Product Search

  Scenario: User searches for a product
    Given the user is on Amazon homepage
    When the user searches for "iPhone"
    Then search results should be displayed
