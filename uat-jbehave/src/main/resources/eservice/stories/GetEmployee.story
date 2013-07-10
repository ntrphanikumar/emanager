Create employee story

Narrative:
As a resource manager
I would like to get details of all employees in system

Scenario: I should see details of all employees in system
Given I am a resource manager
And I have access to emanager application
When I request for all employees in system
Then I should get list of all employees

Scenario: I should see details of a particular employee in system by employee id
Given I am a resource manager
And I have access to emanager application
When I request for details of an existing employee
Then I should get employee in response

Scenario: I should get error response when I request for details of employee with invalid id
Given I am a resource manager
And I have access to emanager application
When I request for details of an employee with invalid employee id
Then I should get error response with error description as 'No employee exists with id: -10'