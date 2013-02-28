Feature: Start a server

    Scenario: Start a server

        When I GET /
        Then the response status is 404