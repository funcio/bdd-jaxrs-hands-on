Feature: Ping

    Scenario: A ping resource responds with a pong

        When I GET /ping
        Then I receive a JSON message
        """
        {"message":"pong"}
        """