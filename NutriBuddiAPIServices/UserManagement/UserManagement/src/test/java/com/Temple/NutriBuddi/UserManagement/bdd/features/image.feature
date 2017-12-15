Feature: Image get, add, update, and delete

  Scenario: Bob wants to upload an image
    When Bob adds an image
    Then it should succeed with adding the image

  Scenario: Jenny wants to delete an image
    When Jenny deletes an image
    Then it should succeed with deleting the image

  Scenario: Ken wants to update an image with coordinates
    When Ken provides the new information
    Then the image should update accordingly