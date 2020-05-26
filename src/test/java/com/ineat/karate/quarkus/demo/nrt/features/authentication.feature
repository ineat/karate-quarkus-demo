Feature: Reusable authentication feature

  Scenario:
    Given url authUrl
    And request { username: '#(username)', password: '#(password)', clientId: '#(clientId)', clientSecret: '#(clientSecret)'}
    And form field username = username
    And form field password = password
    And form field client_id = clientId
    And form field client_secret = clientSecret
    And form field grant_type = 'password'
    When method POST
    Then status 200
    And def accessToken = response.access_token