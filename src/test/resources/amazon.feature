Feature: Login to amazon and check book details
  In amazon search for a book and read the list

  Scenario: Launch Amazon URL
    Given New Chrome Browser
    When Enter Book Name
    And Click Search Button
    Then Read Book Details
    And Close the Browser




