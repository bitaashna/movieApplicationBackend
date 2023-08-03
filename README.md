# Movie Application
Movie Application backend with Springboot, MySQL

# DB Schema
![movies_api_schema](https://github.com/aashnachourasia/movieApplication/assets/138990839/f3832d71-be4f-4dcc-83c6-2c53e448afc0)

# Features
  User can:
  1. register/login to website.
  2. view all sample movies.
  3. navigate to particular movie and view all the cast and info related to movie.
  4. go to any particular actor to see in which movie the cast played a role and info about the cast.
  5. add a movie to Favourites.
  6. delete a movie from Favourites.
  7. view all Favourites.
  8. create a review

# Implementation
  1. Created spring boot application and added code for API endpoints.
  2. Tested API endpoints using Postman application.

# Database
The application uses a database in MySQL called 'moviesApi' which consist of the following tables:
  * actors_table
  * movies_table
  * movie_actor
  * review_table
  * users_table
  * wishlist_table
    
Out of which the first 3 tables have to imported to MySQL workbench from the attached .csv files (utilities->SqlTable) and the dataTypes have to be checked and for movie_poster and actor_img datatype is set to blob and to set up the project the images should be updated in blobs using Right click->Load from file for each entry.

Other tables will be created when we run the application.

Application starting point is ```MovieApplication.java```. 
  
  
