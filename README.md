# Movie Battle

    Battle about movie grades

## Features

    Simple Login
    Load movie from http://www.omdbapi.com/
    Create a new game
    Finishes an existing game
    Creates a new round
    Select movie on round
    Player Ranking

## Domain

    Player
        id
        username
        password
    
    Game
        id
        Player
        Rounds
        GameStatus
    
    Round
        Id
        Movies
        SelectedMovie
        RoundStatus
    
    Movie
        Name
        Points

## Activities

    Create Initial structure - OK    
    Application
        Create use cases for the appication - OK
            Game Match
            Ranking
        Create the repositories for the database - OK
        Create the controllers - OK 
    Load using webscrappping - OK
    Document using OpenApi 3.0 - OK
    Unit Tests - In Progress
    Integration Tests - OK
    Authentication - ToDo

## Improvements

    Exception handling
    Increment OpenApi Documentation
    Use token for APIs
    
## Useful Links

    SWAGGER: http://localhost:8080/swagger-ui/index.html
    H2 Console: http://localhost:8080/h2-console/login.jsp?jsessionid=41d113c4a192099085ad8a9bf84065ea: 

## Contact
    Created by leandeodeponte@gmail.com