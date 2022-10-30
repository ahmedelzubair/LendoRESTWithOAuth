# Getting Started

### Reference Documentation

How to use the API:

- Please clone this repository and run it locally. 

- find the API postman collection in the root folder of the project.
Import it.
- Login by user (admin) and password (admin) or you can use the saved user in the postman collection.
- Or open AppSecurityConfig.java to set your user in the inMemoryAuthentication method.
- Then you should get the access token and refresh token from the response headers of the login process.
- After that you are good to go with testing all protected routes by setting the access token on each request header.
- Or put it in the postman environment variables to be used in all requests.

By default, the authorization header is set to Bearer token in the header of the parent environment variables in the postman collection.

Hope this what you are looking for.

Thanks and I am looking forward to hearing from you.

Ahmed M. Elzubair