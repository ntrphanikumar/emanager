Delete employee story

Narrative:
As a resource manager
I would like to delete an employee from system

Scenario: I should delete a particular employee from system by employee id
Given I am a resource manager
And I have access to emanager application
When I request do delete a existing employee
Then Employee should be removed successfully from system

Scenario: I should get error response when I request to delete a non existing employee
Given I am a resource manager
And I have access to emanager application
When I request do delete a employee with invalid employee id
Then I should get error response with error description as 'No employee exists with id: -10'