# Getting Started

### Reference Documentation

How to use the API:

- Please clone this repository and run it locally. 

- find the API postman collection in the root folder of the project.
Import it.
- And you can import the dummy test data from import.sql file. Or create some posts and comments.
- Create new user account using the signup route to test the API.
- Log in with the login route using your credentials you created. then you can get the access token and refresh token from the response header of the login process.
- After that you are good to go with testing all protected routes.

By default, the authorization header is set to Bearer token in the header of the parent folder of the collection, so you need to change it with your own.

For best users experience. I can build this API by saving access token and refresh to seperated db table.
So when there access tokens expire they can use the refresh token to get a new access token without having to log in again.


Hope this what you are looking for.

Thanks and I am looking forward to hearing from you.

Ahmed M. Elzubair