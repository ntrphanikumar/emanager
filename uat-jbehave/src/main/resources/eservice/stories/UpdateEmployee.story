Create employee story

Narrative:
As a resource manager
I would like to update an employee in the system

Scenario: I should not be able update an employee without name
Given I am a resource manager
And I have access to emanager application
When I try to update an employee without name
Then I should get error response with error description as 'Employee name cannot be empty'

Scenario: I should not be able update an employee without email
Given I am a resource manager
And I have access to emanager application
When I try to update an employee without email
Then I should get error response with error description as 'Employee email cannot be empty'

Scenario: I should not be able update an employee without date of birth
Given I am a resource manager
And I have access to emanager application
When I try to update an employee without date of birth
Then I should get error response with error description as 'Date of birth cannot be null'

Scenario: I should be able create an employee without extension
Given I am a resource manager
And I have access to emanager application
When I try to update an employee without extension
Then I should get updated employee in response with all provided details filled

Scenario: I should be able create an employee with all details filled
Given I am a resource manager
And I have access to emanager application
When I try to update an employee with all details filled
Then I should get updated employee in response with all provided details filled