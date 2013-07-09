Create employee story

Narrative:
As a resource manager
I would like to an employee to the system

Scenario: I should not be able create an employee without name
When I try to create an employee without name
Then I should get error response with error description as 'Employee name cannot be empty'

Scenario: I should not be able create an employee without email
When I try to create an employee without email
Then I should get error response with error description as 'Employee email cannot be empty'

Scenario: I should not be able create an employee without date of birth
When I try to create an employee without date of birth
Then I should get error response with error description as 'Date of birth cannot be null'

Scenario: I should be able create an employee without extension
When I try to create an employee without extension
Then I should get created employee in response with all provided details filled

Scenario: I should be able create an employee with all details filled
When I try to create an employee with all details filled
Then I should get created employee in response with all provided details filled