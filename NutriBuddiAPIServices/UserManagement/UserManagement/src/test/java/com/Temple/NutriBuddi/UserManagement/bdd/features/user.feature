Feature: User registration, login, and update

  Scenario: Mike wants to view all users
    Given Mike is authorized
    When he makes the call to /user/all
    Then a response should be retrieved with status 200 of type json
    And is populated with user names