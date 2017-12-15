Feature: User goal CRUD for add, update, find, and delete

  Scenario: Sarah wants to delete a goal she no longer needs
    When she makes a request to usergoal/deleteUserGoal
    Then she should get back a message saying delete successful with status 200

  Scenario: Hannah wants to add a goal because she wants to only eat 1300 calories a day
    When she makes a request to usergoal/addUserGoal
    Then she should get back a message saying user goal updated with status 200