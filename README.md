## What do we want to do? 🙌

Implement a sign-up/sign-in mechanism using JWT and Spring Security in a demo Spring Boot project.

## Why do it? 👨‍🔬
Secure our application to specify the role that every user will play. Admins have superuser privileges. A user can't see other user's data. Each user sees a custom view. etc...
## How to do it?

## **1️⃣** 
Import ***spring-boot-starter-security*** using Maven.
Note, Importing it will make Spring:
-   Add mandatory authentication for all URLs.
-   Adds login form.
-   Handle login errors.
-   Creates a user and sets a default password.


## **2️⃣**
Create database entities
-   User
-   Authority

User and authority have a OneToMany relationship.
Create repositories for them as well

## **3️⃣**
Create a class (let's call it ***CustomUserDetails***) that implements ***UserDetails*** interface from Spring Security

-   We implement some methods like getPassword, getUserName, etc...

## **4️⃣**
Create a class (let's call it ***CustomUserDetailService***) that implements ***UserDetailsService*** interface from Spring Security

-   Implement *loadUserByUsername* method Using the username, write the code to get the corresponding User from the database.

## **5️⃣**
Create a service for JWT processing
-   Include ***jjwt*** Maven dependency
-   Create jwt service class with methods to create, validate JWT tokens.

## **6️⃣**
Create a request filter for JWT tokens
-   It extends the abstract class ***OncePerRequestFilter***
-   It overrides a single method (***doFilterInternal***) from the class.

## **7️⃣**
Create a **security configuration** class

-   Extends ***WebSecurityConfigurerAdapter*** from Spring Security
-   Annotate it with ***@EnableWebSecurity***
-   Override the method ***configure(AuthenticationManagerBuilder auth)***

-   Override the method ***configure(HttpSecurity http)***
	-   Set the ant matchers.
	-   Add the request filter we had created.

-   Create a Bean method that returns a ***PasswordEncoder***.
- Create a bean method that returns an ***AuthenticationManager*** object.

## **8️⃣**
Add a backend controller to allow a user to get a ***JWT token***.

-   Call the ***authenticate*** method of the ***AuthenticationManager*** class using the username and password provided by the client.
	-   If the provided credentials aren’t correct an exception will be thrown.
-   Create and return a JWT using the ***JwtService*** we had created.

## **9️⃣**
Add a backend controller for adding new users (Signup).


## How does it all play together?

✅ The incoming request first goes through Spring Security filters

- Its configurations are defined in the security config file at the method configure(HttpSecurity http)
	    
- There we specify the URLs that specific users can access
	    

	-   For example, all requests can go to the URL /login
	    
	-   Only authenticates users with the role Admin can go to /admin
    
✅ Then, if the request passes it goes into the JWT request filter we had created and set in the security config.

- We extract the username and password from the request’s Authorization header
	    
- If there are a valid token and an existing user with the given user name
	    
	-   Create a ***UsernamePasswordAuthenticationToken*** object and set it to the authentication of the security context.

✅ Then the request proceeds to its **destination URL**

- If it’s the authentication URL.

	- The AuthenticationManager object is called using the password and username provided.

	-  If the credentials don’t match an AuthenticationException will be thrown.
		
	- If not thrown, a JWT token will be created and returned to the client to include in future requests’ Authorization header.
