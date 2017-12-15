Feature:  Eats add, get, & put

  Scenario: Eleven wants to get food nutritional values for Eggos
    When she makes the call to /eats/getEatsByFoodName
    Then a response should be retrieved with status 200

  Scenario: Elliot wants to get a history of a food he's eaten
    When he makes the call to /eats/getEatsByDatesAndEmail with valid date range
    Then a response should be retrieved with status 200 with list of foods