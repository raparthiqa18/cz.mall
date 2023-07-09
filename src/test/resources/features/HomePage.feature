Feature: Verify carousels on the Home Page


  @Test
  Scenario Outline: To verify each carousel on the Home Page contains 15 unique elements
    Given I am on the mall.cz home page
    When I scroll down to the carousels
    Then I should see unique <elementsCount> elements for each carousel

    Examples:
      | elementsCount |
      | 15            |