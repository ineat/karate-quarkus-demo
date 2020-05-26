Feature: Create new todo

  Background:
    * def signIn = call read('authentication.feature') { username: 'todo-list-user', password: 'todo-list-user', clientId: 'karate-quarkus-demo', clientSecret: '19f745ce-5452-467f-bad8-ee14184240e5' }
    * def accessToken = signIn.accessToken
    * def todoJson = read('data/todo.json')
    * def resourceUrl = apiBaseUrl + '/todos'


  @nominal
  Scenario: Create todo should return 201 status
    # Create a todo
    Given url resourceUrl
    And header Authorization = 'Bearer ' + accessToken
    And request todoJson
    When method POST
    Then status 201

    * def location = responseHeaders['Location'][0]

    # Search a todo
    Given url location
    And header Authorization = 'Bearer ' + accessToken
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    And match response contains {title: 'course', priority: 1}

    # Remove a todo
    Given url location
    And header Authorization = 'Bearer ' + accessToken
    And header Content-Type = 'application/json'
    When method DELETE
    Then status 204

  Scenario: Create todo without title should return a 400 status
    # Create a todo
    Given url 'http://localhost:8080/api/todos'
    And header Authorization = 'Bearer ' + accessToken
    And request {description: "Aller faire les courses", priority : 1}
    When method POST
    Then status 400